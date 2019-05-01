/**
 * @author Christian McClellan
 *
 */
public class Book {
	
	private String title;
	private String authorFirstName;
	private String authorLastName;
	private String genre;
	private int pages;
	private boolean hardcover;

	/**
	 * @param title
	 * @param authorFirstName
	 * @param authorLastName
	 * @param genre
	 * @param pages
	 * @param hardcover
	 */
	public Book(String title, String authorFirstName, String authorLastName, String genre, int pages, boolean hardcover) {
		setTitle(title);
		setAuthorFirstName(authorFirstName);
		setAuthorLastName(authorLastName);
		setGenre(genre);
		setPages(pages);
		setHardcover(hardcover);
		
		this.pages /= 2; //width of pages is only half page count
		
		//this is to account roughly for the extra "pages" worth of width added to the book by the cover
		if (this.isHardcover()) {
			this.pages += 40;
		}
		else {
			this.pages += 4;
		}
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author's first name
	 */
	public String getAuthorFirstName() {
		return authorFirstName;
	}

	/**
	 * @param authorFirstName the first name to set
	 */
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}
	
	/**
	 * @return the author's last name
	 */
	public String getAuthorLastName() {
		return authorLastName;
	}
	
	/**
	 * @param authorLastName the last name to set
	 */
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		if (genre.equalsIgnoreCase("fantasy") || genre.equalsIgnoreCase("fiction") || genre.equalsIgnoreCase("nonfiction")) {
			this.genre = genre.toLowerCase();
		}
		else throw new IllegalArgumentException("Invalid Genre");
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	/**
	 * @return the hardcover
	 */
	public boolean isHardcover() {
		return hardcover;
	}

	/**
	 * @param hardcover the hardcover to set
	 */
	public void setHardcover(boolean hardcover) {
		this.hardcover = hardcover;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [title=" + title + ", authorFirstName=" + authorFirstName + ", authorLastName=" + authorLastName
				+ ", genre=" + genre + ", pages=" + (pages * 2) + ", hardcover=" + hardcover + "]";
	}
}
