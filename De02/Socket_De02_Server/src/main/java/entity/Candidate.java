package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "candidates")
public class Candidate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4536472594072696712L;

	@Id
	@Column(name = "candidate_id")
	private String id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "year_of_birth")
	private int yearOfBirth;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "description")
	private String description;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private Position position;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
	private List<Certificate> certificates;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
	private List<Experience> experiences;
	
	public Candidate(String id, String fullName, int yearOfBirth, String gender, String email, String phone,
			String description) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.yearOfBirth = yearOfBirth;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.description = description;
	}
}
