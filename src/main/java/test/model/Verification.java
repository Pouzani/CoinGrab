package test.model;

public class Verification extends Profile{

	private Integer idVerification, idProfile;
	private boolean verificationBadge;
	private String scannedIdentity, scannedPicture;
	
	
	//Constructor
	public Verification() {
		super();
	}
	
	public Verification(Integer idVerification, Integer idProfile, boolean verificationBadge, String scannedIdentity,
			String scannedPicture) {
		super();
		this.idVerification = idVerification;
		this.idProfile = idProfile;
		this.verificationBadge = verificationBadge;
		this.scannedIdentity = scannedIdentity;
		this.scannedPicture = scannedPicture;
	}
	
	//Getters & Setters	
	public Integer getIdVerification() {
		return idVerification;
	}

	public void setIdVerification(Integer idVerification) {
		this.idVerification = idVerification;
	}
	public Integer getIdProfile() {
		return idProfile;
	}
	public void setIdProfile(Integer idProfile) {
		this.idProfile = idProfile;
	}
	public boolean isVerificationBadge() {
		return verificationBadge;
	}
	public void setVerificationBadge(boolean verificationBadge) {
		this.verificationBadge = verificationBadge;
	}
	public String getScannedIdentity() {
		return scannedIdentity;
	}
	public void setScannedIdentity(String scannedIdentity) {
		this.scannedIdentity = scannedIdentity;
	}
	public String getScannedPicture() {
		return scannedPicture;
	}
	public void setScannedPicture(String scannedPicture) {
		this.scannedPicture = scannedPicture;
	}
	
	

}
