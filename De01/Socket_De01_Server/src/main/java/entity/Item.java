package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6827914062929915594L;
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "on_special")
	private boolean onSpecial;
	
	@ToString.Exclude
	@ManyToMany
	@JoinTable(
			name = "items_ingredients",
			joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id", nullable = false)
            )
	private List<Ingredient> ingredients;
	
	public Item(String id, String name, double price, String description, boolean onSpecial) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.onSpecial = onSpecial;
	}
	
}
