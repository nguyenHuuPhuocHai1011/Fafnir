package init;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connection.ConnectionMSSQL;
import jakarta.persistence.EntityManager;

class JTC_Init {

	private EntityManager entityManager;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ConnectionMSSQL.open();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		ConnectionMSSQL.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		entityManager = ConnectionMSSQL.getEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		entityManager.close();
	}

	@Test
	void test() {
		System.out.println(entityManager.createQuery("SELECT 'Connect Successfully!'").getSingleResult().toString());
	}

}
