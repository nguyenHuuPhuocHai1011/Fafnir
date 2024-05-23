package entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "ingredients")
public class Ingredient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2106860380778299080L;

	@Id
	@Column(name = "ingredient_id")
	private String id;
	
	@Column(name = "ingredient_name")
	private String name;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "quantity")
	private double quantity;
	
	@Column(name = "manufacturing_date")
	private LocalDate manufacturingDate;
	
	@Column(name = "expiry_date")
	private LocalDate expiryDate;
	
	@Column(name = "supplier_name")
	private String supplierName;
	
	public Ingredient(String id, String name, String unit, double price, double quantity, LocalDate manufacturingDate,
			LocalDate expiryDate, String supplierName) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.quantity = quantity;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
		this.supplierName = supplierName;
	}
}
