package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "foods")
public class Food extends Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8694027314826087942L;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private Type type;
	
	@Column(name = "preparation_time")
	private int preparationTime;
	
	@Column(name = "serving_time")
	private int servingTime;
	
	public Food(String id, String name, double price, String description, boolean onSpecial, Type type,
			int preparationTime, int servingTime) {
		super(id, name, price, description, onSpecial);
		this.type = type;
		this.preparationTime = preparationTime;
		this.servingTime = servingTime;
	}

}
