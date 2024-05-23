package server;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import connection.ConnectionMSSQL;
import dao.DAO;
import dao.imp.daoimp;
import entity.Book;

public class Server {
	public static void main(String[] args) {
		
		ConnectionMSSQL.open();
		
		try (ServerSocket server = new ServerSocket(8080)) {
			
			System.out.println("Server is listening on port 8080");
			
			while (true) {
				Socket socket = server.accept();
				System.out.println("New client connected");
				System.out.println("Client IP: " + socket.getInetAddress().getHostName());
				
				Thread t = new Thread(new ClientHandler(socket));
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ClientHandler implements Runnable{

	private Socket socket;
	private DAO dao;
	
	public ClientHandler(Socket socket) {
		super();
		this.socket = socket;
		this.dao = new daoimp();
	}
	
	@Override
	public void run() {
		
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
			int choice = 0;
			
			while(true) {
				choice = in.readInt();
				switch(choice) {
				case 1:
					String author = in.readUTF();
					int rating = in.readInt();
					
					List<Book> listRatedBooks = dao.listRatedBooks(author, rating);
					listRatedBooks.forEach(System.out::println);
					
					//response to client
					
					out.writeObject(listRatedBooks);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}