package com.nepestate.model;

import java.util.Date;

public class CustomerModel {
	private String Cust_FirstName;
	private String Cust_LastName;
	private String Cust_Username;
	private String Cust_EmailAddress;
	private String Cust_Password;
	private String Cust_ProfilePicture;
	private String Cust_DoB;
	private String Cust_PhoneNumber;
	
	public CustomerModel() {}
	
	public CustomerModel(String cust_FirstName, String cust_LastName, String cust_Username, String cust_EmailAddress,
			String cust_Password, String cust_ProfilePicture, String cust_DoB, String cust_PhoneNumber) {
		super();
		Cust_FirstName = cust_FirstName;
		Cust_LastName = cust_LastName;
		Cust_Username = cust_Username;
		Cust_EmailAddress = cust_EmailAddress;
		Cust_Password = cust_Password;
		Cust_ProfilePicture = cust_ProfilePicture;
		Cust_DoB = cust_DoB;
		Cust_PhoneNumber = cust_PhoneNumber;
	}

	public String getCust_FirstName() {
		return Cust_FirstName;
	}

	public void setCust_FirstName(String cust_FirstName) {
		Cust_FirstName = cust_FirstName;
	}

	public String getCust_LastName() {
		return Cust_LastName;
	}

	public void setCust_LastName(String cust_LastName) {
		Cust_LastName = cust_LastName;
	}

	public String getCust_Username() {
		return Cust_Username;
	}

	public void setCust_Username(String cust_Username) {
		Cust_Username = cust_Username;
	}

	public String getCust_EmailAddress() {
		return Cust_EmailAddress;
	}

	public void setCust_EmailAddress(String cust_EmailAddress) {
		Cust_EmailAddress = cust_EmailAddress;
	}

	public String getCust_Password() {
		return Cust_Password;
	}

	public void setCust_Password(String cust_Password) {
		Cust_Password = cust_Password;
	}

	public String getCust_ProfilePicture() {
		return Cust_ProfilePicture;
	}

	public void setCust_ProfilePicture(String cust_ProfilePicture) {
		Cust_ProfilePicture = cust_ProfilePicture;
	}

	public String getCust_DoB() {
		return Cust_DoB;
	}

	public void setCust_DoB(String cust_DoB) {
		Cust_DoB = cust_DoB;
	}

	public String getCust_PhoneNumber() {
		return Cust_PhoneNumber;
	}

	public void setCust_PhoneNumber(String cust_PhoneNumber) {
		Cust_PhoneNumber = cust_PhoneNumber;
	}
	
	
	public CustomerModel(String username,String password) 
	{
		this.Cust_Username=username;
		this.Cust_Password=password;
	}
	
}
