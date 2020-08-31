package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface {

	public int signUp(User user) throws ClassNotFoundException, SQLException, Exception{
		// TODO Auto-generated method stub
		String email = user.getEmail();
		String password = user.getPassword();
			
		String sql = "insert into project_week5_1(EMAIL,PASSWORD)VALUES(?,?)";
		
		
		PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
		
		st.setString(1, email);
		st.setString(2, password);
		
		int result;
		return result = st.executeUpdate();
		
	}
	
	public boolean loginUser(User user)throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String email = user.getEmail();
		String password = user.getPassword();
		try {
	
		ConnectionManager con = new ConnectionManager();
		Statement st = con.getConnection().createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM project_week5_1");
		
		while(rs.next()) {
			if(email.equals(rs.getString("EMAIL")) && password.equals(rs.getString("PASSWORD"))) {
				con.getConnection().close();
				return true;
			}

		}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

}
