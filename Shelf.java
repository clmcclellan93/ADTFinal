import java.util.Vector;

/**
 * @author Christian McClellan
 *
 */
public class Shelf {

	private final int MAX_WIDTH = 5000;
	private int currentWidth;
	private Vector<Book> shelf;
	
	
	/**
	 * default shelf constructor
	 */
	public Shelf() {
		this.currentWidth = 0;
		shelf = new Vector<Book>();
	}
	/**
	 * @return the currentWidth
	 */
	public int getCurrentWidth() {
		return currentWidth;
	}
	
	/**
	 * @param book the book to add to the shelf
	 * @param index the location on the shelf to add the book
	 * @return true if insertion succeeds
	 */
	public boolean insert(Book book) {
		if (isEmpty() && book.getPages() <= MAX_WIDTH) {
			shelf.add(book);
			return true;
		}
		else if ((currentWidth + book.getPages()) <= MAX_WIDTH) { //if the book fits on the shelf
			boolean found = false; // records whether the loop has found the location to add the book, if false, book goes at the end
			for (int i = 0; i < shelf.size(); i++) { // check each element until you find the first book that goes after the one being added
				if ((book.getAuthorLastName().compareToIgnoreCase(shelf.get(i).getAuthorLastName()) == 0
						&& book.getTitle().compareToIgnoreCase(shelf.get(i).getTitle()) < 0)
						|| book.getAuthorLastName().compareToIgnoreCase(shelf.get(i).getAuthorLastName()) < 0) {
					found = true; //the placement for the book has been found
					shelf.add(i, book); //add the book, and shift all books after that index to the right one
					break; //break out of the loop
				}
			}
			if (!found) { //book fits on the shelf, so if placement is not found, it must go at the end
				shelf.add(book);
			}
			return true;
		}
		else return false;
	}
	
	/**
	 * @param book the book to remove
	 * @return the removed book
	 */
	public Book remove(Book book) {
		return shelf.remove(shelf.indexOf(book));
	}
	
	/**
	 * @param index the index to peek
	 * @return the book at index
	 */
	public Book peek(int index) {
		return shelf.get(index);
	}
	
	/**
	 * @return true if shelf is empty
	 */
	public boolean isEmpty() {
		return shelf.isEmpty();
	}
	
	/**
	 * @param book the book to search for
	 * @return true if the book is found
	 */
	public boolean search(Book book) {
		if (shelf.contains(book)) return true;
		else return false;
	}
	
	/**
	 * @return the first book on the shelf
	 */
	public Book getFirst() {
		return shelf.firstElement();
	}
	
	/**
	 * @return the last book on the shelf
	 */
	public Book getLast() {
		return shelf.lastElement();
	}
}
