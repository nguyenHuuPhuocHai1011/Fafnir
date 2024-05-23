package entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "certificates")
public class Certificate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6276260044101846583L;

	@Id
	@Column(name = "certificate_id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "organization")
	private String organization;
	
	@Column(name = "issue_date")
	private LocalDate issueDate;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	public Certificate(String id, String name, String organization, LocalDate issueDate) {
		super();
		this.id = id;
		this.name = name;
		this.organization = organization;
		this.issueDate = issueDate;
	}
	
}
