package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entity.Candidate;
import entity.Position;

public class Client {

	public static void main(String[] args) {

		try (Socket socket = new Socket("localhost", 8080); 
				Scanner scanner = new Scanner(System.in);) {
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			int choice = 0;
			
			while(true) {
				System.out.println("Enter your choice: ");
				System.out.println("1. listPositions");
				System.out.println("2. listCadidatesByCompanies");
				System.out.println("3. listCandidatesWithLongestWorking");
				System.out.println("4. Exit");
				
				choice = scanner.nextInt();
				
				out.writeInt(choice);
				
				switch(choice) {
				case 1:
					scanner.nextLine();
					System.out.println("Enter name: ");
					String name = scanner.next();
					out.writeUTF(name);
					out.flush();

					scanner.nextLine();
					System.out.println("Enter salaryFrom: ");
					double salaryFrom = scanner.nextDouble();
					out.writeDouble(salaryFrom);
					out.flush();

					scanner.nextLine();
					System.out.println("Enter salaryTo: ");
					double salaryTo = scanner.nextDouble();
					out.writeDouble(salaryTo);
					out.flush();
					
					@SuppressWarnings("unchecked")
					List<Position> list = (List<Position>) in.readObject();
					list.forEach(System.out::println);

					break;
					
				case 2:
					@SuppressWarnings("unchecked")
					Map<Candidate, Long> map = (Map<Candidate, Long>) in.readObject();
					map.forEach((k, v) -> System.out.println(k + " - " + v));

					break;
				
				case 3:
					@SuppressWarnings("unchecked")
					Map<Candidate, Position> map1 = (Map<Candidate, Position>) in.readObject();
					map1.forEach((k, v) -> System.out.println(k + " - " + v));

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
