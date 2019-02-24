package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.CustomerBean;
import model.CustomerDAO;
public class CustomerDAOJdbc implements CustomerDAO {
	private DataSource dataSource;
	public CustomerDAOJdbc() {
		try {
			Context ctx = new InitialContext();
			this.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String SELECT_BY_CUSTID = "select * from customer where custid=?";
	@Override
	public CustomerBean findByPrimaryKey(String custid) {
		CustomerBean result = null;
		ResultSet rset = null;
		try(//Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_BY_CUSTID);) {

			stmt.setString(1, custid);			
			rset = stmt.executeQuery();
			if(rset.next()) {
				result = new CustomerBean();
				result.setCustid(rset.getString("custid"));
				result.setPassword(rset.getBytes("password"));
				result.setEmail(rset.getString("email"));
				result.setBirth(rset.getDate("birth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rset!=null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
		}
		return result;
	}

	private static final String UPDATE =
			"update customer set password=?, email=?, birth=? where custid=?";
	@Override
	public boolean update(byte[] password, String email, java.util.Date birth, String custid) {
		try(//Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE);	) {
			
			stmt.setBytes(1, password);
			stmt.setString(2, email);
			if(birth!=null) {
				long date1 = birth.getTime();
				java.sql.Date date2 = new java.sql.Date(date1);
				stmt.setDate(3, date2);
			} else {
				stmt.setDate(3, null);
			}
			stmt.setString(4, custid);
			int i = stmt.executeUpdate();
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
