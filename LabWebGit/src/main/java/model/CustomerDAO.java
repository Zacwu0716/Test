package model;

public interface CustomerDAO {

	CustomerBean findByPrimaryKey(String custid);

	boolean update(byte[] password, String email, java.util.Date birth, String custid);

}