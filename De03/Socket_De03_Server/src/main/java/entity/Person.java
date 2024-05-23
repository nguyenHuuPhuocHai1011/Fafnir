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
@Table(name = "people")
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2583508512288182544L;

	@Id
	@Column(name = "person_id")
	private int id;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "professional_title")
	private String professionalTitle;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
	private List<Reviews> reviews;
	
	public Person(int id, String lastName, String firstName, String email, String professionalTitle) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.professionalTitle = professionalTitle;
	}
	
}
