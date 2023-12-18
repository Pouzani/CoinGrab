package test.dao;

import test.model.Verification;

public interface VerificationDAO {

	void addVerification(Verification verification) throws DAOException;
	
	Verification getVerificationById(int idVerification) throws DAOException;
	
	Verification getVerificationByIdProfil(int idProfil)  throws DAOException;
	
	Verification getVerificationByIdClient(int idClient) throws DAOException;

	boolean uploadDocuments(Verification verification) throws DAOException;
	
	void verifyProfile(int idVerification) throws DAOException;

}
