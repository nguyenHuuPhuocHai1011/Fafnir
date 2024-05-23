package query;

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
import entity.Book;

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
	void listRatedBooks(){
		System.out.println("listRatedBooks()");
		List<Book> list = dao.listRatedBooks("Andrew", 1);
		list.forEach(System.out::println);
	}
	
	@Test
	void countBooksByAuthor() {
		System.out.println("countBooksByAuthor()");
		Map<String, Long> map = dao.countBooksByAuthor();
		map.forEach((k, v) -> System.out.println(k + " - " + v));
	}

}
