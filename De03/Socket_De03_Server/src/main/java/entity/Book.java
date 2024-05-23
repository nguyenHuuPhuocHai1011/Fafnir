package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3716840869952326409L;

	@Id
	@Column(name = "ISBN")
	private String ISBN;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "publish_year")
	private int publishYear;
	
	@Column(name = "num_of_pages")
	private int numOfPages;
	
	@Column(name = "price")
	private double price;
	
	@ElementCollection
	@CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
	@Column(name = "author", nullable = false)
	private Set<String> authors;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private List<Reviews> reviews;
	
	public Book(String iSBN, String name, int publishYear, int numOfPages, double price, Set<String> authors,
			Publisher publisher) {
		super();
		ISBN = iSBN;
		this.name = name;
		this.publishYear = publishYear;
		this.numOfPages = numOfPages;
		this.price = price;
		this.authors = authors;
		this.publisher = publisher;
	}
	
}
