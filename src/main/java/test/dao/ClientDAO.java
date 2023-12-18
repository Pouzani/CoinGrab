package test.dao;


import test.model.Client;

public interface ClientDAO {
	public void create(Client client) throws DAOException;
    public Client getClientByIdUser(int idUser);
}
