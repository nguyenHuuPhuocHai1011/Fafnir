package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entity.Food;
import entity.Item;

public class Client {

	public static void main(String[] args) {
		
		try (Socket socket = new Socket("localhost", 8080);
				Scanner scanner = new Scanner(System.in)) {
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			int choice = 0;
			
			while(true) {
				System.out.println("Enter your choice: ");
				System.out.println("1. addFood");
				System.out.println("2. ListItems");
				System.out.println("3. listFoodAndCost");
				System.out.println("4. Exit");
				
				choice = scanner.nextInt();
				
				out.writeInt(choice);
				
				switch(choice) {
				case 1:
					scanner.nextLine();
					System.out.println("Enter id: ");
					String id = scanner.nextLine();
					out.writeUTF(id);
					out.flush();

					scanner.nextLine();
					System.out.println("Enter name: ");
					String name = scanner.nextLine();
					out.writeUTF(name);
					out.flush();

					scanner.nextLine();
					System.out.println("Enter price: ");
					double price = scanner.nextDouble();
					out.writeDouble(price);
					out.flush();

					scanner.nextLine();
					System.out.println("Enter preparationTime: ");
					int preparationTime = scanner.nextInt();
					out.writeInt(preparationTime);
					out.flush();

					scanner.nextLine();
					System.out.println("Enter servingTime: ");
					int servingTime = scanner.nextInt();
					out.writeInt(servingTime);
					out.flush();

					scanner.nextLine();
					System.out.println("Enter onSpecial: ");
					boolean onSpecial = scanner.nextBoolean();
					out.writeBoolean(onSpecial);
					out.flush();

					scanner.nextLine();
					System.out.println("Enter ingredients: ");
					String ingredients = scanner.nextLine();
					out.writeUTF(ingredients);
					out.flush();

					boolean result = in.readBoolean();
					if (result) {
						System.out.println("Add food successfully");
					} else {
						System.out.println("Add food failed");
					}
					break;
					
				case 2:
					scanner.nextLine();
					System.out.println("Enter supplierName: ");
					String supplierName = scanner.nextLine();
					out.writeUTF(supplierName);
					out.flush();

					@SuppressWarnings("unchecked")
					List<Item> list = (List<Item>) in.readObject();
					list.forEach(System.out::println);
					
					break;
					
				case 3:
					@SuppressWarnings("unchecked")
					Map<Food, Double> listFoodAndCost = (Map<Food, Double>) in.readObject();
					listFoodAndCost.forEach((k, v) -> System.out.println(k + " - " + v));
					
					break;
					
				case 4:
					System.exit(0);
					break;
					
				default:
					break;
				}
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
