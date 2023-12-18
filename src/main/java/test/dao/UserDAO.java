package test.dao;

import java.util.List;



import test.model.User;


public interface UserDAO {
	void create( User user ) throws DAOException;
	boolean update (User user)  throws DAOException;
	User getUserById(int idUser) throws DAOException;
	User getUserByEmail(String email) throws DAOException;
	List<User> searchUserByName (String name) throws DAOException;
	boolean validate(String email, String password) throws DAOException;
	boolean emailExist(String email)throws DAOException;
	void delete (int id)  throws DAOException;
	void updatePassword(String email, String currentPassword, String newPassword)  throws DAOException;
	List<User> getAllUsers();
	boolean userNameExist(String userName)throws DAOException;
	public boolean updatePic (User user) throws DAOException;
}
