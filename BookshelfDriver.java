/**
 * @author Christian McClellan
 *
 */
public class BookshelfDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Bookshelf fantasy = new Bookshelf("Fantasy", 5);
		Book fBook;
		Book capacityTest;
		
		try {
			
			fBook = new Book("Dragon Charm", "Graham", "Edwards", "Fantasy", 506, false);
			
			fantasy.addBook(fBook);
			System.out.println("Normal Book Test Success");
			
			System.out.println(fantasy.removeBook(fBook));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Normal Book Test Failed");
		}
		
		try {
			capacityTest = new Book("Capacity Test", "Christian", "McClellan", "Fantasy", 10000, false); //nothing stops you from making a book this big at the moment
			fantasy.addBook(capacityTest);
			
			System.out.println(fantasy.removeBook(capacityTest));
			System.out.println("Capacity Test Failed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Capacity Test Success");
		}
		
		try {
			Book genreTest = new Book("Genre Test", "Christian", "McClellan", "Action", 20, false);
			System.out.println("Genre Test Failed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Genre Test Success");
		}

	}

}
