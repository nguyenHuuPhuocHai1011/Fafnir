package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import entity.Book;

public class Client {

	public static void main(String[] args) {
		
		try (Socket socket = new Socket("localhost", 8080);
				Scanner scanner = new Scanner(System.in);){
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			int choice = 0;
			
			while(true) {
				System.out.println("Enter your choice: ");
				System.out.println("1. List books by author and rating");
				
				choice = scanner.nextInt();
				
				out.writeInt(choice);
				
				switch(choice) {
				case 1:
					scanner.nextLine();
					System.out.println("Enter author name: ");
					String author = scanner.next();
					out.writeUTF(author);
					out.flush();

					scanner.nextLine();
					System.out.println("Enter rating: ");
					int rating = scanner.nextInt();
					out.writeInt(rating);
					out.flush();
					
					@SuppressWarnings("unchecked") 
					List<Book> listRatedBooks = (List<Book>) in.readObject();
					listRatedBooks.forEach(System.out::println);

					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
