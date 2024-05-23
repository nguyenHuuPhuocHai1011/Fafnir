package query;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connection.ConnectionMSSQL;
import dao.DAO;
import dao.imp.daoimp;
import entity.Food;
import entity.Item;
import entity.Type;

class JTC_Query {

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
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	DAO dao = new daoimp();

	@Test
	void addFood() throws RemoteException {
        Food food = new Food("F001", "Banh Mi", 1000, "Banh Mi thit", false, Type.DESSERT, 10, 10);
        assertTrue(dao.addFood(food));
	}
	
	@Test
	void testAddDuplicate() throws RemoteException {
		Food food = new Food("F001", "Banh Mi", 1000, "Banh Mi thit", false, Type.DESSERT, 10, 10);
		assertEquals(false, dao.addFood(food));
	}
	
	@Test
	void testAddInvalid() throws RemoteException {
		Food food = new Food("F", "Banh Mi", 1000, "Banh Mi thit", false, Type.DESSERT, 10, 10);
		assertEquals(false, dao.addFood(food));
	}
	
	@Test
	void listItems() {
		System.out.println("listItems()");
		List<Item> list = dao.listItems("Bob");
		list.forEach(System.out::println);
	}
	
	@Test
	void listFoodAndCost() {
		System.out.println("listFoodAndCost()");
	    Map<Food, Double> map = dao.listFoodAndCost();
	    map.forEach((k, v) -> System.out.println(k + " : " + v));
	}

}
