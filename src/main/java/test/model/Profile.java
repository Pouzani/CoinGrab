package test.model;

public class Profile extends Client{
	
	private Integer idProfile,idClient;
	
	private String description;
	
	private	String picture;

	//Constructor
	public Profile() {
		super();
	}
	
	
	public Profile(Integer idProfile, Integer idClient, String description, String picture) {
		super();
		this.idProfile = idProfile;
		this.idClient = idClient;
		this.description = description;
		this.picture = picture;
	}




	//Getters & Setters
	public Integer getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(Integer idProfile) {
		this.idProfile = idProfile;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
