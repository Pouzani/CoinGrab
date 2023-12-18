package test.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import test.model.Profile;

public class ProfileDaoImpl implements ProfileDAO{
	
	private DAOFactory daoFactory;
	
	public ProfileDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	private static Profile map( ResultSet resultSet ) throws SQLException{
		Profile profile = new Profile();	
		profile.setDescription(resultSet.getString( "description" ));
		profile.setPicture(resultSet.getString( "picture" ));
		return profile;
	}
	
	public static PreparedStatement initRequestPrepare( Connection connexion, String sql, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}

	@Override
	public void addClientProfile(Profile profile)  throws DAOException{
		final String SQL_INSERT_PROFILE = "INSERT INTO profile (idClient, description, picture) VALUES (?,?,?)";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_INSERT_PROFILE, profile.getIdClient(), profile.getDescription(), profile.getPicture() );
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
                throw new DAOException( "Échec de la création profile, aucune ligne ajoutée dans la table." );
            }
            
	    } catch ( SQLException e ) {
	        System.out.println("probleme create profile"+e);
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
	}

	@Override
	public boolean updateProfileDescription(Profile profile)  throws DAOException{
		final String SQL_UPDATE_DESCRIPTION = "UPDATE profile SET description = ? WHERE idProfile = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    boolean rs = false;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	    	
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_UPDATE_DESCRIPTION, profile.getDescription(), profile.getIdProfile());
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
                rs = true;
                
            } 

	        
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return rs;
	}

	@Override
	public boolean updateProfilePicture(Profile profile)  throws DAOException{
		final String SQL_UPDATE_PICTURE = "UPDATE profile SET picture = ? WHERE idProfile = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    boolean rs = false;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	    	
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_UPDATE_PICTURE, profile.getPicture(), profile.getIdProfile());
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
                rs = true;
                
            } 
	        
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return rs;
	}

	@Override
	public Profile getProfileByIdClient(int idClient)  throws DAOException{
		final String SQL_SELECT_PAR_NOM = "SELECT * FROM user WHERE idClient = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Profile profile = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, idClient );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	profile = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return profile;
	}

	@Override
	public void deleteProfile(int idProfile) throws DAOException{
        System.out.println("Nms7o had profile ");

		// TODO Auto-generated method stub
		final String SQL_DELETE_PAR_ID = "DELETE FROM profile WHERE idProfile = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        System.out.println("kaytmse7");
	        preparedStatement = initRequestPrepare( connexion, SQL_DELETE_PAR_ID, idProfile );
	        int statut = preparedStatement.executeUpdate();
	        preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du profile, aucune ligne supprimée de la table." );
            } 
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		
	}

}
