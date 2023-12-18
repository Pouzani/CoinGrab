package test.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import test.model.Admin;


public class AdminDaoImpl implements AdminDAO {
	private static final boolean False = false;
	private DAOFactory daoFactory;
	
	public AdminDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	private static Admin map( ResultSet resultSet ) throws SQLException {
		Admin admin = new Admin();	
		admin.setPseudo(resultSet.getString( "pseudo" ));
		admin.setNationalIdentity(resultSet.getString("nationalIdentity"));
		return admin;
	}
	
	public static PreparedStatement initRequestPrepare( Connection connexion, String sql, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}
	
	@Override
	public void create(Admin admin) throws DAOException {
		final String SQL_SELECT_PAR_NOM = "INSERT INTO admin (idUser, pseudo, nationalIdentity) VALUES (?,?,?)";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, admin.getIdUser(), admin.getPseudo(), admin.getNationalIdentity() );
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du client, aucune ligne ajoutée dans la table." );
            }
            
	    } catch ( SQLException e ) {
	        System.out.println("probleme create admin"+e);
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
	}
	
	
    @Override
    public Admin getAdminByIdUser(int idUser) {
		final String SQL_SELECT_PAR_NOM = "Select ad.* from user as u, admin as ad where u.idUser = ?;";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Admin admin = null;
        try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, idUser );
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	        	admin = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return admin;
    }
}
