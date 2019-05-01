import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Christian McClellan
 *
 */
@SuppressWarnings("serial")
public class BookshelfFrame extends JFrame {

	// global input/output fields for Book class
	JTextField title, authorFirstName, authorLastName, genre, pages;
	JCheckBox hardcover;
	JLabel output;

	// global bookshelf objects to store books
	Bookshelf fantasy = new Bookshelf("Fantasy", 5);
	Bookshelf scifi = new Bookshelf("Scifi", 5);
	Bookshelf fiction = new Bookshelf("Fiction", 5);
	Bookshelf nonfiction = new Bookshelf("Nonfiction", 5);

	/**
	 * GUI JFrame constructor
	 */
	public BookshelfFrame() {
		JPanel panel = new JPanel();
		JButton submit = new JButton("Submit");
		JButton clear = new JButton("Clear");
		title = new JTextField();
		JLabel titlePrompt = new JLabel("Title: ");
		authorFirstName = new JTextField();
		JLabel authorFPrompt = new JLabel("Author First Name: ");
		authorLastName = new JTextField();
		JLabel authorLPrompt = new JLabel("Author Last Name: ");
		genre = new JTextField();
		JLabel genrePrompt = new JLabel("Genre: ");
		pages = new JTextField();
		JLabel pagesPrompt = new JLabel("Pages: ");
		hardcover = new JCheckBox();
		JLabel hardcoverPrompt = new JLabel("Hardcover: ");
		output = new JLabel();

		ActionListener s = new submitListener();
		submit.addActionListener(s);
		ActionListener c = new clearListener();
		clear.addActionListener(c);

		panel.add(titlePrompt);
		panel.add(title);
		panel.add(authorFPrompt);
		panel.add(authorFirstName);
		panel.add(authorLPrompt);
		panel.add(authorLastName);
		panel.add(genrePrompt);
		panel.add(genre);
		panel.add(pagesPrompt);
		panel.add(pages);
		panel.add(hardcoverPrompt);
		panel.add(hardcover);
		panel.add(output);
		panel.add(submit);
		panel.add(clear);

		add(panel);

		setSize(500, 250);
		setTitle("Bookshelf");

		Dimension w = new Dimension(180, 20);
		titlePrompt.setPreferredSize(w);
		title.setPreferredSize(w);
		authorFPrompt.setPreferredSize(w);
		authorFirstName.setPreferredSize(w);
		authorLPrompt.setPreferredSize(w);
		authorLastName.setPreferredSize(w);
		genrePrompt.setPreferredSize(w);
		genre.setPreferredSize(w);
		pagesPrompt.setPreferredSize(w);
		pages.setPreferredSize(w);
		hardcoverPrompt.setPreferredSize(w);
		hardcover.setPreferredSize(w);
		output.setPreferredSize(new Dimension(450, 20));

	}

	/**
	 * @author Christian McClellan
	 * Event listener for the Submit button
	 */
	public class submitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				Book toSubmit = new Book(title.getText(), authorFirstName.getText(), authorLastName.getText(),
						genre.getText(), Integer.parseInt(pages.getText()), hardcover.isSelected());
				
				String bookGenre = toSubmit.getGenre();	
				boolean submitted = false;
				
				switch(bookGenre) {
				case "fantasy":
					submitted = fantasy.addBook(toSubmit);
					break;
				case "scifi":
					submitted = scifi.addBook(toSubmit);
					break;
				case "fiction":
					submitted = fiction.addBook(toSubmit);
					break;
				case "nonfiction":
					submitted = nonfiction.addBook(toSubmit);
					break;
				}
				
				if (submitted) {
					output.setText(toSubmit.getTitle() + " added to " + bookGenre);
				}
				else {
					output.setText("Adding book to shelf failed");
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				output.setText("Invalid Book");
				
			}

		}
	}

	/**
	 * @author Christian McClellan
	 * Event listener for the Clear button
	 */
	public class clearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			title.setText("");
			authorFirstName.setText("");
			authorLastName.setText("");
			genre.setText("");
			pages.setText("");
			hardcover.setSelected(false);
			output.setText("");

		}
	}
}
