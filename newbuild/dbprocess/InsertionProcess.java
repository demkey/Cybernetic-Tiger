/*
 * @author Colin Larson
 * TO BE COMPLETED
 */

package dbprocess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Book;
import model.User;

public class InsertionProcess extends DatabaseProcess {
	/* name of the database */
    private static String dbname = "cmpt370group04";
    
    private InsertionProcess() throws SQLException {
    	super();
    }
    
	/**
     * Add a book to a user's rentals
     * @param	u	the user/renter
     * @param 	b	the book to be rented
     */
    /*
    public void addBookToUser(Book b, User u, int rentalID) throws SQLException {
        if(b==null || u==null) {
            return;
        }
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM tblBook WHERE BookID=" + b.getBookID() + ";");
        if(rs.first()) {
        	stmt.execute("INSERT INTO tblBookRental(RentalID, BookID) VALUES (\"" + rentalID + "\"," + b.getBookID() + ");");
        }
    }
    */
    
    /**
     * Add a book to the catalogue
     * @param	b	book to be added to the catalogue
     * @postcond	book has been added to the catalogue if it was not present already
     */
    /*
    public void addBookToCatalogue(Book b) throws SQLException {
        if(b==null) {
            return;
        }
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM tblBook WHERE ISBN=\"" + b.getBookISBN() + "\";");
        if(rs.first()) {
            return;    //book with this isbn already exists
        } else {
        	stmt.execute("INSERT INTO tblBook (Title,Author,Price,Url,ISBN,BookID,picURL,Description) VALUES (\"" 
        			+ b.getBookTitle() + 		"\",\"" + b.getBookAuthor() + "\"," + b.getBookPrice() 
        			+ ",\"" + b.getBookPdfURL() + "\",\"" + b.getBookISBN() + "\"," + b.getBookID() 		
        			+ ",\"" + b.getBookImg() + "\",\"" + b.getBookDescription() + "\");");
        	rs=stmt.executeQuery("SELECT * FROM tblBook WHERE ISBN=\"" + b.getBookISBN() +"\";");
            rs.first();
        }
    }
    */
    
    /**
     * Create a new user for the system
     * @param	u	user to be added to the system
     * @postcond	user has been added to the system
     * @return 		user ID if successful; or -1 (username in use)
     */
    public int createUser(User u) throws SQLException {
        if(u==null) {
            return -1;
        }
        if(stringIsEmpty(u.getUserName()) || stringIsEmpty(u.getPassword())) {
            return -2;
        }
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM tblUser WHERE UserName=\"" + u.getUserName() + "\";");
        if(rs.first()) {
            return -1;    //value in result set; user already exists
        } else {
        	stmt.execute("INSERT INTO tblUser (UserName, PassWord, IsAdmin) VALUES (\"" + u.getUserName() + "\",\"" + u.getPassword() + "\"," + 0 +");");
        	rs=stmt.executeQuery("SELECT * FROM tblUser WHERE UserName=\"" + u.getUserName() +"\"");
        	stmt.execute("SELECT * FROM tblAccountInfo WHERE UserName=\"" + u.getUserName() +"\"");
        	//DO STUFF HERE
        	//stmt.execute("INSERT INTO tblAccountInfo(UserName, FirstName, LastName, Email, PayInfo) VALUES (\"" 
        	//		+ u.getUserName() + "\",\"" + FirstName + "\",\"" + LastName + "\",\"" + Email + "\",\"" + PayInfo + "\",\"");
        	rs=stmt.executeQuery("SELECT * FROM " + dbname + ".users WHERE users.username=\"" + u.getUserName() +"\";");
            rs.first();
            return 0;
        }
    }    
    
    /**
     * Helper method to validate that a string is not empty, null or ""
     * @param str	the string to check
     * @return		true if "not empty", otherwise false
     */
    private boolean stringIsEmpty(String str) {
        if (str == null || str.isEmpty() || str.equals("")) {
            return true;
        }
        return false;
    }
    
    public void saveShoppingCart() {
    	//DO STUFF HERE
    	//Statement stmt=conn.createStatement();
    	//stmt.execute("INSERT INTO tblCartContent(CartNumber, BookID) VALUES (\"" + r.getRentalNum() + ")");
    }
}
