package query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connection.ConnectionMSSQL;
import dao.DAO;
import dao.imp.daoimp;
import entity.Candidate;
import entity.Certificate;
import entity.Position;

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
	void listPositions() {
        System.out.println("listPositions()");
        List<Position> list = dao.listPositions("Admin", 500, 15000);
        list.forEach(System.out::println);
    }
		
	@Test
	void listCadidatesByCompanies() {
		System.out.println("listCadidatesByCompanies()");
		Map<Candidate, Long> map = dao.listCadidatesByCompanies();
		map.forEach((k, v) -> System.out.println(k + " - " + v));
	}
	
	@Test
	void listCandidatesWithLongestWorking() {
		System.out.println("listCandidatesWithLongestWorking()");
		Map<Candidate, Position> map = dao.listCandidatesWithLongestWorking();
		map.forEach((k, v) -> System.out.println(k + " - " + v));
	}
	
	@Test
	void listYearsOfExperienceByPosition() {
		System.out.println("listYearsOfExperienceByPosition()");
        Map<Position, Integer> map = dao.listYearsOfExperienceByPosition("C105");
        map.forEach((k, v) -> System.out.println(k + " - " + v));
    }

	@Test
	void listCadidatesAndCertificates() {
		System.out.println("listCadidatesAndCertificates()");
		Map<Candidate, Set<Certificate>> map = dao.listCadidatesAndCertificates();
		map.forEach((k, v) -> System.out.println(k + " - " + v));
	}
	
//	 Candidate(String id, String fullName, int yearOfBirth, String gender, String email, String phone,
//				String description)
	
	@Test
	void addCandidate() {
        System.out.println("addCandidate()");
        Candidate candidate = new Candidate("C001", "Nguyen Van", 1999, "Nam", "abc@gmail.com", "123456789", "abc");
        assertTrue(dao.addCandidate(candidate));
	}
	
	@Test
	void testAddDuplicate() {
        System.out.println("testAddDuplicateCandidate()");
        Candidate candidate = new Candidate("C001", "Nguyen Van", 1999, "Nam", "abc@gmail.com", "123456789", "abc");
        assertEquals(false, dao.addCandidate(candidate));
	}
	
	@Test
	void testAddInvalid() {
        System.out.println("testAddInvalidCandidate()");
        Candidate candidate = new Candidate("C", "Nguyen Van", 1999, "Nam", "abc@gmail.com", "123456789", "abc");
        assertEquals(false, dao.addCandidate(candidate));
	}
}