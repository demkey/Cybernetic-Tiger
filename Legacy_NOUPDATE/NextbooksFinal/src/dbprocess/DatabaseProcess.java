package dbprocess;
import java.util.LinkedList;
import java.sql.*;

import obj.*;

/**
 * @author cml220
 */
public class DatabaseProcess {
    /* name of the database */
    private static String dbname = "cmpt370group04";
    /* connection to the database */
    protected Connection conn;

    private static DatabaseProcess instance;

    /**
     * Constructor
     */
    protected DatabaseProcess() throws SQLException {
        initDatabaseConnection();
    }

    /**
     * Singleton pattern DatabaseProcess init
     * @return	single instance of DatabaseProcess
     */
    public static synchronized DatabaseProcess getInstance() {
        if (instance == null) {
            try {
                instance = new DatabaseProcess();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * Initialize a connection to the database
     * @postcond	connection to the database initialized
     */
    private void initDatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url="jdbc:mysql://papyrus.usask.ca/" + dbname + "?user=cmpt370group04&password=74jv1vde";
            conn=DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the image url associated with a single user
     * @param user	the user whose assoc. img is to be found
     * @return	the url (as a string) if found, otherwise null
     */
    public User getUserInfo(String user) throws SQLException {
    	GetterProcess db = (GetterProcess) getInstance();
        return db.getUserInfo(user);
    }

    /**
     * Get a list of all the books in the catalogue
     * @return	a list of all the books currently in the catalogue (books table)
     */
    public LinkedList<Book> getCatalogue() throws SQLException {
    	GetterProcess db = (GetterProcess) getInstance();
        return db.getCatalogue();
    }

    /**
     * Find books by title (pattern match)
     * @param	title	title to search for
     * @return	a list of books that are exactly or contain the substring in their title field
     */
    public LinkedList<Book> getBookByTitle(String title) throws SQLException {
    	GetterProcess db = (GetterProcess) getInstance();
        return db.getBookByTitle(title);
    }

    /**
     * Find books by author (pattern match)
     * @param	author	author to search for
     * @return	a list of books that are exactly or contain the substring in their author field
     */
    public LinkedList<Book> getBookByAuthor(String author) throws SQLException {
    	GetterProcess db = (GetterProcess) getInstance();
        return db.getBookByAuthor(author);
    }

    /**
     * Find a book by isbn (exact)
     * @param	ISBN	ISBN to search for
     * @return	the book with the given ISBN if found; otherwise null
     */
    public Book getBookByIsbn(String isbn) throws SQLException {
    	GetterProcess db = (GetterProcess) getInstance();
        return db.getBookByIsbn(isbn);
    }

    /**
     * Find a book by ID (exact)
     * @param	ID	ID of the book
     * @return	the book with the given ID if found; otherwise null
     */
    public Book getBookById(int id) throws SQLException {
    	GetterProcess db = (GetterProcess) getInstance();
        return db.getBookById(id);
    }

    /**
     * Get a list of books being rented by a user
     * @param	u	the user to find the books being rented by
     * @return	a list of books being rented by the given user
     */
    public LinkedList<Book> getBooksByUser(User u) throws SQLException {
    	GetterProcess db = (GetterProcess) getInstance();
        return db.getBooksByUser(u);
    }

    /**
     * Add a book to a user's rentals
     * @param	u	the user/renter
     * @param 	b	the book to be rented
     */
    public void addBookToUser(Book b, User u) throws SQLException {
    	InsertionProcess db = (InsertionProcess) getInstance();
        db.addBookToUser(b, u);
    }

    /**
     * Add a book to the catalogue
     * @param	b	book to be added to the catalogue
     * @postcond	book has been added to the catalogue if it was not present already
     */
    public void addBookToCatalogue(Book b) throws SQLException {
    	InsertionProcess db = (InsertionProcess) getInstance();
        db.addBookToCatalogue(b);
    }

    /**
     * Remove a single book from the catalogue as well as all rentals
     * @param bookid	the id of the book to be removed
     */
    public void removeBookFromCatalogue(int bookid) throws SQLException {
    	RemovalProcess db = (RemovalProcess) getInstance();
        db.removeBookFromCatalogue(bookid);
    }

    /**
     * Create a new user for the system
     * @param	u	user to be added to the system
     * @postcond	user has been added to the system
     * @return 		user ID if successful; or -1 (username in use)
     */
    public int createUser(User u) throws SQLException {
    	InsertionProcess db = (InsertionProcess) getInstance();
        return db.createUser(u);
    }

    /**
     * Check to see if a user is registered in the system
     * @param	userName	the login name to be searched for
     * @param	password	the password to be searched for
     * @return 	true if username and password are registered to a user; false otherwise
     */
    public boolean checkLogin(String username, String password) throws SQLException {
    	VerificationProcess db = (VerificationProcess) getInstance();
        return db.checkLogin(username, password);
    }
    
    /**
     * Check to see if a username is in use in the system
     * @param	userName	the login name to be searched for
     * @return 	true if username is available; false otherwise
     */
    public boolean checkNameAvailable(String username) throws SQLException {
    	VerificationProcess db = (VerificationProcess) getInstance();
        return db.checkNameAvailable(username);
    }

    /**
     * Update a user's profile image
     * @param	url			the url of the profile image
     * @param	username	the username of the user
     */
    public void editUserProfilePic(String url, String username)	throws SQLException {
    	ModificationProcess db = (ModificationProcess) getInstance();
        db.editUserProfilePic(url, username);
    }

    /**
     * Get the admin status of a user
     * @param	username	the username of the user
     */
    public String getAdminStatus(String username) throws SQLException {
    	GetterProcess db = (GetterProcess) getInstance();
        return db.getAdminStatus(username);
    }

    /**
     * Get pertinent book info
     * @param id	of the book to be gotten
     * @return	the info as a string
     */
    public String getBookInfo(int id) throws SQLException {
    	GetterProcess db = (GetterProcess) getInstance();
        return db.getBookInfo(id);
    }
}