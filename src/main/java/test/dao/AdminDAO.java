package test.dao;


import test.model.Admin;

public interface AdminDAO {
	void create( Admin admin ) throws DAOException;
	
    public Admin getAdminByIdUser(int idUser);
}
