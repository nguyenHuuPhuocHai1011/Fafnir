package connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionMSSQL {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	private ConnectionMSSQL() {
	}
	
	public static void open() {
		entityManagerFactory = Persistence.createEntityManagerFactory("jpa-mssql");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public static void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public static EntityManager getEntityManager() {
		return entityManager;
	}
}
