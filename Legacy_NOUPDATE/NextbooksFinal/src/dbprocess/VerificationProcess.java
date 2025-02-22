package dbprocess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerificationProcess extends DatabaseProcess {
    /* name of the database */
    private static String dbname = "cmpt370group04";
    
    private VerificationProcess() throws SQLException {
    	super();
    }
    
    /**
     * Check to see if a user is registered in the system
     * @param	userName	the login name to be searched for
     * @param	password	the password to be searched for
     * @return 	true if username and password are registered to a user; false otherwise
     */
    public boolean checkLogin(String username, String password) throws SQLException {
        if(username==null || password==null) {
            return false;
        }
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM tblUser WHERE UserName=\"" + username + "\" AND PassWord=\"" + password + "\";");
        if(rs.first()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkNameAvailable(String username) throws SQLException {
    	if(username==null) {
            return false;
        }
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT UserName FROM tblUser");
        if(rs.first()) {
            return true;	//name available
        } else {
            return false;	//name unavailable
        }
    }

}
