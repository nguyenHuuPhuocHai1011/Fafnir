package entity;

import java.io.Serializable;

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
@Table(name = "reviews")
public class Reviews implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2378554013402265683L;

	@Column(name = "rating")
	private int rating;
	
	@Column(name = "comment")
	private String comment;
	
	@ToString.Exclude
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person person;
	
	@ToString.Exclude
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ISBN")
	private Book book;
	
	public Reviews(int rating, String comment) {
		super();
		this.rating = rating;
		this.comment = comment;
	}
	
}
