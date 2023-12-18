package test.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import test.model.Verification;

public class VerificationDaoImpl implements VerificationDAO{

	private DAOFactory          daoFactory;
	
	public VerificationDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	private static Verification map( ResultSet resultSet ) throws SQLException{
		Verification verification = new Verification();	
		verification.setVerificationBadge(resultSet.getBoolean( "verificationBadge" ));
		verification.setScannedIdentity(resultSet.getString( "scannedIdentity" ));
		verification.setScannedPicture(resultSet.getString( "scannedPicture" ));
		return verification;
	}
	
	public static PreparedStatement initRequestPrepare( Connection connexion, String sql, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}
	@Override
	public void addVerification(Verification verification) throws DAOException{
		final String SQL_INSERT_VERIFICATION = "INSERT INTO verification (idProfil, verificationBadge, scannedIdentity, scannedPicture) VALUES (?,?,?,?)";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_INSERT_VERIFICATION, verification.getIdClient(), verification.getDescription(), verification.getPicture(), verification.getPicture() );
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
                throw new DAOException( "Échec de l'ajout de la vérification, aucune ligne ajoutée dans la table." );
            }
            
	    } catch ( SQLException e ) {
	        System.out.println("probleme create vérification"+e);
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		
	}

	@Override
	public Verification getVerificationById(int idVerification) throws DAOException {
		final String SQL_SELECT_PAR_VERIFICATION = "SELECT * FROM verification WHERE idVerification = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Verification verification = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_VERIFICATION, idVerification );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	verification = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return verification;
	}
	@Override
	public Verification getVerificationByIdProfil(int idProfil)  throws DAOException{
		final String SQL_SELECT_PAR_VERIFICATION = "SELECT * FROM verification WHERE idProfil = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Verification verification = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_VERIFICATION, idProfil );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	verification = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return verification;
	}

	@Override
	public Verification getVerificationByIdClient(int idClient) throws DAOException{
		final String SQL_SELECT_PAR_ClIENT = "SELECT v.* FROM verification as v, profile as p WHERE v.idProfile = p.idProfile AND p.idClient = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Verification verification = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_ClIENT, idClient );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	verification = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return verification;
	}
	
	@Override
	public boolean uploadDocuments(Verification verification) throws DAOException {
		final String SQL_UPDATE_SCANS = "UPDATE verification SET scannedIdentity = ?, scannedPicture = ? WHERE idVerification = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    boolean rs = false;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	    	
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_UPDATE_SCANS, verification.getScannedIdentity(), verification.getScannedPicture(), verification.getIdVerification() );
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
	public void verifyProfile(int idVerification) throws DAOException {
		final String SQL_UPDATE_APPROVEMENT = "UPDATE verification SET verificationBadge = true WHERE idVerification = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	    	
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_UPDATE_APPROVEMENT, idVerification );
	        preparedStatement.executeUpdate();
	        
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

	}


}
