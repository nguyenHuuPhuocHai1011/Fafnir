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
@Table(name = "experiences")
public class Experience implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -98599551215229492L;

	@Id
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "from_date")
	private LocalDate fromDate;
	
	@Column(name = "to_date")
	private LocalDate toDate;
	
	@Column(name = "description")
	private String description;
	
	@ToString.Exclude
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private Position position;
	
	@ToString.Exclude
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	
	public Experience(String companyName, LocalDate fromDate, LocalDate toDate, String description) {
		super();
		this.companyName = companyName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.description = description;
	}
	
}
