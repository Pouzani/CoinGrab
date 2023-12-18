package test.dao;

import test.model.Profile;

public interface ProfileDAO {
	
	void addClientProfile(Profile profile) throws DAOException;

    boolean updateProfileDescription(Profile profile) throws DAOException;
    
    boolean updateProfilePicture(Profile profile) throws DAOException;

    Profile getProfileByIdClient(int idClient) throws DAOException;
    
	public void deleteProfile(int idProfile) throws DAOException ;

}
