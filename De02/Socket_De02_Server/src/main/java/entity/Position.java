package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "positions")
public class Position implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2928142885156496442L;

	@Id
	@Column(name = "position_id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "salary")
	private double salary;
	
	@Column(name = "number")
	private int number;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
	private List<Candidate> candidates;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
	private List<Experience> experiences;
	
	public Position(String id, String name, String description, double salary, int number) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.salary = salary;
		this.number = number;
	}
	
}
