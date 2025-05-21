package com.nepestate.model;

public class AdminModel {
	private int AdminID;
	private String Admin_FirstName;
	private String Admin_LastName;
	private String Admin_Username;
	private String Admin_EmailAddress;
	private String Admin_Password;
	private String Admin_ProfilePicture;
	private String Admin_PhoneNumber;
	
	
	public AdminModel() {}
	
	public AdminModel(String admin_FirstName, String admin_LastName, String admin_Username,
			String admin_EmailAddress, String admin_Password, String admin_ProfilePicture, String admin_PhoneNumber) {
		super();
		
		Admin_FirstName = admin_FirstName;
		Admin_LastName = admin_LastName;
		Admin_Username = admin_Username;
		Admin_EmailAddress = admin_EmailAddress;
		Admin_Password = admin_Password;
		Admin_ProfilePicture = admin_ProfilePicture;
		Admin_PhoneNumber = admin_PhoneNumber;
	}
	public String getAdmin_FirstName() {
		return Admin_FirstName;
	}
	public void setAdmin_FirstName(String admin_FirstName) {
		Admin_FirstName = admin_FirstName;
	}
	public String getAdmin_LastName() {
		return Admin_LastName;
	}
	public void setAdmin_LastName(String admin_LastName) {
		Admin_LastName = admin_LastName;
	}
	public String getAdmin_Username() {
		return Admin_Username;
	}
	public void setAdmin_Username(String admin_Username) {
		Admin_Username = admin_Username;
	}
	public String getAdmin_EmailAddress() {
		return Admin_EmailAddress;
	}
	public void setAdmin_EmailAddress(String admin_EmailAddress) {
		Admin_EmailAddress = admin_EmailAddress;
	}
	public String getAdmin_Password() {
		return Admin_Password;
	}
	public void setAdmin_Password(String admin_Password) {
		Admin_Password = admin_Password;
	}
	public String getAdmin_ProfilePicture() {
		return Admin_ProfilePicture;
	}
	public void setAdmin_ProfilePicture(String admin_ProfilePicture) {
		Admin_ProfilePicture = admin_ProfilePicture;
	}
	public String getAdmin_PhoneNumber() {
		return Admin_PhoneNumber;
	}
	public void setAdmin_PhoneNumber(String admin_PhoneNumber) {
		Admin_PhoneNumber = admin_PhoneNumber;
	}
	
	public AdminModel(String username, String password) {
		this.Admin_Username = username;
		this.Admin_Password = password;
	}

	public int getAdminID() {
		return AdminID;
	}

	public void setAdminID(int adminID) {
		AdminID = adminID;
	}
}
