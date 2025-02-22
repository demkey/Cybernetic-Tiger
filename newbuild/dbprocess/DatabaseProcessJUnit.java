package dbprocess;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedList;
import org.apache.log4j.Logger;
import model.Book;
import model.User;

import org.junit.Test;

public class DatabaseProcessJUnit {

	private DatabaseProcess db;
	Logger log = Logger.getLogger(DatabaseProcessJUnit.class);
	/**
	 * Test cases for User based database operations.
	 */
	@Test
	public void testCreateUser() {
		log.debug("testCreateUser Entered.");
		User u = new User("Test", false, "1234@google.se");
		User u2 = new User(null, false, null);
		User u3 = new User("", false, "");
		try {
			int res = db.createUser(u);
			int res2 = db.createUser(u2);
			int res3 = db.createUser(u3);
			assertTrue(res == 0 || res == -1);	//if test run more than once u1 might exist
	        assertTrue(res2 == -2);					//the null case
	        assertTrue(res3 == -3);					//the blank case
			assertTrue(db.checkLogin(u.getUserName(), u.getPassword()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetUserInfo() {
		log.debug("testGetUserInfo Entered.");
		User u = new User("Test", false, "1234@google.se");
		try {
			db.createUser(u);
			User res = db.getUserInfo("Test");
			assertEquals("User Retrival", u.getPassword(), res.getPassword());
			assertEquals("User Retrival", u.getUserName(), res.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGetAdminStatus() {
		log.debug("testGetAdminStatus Entered.");
		User u = new User("Test", false, "1234@google.se");
		try {
			User res = db.getUserInfo("Test");
			assertEquals("isAdmin Status", u.isAdmin, res.isAdmin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test Cases for Book based database operation
	 */
	@Test
	public void testAddBookToCatalogue() {
		log.debug("testAddBookToCatalogue Entered.");
		Book b = new Book("THISISAUNIQUESTRING","THISISAUNIQUESTRING",(float) 1.10,"THISISAUNIQUESTRING", "2309580932902385","THISISAUNIQUESTRING","THISISAUNIQUESTRING");
		try {
			db.addBookToCatalogue(b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddBookToUser() {
		log.debug("testAddBookToUser Entered.");
		User u = new User("Test", false, "1234@google.se");
		Book b = new Book("THISISAUNIQUESTRING","THISISAUNIQUESTRING",(float) 1.10,"THISISAUNIQUESTRING", "2309580932902385","THISISAUNIQUESTRING","THISISAUNIQUESTRING");
		try {
			db.addBookToUser(b, u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetCatalogue() {
		log.debug("testGetCatalogue Entered.");
		try {
			LinkedList<Book> booklist= db.getCatalogue();
			assertTrue(!booklist.isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetBookByTitle() {
		log.debug("testGetBookByTitle Entered.");
		LinkedList<Book> booklist;
		try {
			booklist = db.getBookByTitle("THISISAUNIQUESTRING");
			assertTrue(!booklist.isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testGetBookByAuthor() {
		log.debug("testGetBookByAuthor Entered.");
		LinkedList<Book> booklist;
		try {
			booklist = db.getBookByAuthor("THISISAUNIQUESTRING");
			assertTrue(!booklist.isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetBookByIsbn() {
		log.debug("testGetBookByIsbn Entered.");
		Book b = new Book("THISISAUNIQUESTRING","THISISAUNIQUESTRING",(float) 1.10,"THISISAUNIQUESTRING", "2309580932902385","THISISAUNIQUESTRING","THISISAUNIQUESTRING");
		Book found;
		try {
			found = db.getBookByIsbn("2309580932902385");
			assertTrue(found != null);
            assertTrue(found.equals(b));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetBookById() {
		log.debug("testGetBookById Entered.");
		//Unsure of the usefulness of searching by id since object class lacks support for ID
		//and ISBN is unique to specific book (International)
		/*Book b = new Book("THISISAUNIQUESTRING","THISISAUNIQUESTRING",(float) 1.10,"THISISAUNIQUESTRING", "2309580932902385","THISISAUNIQUESTRING","THISISAUNIQUESTRING");
		Book found;
		try {
			found = db.getBookByIsbn("2309580932902385");
			assertEquals("2309580932902385", found.getBookISBN(), b.getBookISBN());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	@Test
	public void testGetBooksByUser() {
		log.debug("testGetBooksByUser Entered.");
		LinkedList<Book> booklist;
		User u = new User("Test", false, "1234@google.se");
		try {
			booklist = db.getBooksByUser(u);
			assertTrue(!booklist.isEmpty());
			assertTrue(booklist.size() == 1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	@Test
	public void testRemoveBookFromCatalogue() {
		log.debug("testRemoveBookFromCatalogue Entered.");
		Book b;
		try {
			b = db.getBookByIsbn("2309580932902385");
			db.removeBookFromCatalogue(Integer.parseInt(b.getBookISBN()));
			b = db.getBookByIsbn("2309580932902385");
			assertTrue(b == null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  Test cases for VerificationProcess database operations.
	 */
	@Test
	public void testCheckLogin() {
		log.debug("testCheckLogin Entered.");
		boolean res;
		try {
			res = db.checkLogin("Test", "1234@google.se");
			assertTrue(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCheckNameAvailable() {
		log.debug("testCheckNameAvailable Entered.");
		boolean res;
		try {
			res = db.checkNameAvailable("Test2");
			assertTrue(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testEditUserProfilePic() {
		log.debug("testEditUserProfilePic Entered.");
		//Unsure of how a test could be made.
	}

	

	@Test
	public void testGetBookInfo() {
		log.debug("testGetBookInfo Entered.");
		// Unsure why/how book id is being used)
	}

}
