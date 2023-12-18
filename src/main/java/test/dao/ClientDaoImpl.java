package test.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import test.model.Client;

public class ClientDaoImpl implements ClientDAO{
	
	private DAOFactory          daoFactory;
	
	
	
	public ClientDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	private static Client map( ResultSet resultSet ) throws SQLException {
		Client client = new Client();	
		client.setProfession(resultSet.getString( "profession" ));
		return client;
	}
	
	public static PreparedStatement initRequestPrepare( Connection connexion, String sql, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}
	
	@Override
	public void create(Client client) throws DAOException {
		final String SQL_SELECT_PAR_NOM = "INSERT INTO client (idUser, profession) VALUES (?,?)";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, client.getIdUser(), client.getProfession() );
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du client, aucune ligne ajoutée dans la table." );
            }
            
	    } catch ( SQLException e ) {
	        System.out.println("probleme create Client"+e);
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
	}
	
    @Override
    public Client getClientByIdUser(int idUser) {
		final String SQL_SELECT_PAR_NOM = "Select cl.* from user as u, client as cl where u.idUser = ?;";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Client client = null;
        try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, idUser );
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	        	client = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return client;
    }

}
