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
@Table(name = "beverages")
public class Beverage extends Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7194972758084850396L;

	@Enumerated(EnumType.STRING)
	@Column(name = "size")
	private Size size;
	
    @Column(name = "supplier_name")
	private String supplierName;

	public Beverage(String id, String name, double price, String description, boolean onSpecial, Size size,
			String supplierName) {
		super(id, name, price, description, onSpecial);
		this.size = size;
		this.supplierName = supplierName;
	}
    
}
