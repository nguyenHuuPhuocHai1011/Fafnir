package dao.imp;

import java.util.List;
import java.util.Map;

import connection.ConnectionMSSQL;
import dao.DAO;
import entity.Candidate;
import entity.Position;
import jakarta.persistence.EntityManager;

public class daoimp implements DAO{
	
	private static EntityManager entityManager = ConnectionMSSQL.getEntityManager();
	
//	a) Liệt kê danh sách các vị trí công việc khi biết tên vị trí (tìm tương đối) và mức lương khoảng từ, 
//	kết quả sắp xếp theo tên vị trí công việc. 
//	+ listPositions(name: String, salaryFrom: double, salaryTo: double): List<Position>  
	public List<Position> listPositions(String name, double salaryFrom, double salaryTo){
		String query = "SELECT p FROM Position p WHERE p.name LIKE :name AND p.salary >= :salaryFrom AND p.salary <= :salaryTo ORDER BY p.name";
		List<Position> list = entityManager.createQuery(query, Position.class).setParameter("name", "%" + name + "%")
				.setParameter("salaryFrom", salaryFrom).setParameter("salaryTo", salaryTo).getResultList();
        return list;
    }
	
//	b) Liệt kê danh sách các ứng viên và số công ty mà các ứng viên này từng làm. 
//	+ listCadidatesByCompanies() : Map<Candidate, Long>
	public Map<Candidate, Long> listCadidatesByCompanies() {
		String query = "SELECT e.candidate, COUNT(e.companyName) FROM Experience e GROUP BY e.candidate";
		List<?> list = entityManager.createQuery(query).getResultList();
		Map<Candidate, Long> map = list.stream().map(o -> (Object[]) o)
				.map(a -> Map.entry((Candidate) a[0], (Long) a[1]))
				.collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return map;
	}
	
//	c) Tìm danh sách các ứng viên đã làm việc trên một vị trí công việc nào đó có thời gian làm lâu nhất.   
//	+ listCandidatesWithLongestWorking (): Map<Candidate, Position>
	public Map<Candidate, Position> listCandidatesWithLongestWorking() {
		String query = "SELECT e.candidate, e.position FROM Experience e "
				+ "WHERE DATEDIFF(day, e.fromDate, e.toDate) = "
				+ "(SELECT MAX(DATEDIFF(day, e1.fromDate, e1.toDate)) FROM Experience e1 "
				+ "WHERE e1.candidate = e.candidate)";
		List<?> list = entityManager.createQuery(query).getResultList();
		Map<Candidate, Position> map = list.stream().map(o -> (Object[]) o)
				.map(a -> Map.entry((Candidate) a[0], (Position) a[1]))
				.collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return map;
	}
	
//	d) Thêm một ứng viên mới. Trong đó mã số ứng viên phải bắt đầu là C, theo sau ít nhất là 3 ký số. 
//	+ addCandidate(candidate: Candidate) : boolean
	public boolean addCandidate(Candidate candidate) {
		if (candidate.getId().matches("C\\d{3,}")) {
			entityManager.getTransaction().begin();
			entityManager.persist(candidate);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}
	
//	e) Tính số năm làm việc trên một vị trí công việc nào đó khi biết mã số ứng viên.   
//	+ listYearsOfExperienceByPosition(candidateID: String): Map<Position, Integer>
	public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID) {
		String query = "SELECT e.position, DATEDIFF(year, e.fromDate, e.toDate) FROM Experience e "
				+ "WHERE e.candidate.id = :id";
		List<?> list = entityManager.createQuery(query).setParameter("id", candidateID).getResultList();
		Map<Position, Integer> map = list.stream().map(o -> (Object[]) o)
				.map(a -> Map.entry((Position) a[0], (Integer) a[1]))
				.collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return map;
	}
}
