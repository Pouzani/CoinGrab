package test.dao;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import test.model.User;

public class UserDaoImpl implements UserDAO {
	
	private DAOFactory          daoFactory;
	
	public UserDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	private static User map( ResultSet resultSet ) throws SQLException {
		User user = new User();
		user.setIdUser( resultSet.getInt( "idUser" ) );
		user.setUserName(resultSet.getString("userName"));
		user.setFirstName(resultSet.getString( "firstName" ));
		user.setLastName(resultSet.getString( "lastName" ));
		user.setEmail(resultSet.getString( "email" ));
		user.setPassword(resultSet.getString( "password" ));
		user.setPhone(resultSet.getString( "phone" ));
		user.setNationality(resultSet.getString( "nationality" ));
		user.setAge(resultSet.getInt( "Age" ));
		user.setRole(resultSet.getString( "role" ));
		if (resultSet.getBytes("picture") != null) {user.setImageData(resultSet.getBytes( "picture" ));}
		return user;
	}
	
	public static PreparedStatement initRequestPrepare( Connection connexion, String sql, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}

	@Override
	public void create(User user) throws DAOException {

		// TODO Auto-generated method stub
		final String SQL_SELECT_PAR_NOM = "INSERT INTO user (userName,firstName, lastName, email, password, phone, nationality, age, role) VALUES (?,?,?,?,?,?,?,?,?)";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM,user.getUserName(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getNationality(), user.getAge(), user.getRole() );
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
	public User getUserById(int idUser) throws DAOException {
		// TODO Auto-generated method stub
		final String SQL_SELECT_PAR_NOM = "SELECT * FROM user WHERE idUser = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    User user = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, idUser );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	user = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws DAOException {
		// TODO Auto-generated method stub
		final String SQL_SELECT_PAR_NOM = "SELECT * FROM user WHERE email = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    User user = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, email );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	user = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return user;
	}

	@Override
	public boolean validate(String email, String password) throws DAOException {
		// TODO Auto-generated method stub
		final String SQL_SELECT_PAR_NOM = "SELECT * FROM user WHERE email=? AND password = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    boolean status = false;
	    
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	    	connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM,email, password );
	        resultSet = preparedStatement.executeQuery();
	        status = resultSet.next();

	        
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
	    

		return status;
	}

	@Override
	public boolean emailExist(String email) throws DAOException {
		// TODO Auto-generated method stub
		final String SQL_SELECT_PAR_NOM = "SELECT * FROM user WHERE email = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    boolean existe = false;
	    
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, email );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	existe=true;
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

	    return existe;
	}

	@Override
	public void delete(int id) throws DAOException {

		// TODO Auto-generated method stub
		final String SQL_DELETE_PAR_ID = "DELETE FROM user WHERE idUser = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        System.out.println("kaytmse7");
	        preparedStatement = initRequestPrepare( connexion, SQL_DELETE_PAR_ID, id );
	        int statut = preparedStatement.executeUpdate();
	        preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du client, aucune ligne supprimée de la table." );
            } 
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
	}

	@Override
	public void updatePassword(String email, String currentPassword, String newPassword) throws DAOException {
		// TODO Auto-generated method stub
		final String SQL_SELECT_PAR_NOM = "UPDATE user SET password = ? WHERE email = ? AND password =?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	  

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	    	
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, newPassword, email, currentPassword);
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
                throw new DAOException( "Échec de la modification de l'user, aucune ligne supprimée de la table." );
            } 
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		final String SQL_SELECT_PAR_NOM = "SELECT * FROM user ";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    User user = null;
		List<User> users = new ArrayList<User>();
        
		try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM );
	        resultSet = preparedStatement.executeQuery();
	        
	        while( resultSet.next() ) {
	        	
	        	user = map( resultSet );
	        	users.add(user);
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return users;
	}

	@Override
	public boolean update (User user)  throws DAOException{
		final String SQL_SELECT_PAR_NOM = "UPDATE user SET email = ?,password = ?, firstName = ?, lastName = ?, age = ?, nationality = ?, phone = ? WHERE idUser = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    boolean rs = false;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	    	
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, user.getEmail(), user.getPassword(),user.getFirstName(),user.getLastName(), user.getAge(), user.getNationality() ,user.getPhone(), user.getIdUser());
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
	
	public boolean updatePic (User user) throws DAOException {
		final String SQL_SELECT_PAR_NOM = "UPDATE user SET picture = ? WHERE idUser = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    boolean rs = false;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	    	
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, user.getImageData(), user.getIdUser());
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
	public List<User> searchUserByName(String name) throws DAOException {
		// TODO Auto-generated method stub
		final String SQL_SELECT_PAR_NOM = "SELECT * FROM user WHERE userName LIKE ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    User user = null;
		List<User> users = new ArrayList<User>();
        
		try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, "%"+name+"%");
	        resultSet = preparedStatement.executeQuery();
	        
	        while( resultSet.next() ) {
	        	
	        	user = map( resultSet );
	        	users.add(user);
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		return users;
	}

	@Override
	public boolean userNameExist(String userName) throws DAOException {
		final String SQL_SELECT_PAR_NOM = "SELECT * FROM user WHERE userName = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    boolean existe = false;
	    
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, userName );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	existe=true;
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

	    return existe;
	}

	
	
	

	


	
}
