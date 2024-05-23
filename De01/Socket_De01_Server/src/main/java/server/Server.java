package server;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import connection.ConnectionMSSQL;
import dao.DAO;
import dao.imp.daoimp;
import entity.Food;
import entity.Item;
import entity.Type;

public class Server {

	public static void main(String[] args) {
		
		ConnectionMSSQL.open();
		
		try (ServerSocket serversocket = new ServerSocket(8080)) {
			System.out.println("Server is listening on port 8080");
			while (true) {
				Socket socket = serversocket.accept();
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

class ClientHandler implements Runnable {

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
				switch (choice) {
				case 1:
//					Food(String id, String name, double price, String description, boolean onSpecial, Type type,
//					int preparationTime, int servingTime)
					String id = in.readUTF();
					String name = in.readUTF();
					double price = in.readDouble();
					String description = in.readUTF();
					boolean onSpecial = in.readBoolean();
					Type type = Type.valueOf(in.readUTF());
					int preparationTime = in.readInt();
					int servingTime = in.readInt();
					
					boolean result = dao.addFood(new Food(id, name, price, description, onSpecial, type, preparationTime, servingTime));
					out.writeBoolean(result);
					
					break;
				
				case 2:
					String supplierName = in.readUTF();
					dao.listItems(supplierName).forEach(System.out::println);
					
					List<Item> list = dao.listItems(supplierName);
					list.forEach(System.out::println);
					out.writeObject(list);
					
					break;
					
				case 3:
					Map<Food, Double> map = dao.listFoodAndCost();
					map.forEach((k, v) -> System.out.println(k + " - " + v));
					out.writeObject(map);
					
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
