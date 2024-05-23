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
import entity.Candidate;
import entity.Position;

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
		
		try{
			DataInputStream in = new DataInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
			int choice = 0;
			
			while(true) {
				choice = in.readInt();
				switch(choice) {
				case 1:
					String name = in.readUTF();
					double salaryFrom = in.readDouble();
					double salaryTo = in.readDouble();
					
					List<Position> list = dao.listPositions(name, salaryFrom, salaryTo);
					list.forEach(System.out::println);
					
					out.writeObject(list);
					break;
					
				case 2:
					Map<Candidate, Long> map = dao.listCadidatesByCompanies();
					map.forEach((k, v) -> System.out.println(k + " - " + v));
					
					out.writeObject(map);
					break;
				
				case 3:
					Map<Candidate, Position> map1 = dao.listCandidatesWithLongestWorking();
					map1.forEach((k, v) -> System.out.println(k + " - " + v));
					
					out.writeObject(map1);
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