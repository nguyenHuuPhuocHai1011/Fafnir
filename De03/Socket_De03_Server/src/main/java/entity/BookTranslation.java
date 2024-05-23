package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "book_translations")
public class BookTranslation extends Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4585449953793514989L;

	@Column(name = "translate_name")
	private String translateName;
	
	@Column(name = "language")
	private String language;

	public BookTranslation(String translateName, String language) {
		super();
		this.translateName = translateName;
		this.language = language;
	}
}
