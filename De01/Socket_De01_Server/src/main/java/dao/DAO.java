package dao;

import java.util.List;
import java.util.Map;

import entity.Food;
import entity.Item;

public interface DAO {

	public boolean addFood(Food food);
	
	public List<Item> listItems(String supplierName);
	
	public Map<Food, Double> listFoodAndCost();
}
