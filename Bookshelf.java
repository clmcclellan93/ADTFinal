import java.util.ArrayList;

/**
 * @author Christian McClellan
 *
 */
public class Bookshelf {
	
	private int numShelves;
	private String genre;
	private ArrayList<Shelf> bookshelf;
	
	public Bookshelf(String genre, int numShelves) {
		try {
			setGenre(genre);
			setNumShelves(numShelves);
		} catch (Exception e) {
			throw e;
		}
		bookshelf = new ArrayList<Shelf>();
		
		for (int i = 0; i < numShelves; i++) {
			bookshelf.add(new Shelf());
		}
	}
	
	/**
	 * @return the number of shelves
	 */
	public int getNumShelves() {
		return this.numShelves;
	}
	
	/**
	 * @param numShelves the number of shelves to set
	 * must be between 1 and 9 inclusive
	 */
	public void setNumShelves(int numShelves) {
		if (numShelves > 0 && numShelves < 10) {
			this.numShelves = numShelves;
		}
		else throw new IllegalArgumentException("Invalid number of shelves");
	}
	
	/**
	 * @return the genre
	 */
	public String getGenre() {
		return this.genre;
	}
	
	/**
	 * @param genre the genre to set
	 * only currently allows fiction, nonfiction, or fantasy
	 */
	public void setGenre(String genre) {
		if (genre.equalsIgnoreCase("fantasy") || genre.equalsIgnoreCase("scifi") || genre.equalsIgnoreCase("fiction") || genre.equalsIgnoreCase("nonfiction")) {
			this.genre = genre.toLowerCase();
		}
		else throw new IllegalArgumentException("Invalid Genre");
	}
	
	/**
	 * @param book the book to add
	 * @return true when successfully adding book
	 */
	public boolean addBook(Book book) {
		boolean success = true;
		int shelfIndex = 0;
		Shelf current = bookshelf.get(shelfIndex);
		while (!current.insert(book)) { //while inserting book fails
			if (book.getAuthorLastName().compareToIgnoreCase(current.getLast().getAuthorLastName()) >= 0 //check to see if failure is because book goes on a later shelf
				&& shelfIndex < numShelves - 1) { 														//and that there IS a later shelf
				shelfIndex++; //if book goes on a later shelf, increment shelfIndex
				current = bookshelf.get(shelfIndex); //and set a new current shelf
			}
			else { //otherwise we need to try and make room on the current shelf
				if (!downProp(shelfIndex)) { //attempt to downProp until it is no longer possible
					if (!upProp(shelfIndex)) { //attempt to upProp until it is no longer possible
						success = false;
						break; //if downProp and upProp both fail, break out of the loop, insert is impossible
					}
				}
			}
		}
		return success;
	}
	
	public Book removeBook(Book book) {
		Book removed = null;
		for (Shelf s : bookshelf) {
			if (s.search(book)) {
				removed = s.remove(book);
			}
		}
		return removed;
	}
	
	/**
	 * @param index the index to start at
	 */
	public boolean downProp(int index) {
		
		boolean success = false;
		
		if (index < numShelves - 1) { //if we are not at the last shelf
			Shelf current = bookshelf.get(index); //create an instance of the current shelf
			Shelf next = bookshelf.get(index + 1);//and an instance of the next shelf
			
			if (next.insert(current.getLast())) { //if inserting the last book on current to next succeeds
				current.remove(current.getLast()); //remove last book from current
				success = true; //success
			}
			else if (index < numShelves - 2) { //if we are not yet on the second to last shelf
				success = downProp(index + 1); //try again
			}
			
			return success;						//final success or failure will propagate up through the recursive algorithm
												//failure only occurs if no last book will go to the next shelf, meaning downProp is impossible
		}
		else return success;
	}
	
	public boolean upProp(int index) {
		
		boolean success = false;
		
		if (index > 0) { //if we are not on the first shelf
			Shelf current = bookshelf.get(index); //create instance of current shelf
			Shelf prev = bookshelf.get(index - 1);//create instance of previous shelf
			
			if (prev.insert(current.getFirst())) { //if inserting the first book on current to prev succeeds
				current.remove(current.getFirst());//remove first book from current
				success = true; //success
			}
			else if (index > 1) { //if we are not yet on the second shelf
				success = upProp(index - 1); //try again
			}
			
			return success;			//final success or failure will propagate up through the recursive algorithm
									//failure only occurs of no first book will go to the previous shelf, meaning upProp is impossible
		}
		else return success;
	}
}
