package dao;

import java.util.List;
import java.util.Map;

import entity.Candidate;
import entity.Position;

public interface DAO {

	public List<Position> listPositions(String name, double salaryFrom, double salaryTo);
	
	public Map<Candidate, Long> listCadidatesByCompanies();
	
	public Map<Candidate, Position> listCandidatesWithLongestWorking();
	
	public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID);
}
