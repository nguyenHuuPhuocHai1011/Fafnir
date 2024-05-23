package dao.imp;

import java.util.List;
import java.util.Map;

import connection.ConnectionMSSQL;
import dao.DAO;
import entity.Book;
import jakarta.persistence.EntityManager;

public class daoimp implements DAO{

	private static EntityManager entityManager = ConnectionMSSQL.getEntityManager();

	
	
//	a) (1.5 điểm) Liệt kê danh sách các cuốn sách được viết bởi tác giả nào đó khi biết tên tác giả và 
//	có điểm đánh giá từ mấy sao trở lên. 
//	+ listRatedBooks(author: String, rating: int): List<Book>  
	public List<Book> listRatedBooks(String author, int rating){
		String query = "SELECT b FROM Book b JOIN b.authors ba JOIN b.reviews r WHERE ba LIKE :author AND r.rating >= :rating";
		List<Book> list = entityManager.createQuery(query, Book.class)
							.setParameter("author","%" + author + "%")
							.setParameter("rating", rating)
							.getResultList();
		return list;
	}
	
//	b) (1.5 điểm)  Thống kê số cuốn sách được dịch sang ngôn ngữ khác của từng tác giả, kết quả sắp 
//	xếp theo tên tác giả. 
//	+ countBooksByAuthor(): Map<String, Long> 
	public Map<String, Long> countBooksByAuthor() {
		String query = "SELECT ba , COUNT(bt) FROM BookTranslation bt JOIN bt.authors ba GROUP BY ba ORDER BY ba ASC";
		List<?> list = entityManager.createQuery(query).getResultList();
		Map<String, Long> map = list.stream().map(o -> (Object[]) o)
                .map(a -> Map.entry((String) a[0], (Long) a[1]))
                .collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return map;
	}
	
//	c) (1.5 điểm) Cập nhật thêm một lượt đánh giá cho một cuốn sách, cập nhật thành công nếu cuốn 
//	sách và người đọc đã tồn tại, rating phải từ 1 đến 5 và bình luận không được để trống hay rỗng. 
//	+ updateReviews(isbn: String, readerID: String, rating: int, comment: String): boolen
	
	public static boolean updateReviews(String isbn, String readerID, int rating, String comment) {
		String query = "UPDATE Book b SET b.rating = :rating, b.comment = :comment WHERE b.isbn = :isbn AND b.readerID = :readerID";
		int result = entityManager.createQuery(query).setParameter("rating", rating).setParameter("comment", comment)
				.setParameter("isbn", isbn).setParameter("readerID", readerID).executeUpdate();
		return result > 0;
	}
}
