package dao.imp;

import java.util.List;
import java.util.Map;

import connection.ConnectionMSSQL;
import dao.DAO;
import entity.Food;
import entity.Item;
import jakarta.persistence.EntityManager;

public class daoimp implements DAO{
	
	private static EntityManager entityManager = ConnectionMSSQL.getEntityManager();
	
//	a) (1.5 điểm) Thêm một món ăn mới. Trong đó, mã số của món phải bắt đầu là F và theo sau ít 
//	nhất 3 ký số. 
//	+ addFood (food: Food) : boolean
	@Override
	public boolean addFood(Food food) {
		if (food.getId().matches("F\\d{3,}")) {
			try {
				entityManager.getTransaction().begin();
				entityManager.persist(food);
				entityManager.getTransaction().commit();
				return true;
			} catch (Exception e) {
				entityManager.getTransaction().isActive();
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

//	b) (1.5 điểm) Liệt kê danh sách mặt hàng là món đặt biệt của nhà hàng mà có sử dụng nguyên 
//	liệu được nhập từ nhà cung cấp nào đó khi biết tên nhà cung cấp (tìm tương đối, không phân biệt 
//	chữ thường hoa). 
//	+ listItems (supplierName: String) : List<Item> 
	@Override
	public List<Item> listItems(String supplierName) {
		String query = "SELECT i FROM Item i JOIN i.ingredients ing WHERE i.onSpecial = true AND ing.supplierName LIKE :supplierName";
		List<Item> list = entityManager.createQuery(query, Item.class)
				.setParameter("supplierName", "%" + supplierName + "%").getResultList();
		return list;
	}
	
//	c) (1.5 điểm) Tính giá gốc của từng món ăn sau khi chế biết thành phẩm. Kết quả sắp xếp giảm 
//	dần theo đơn giá gốc.  
//	Trong đó: Giá gốc món ăn = (số lượng nguyên liệu * đơn giá nguyên liệu) + (thời gian chuẩn 
//	bị + thời gian phục vụ) * 0.2$ 
//	+ public listFoodAndCost(): Map<Food, Double>
	@Override
	public Map<Food, Double> listFoodAndCost() {
		String query = "SELECT f, (SUM(ing.price * ing.quantity) + (f.preparationTime + f.servingTime) * 0.2) AS totalCost "
				+ "FROM Food f JOIN f.ingredients ing "
				+ "GROUP BY f "
				+ "ORDER BY totalCost DESC ";
		List<?> list = entityManager.createQuery(query).getResultList();
		Map<Food, Double> map = list.stream().map(o -> (Object[]) o).map(a -> Map.entry((Food) a[0], (Double) a[1]))
				.collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return map;
		
	}

}
