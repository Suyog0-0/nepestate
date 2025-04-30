package com.nepestate.model;


public class CustomerModel {
	private String Customer_FirstName;
	private String Customer_LastName;
	private String Customer_Username;
	private String Customer_EmailAddress;
	private String Customer_Password;
	private String Customer_ProfilePicture;
	private String Customer_DoB;
	private String Customer_PhoneNumber;
	
	public CustomerModel() {}
	
	public CustomerModel(String cust_FirstName, String cust_LastName, String cust_Username, String cust_EmailAddress,
			String cust_Password, String cust_ProfilePicture, String cust_DoB, String cust_PhoneNumber) {
		super();
		Customer_FirstName = cust_FirstName;
		Customer_LastName = cust_LastName;
		Customer_Username = cust_Username;
		Customer_EmailAddress = cust_EmailAddress;
		Customer_Password = cust_Password;
		Customer_ProfilePicture = cust_ProfilePicture;
		Customer_DoB = cust_DoB;
		Customer_PhoneNumber = cust_PhoneNumber;
	}

	public String getCustomer_FirstName() {
		return Customer_FirstName;
	}

	public void setCustomer_FirstName(String cust_FirstName) {
		Customer_FirstName = cust_FirstName;
	}

	public String getCustomer_LastName() {
		return Customer_LastName;
	}

	public void setCustomer_LastName(String cust_LastName) {
		Customer_LastName = cust_LastName;
	}

	public String getCustomer_Username() {
		return Customer_Username;
	}

	public void setCustomer_Username(String cust_Username) {
		Customer_Username = cust_Username;
	}

	public String getCustomer_EmailAddress() {
		return Customer_EmailAddress;
	}

	public void setCustomer_EmailAddress(String cust_EmailAddress) {
		Customer_EmailAddress = cust_EmailAddress;
	}

	public String getCustomer_Password() {
		return Customer_Password;
	}

	public void setCustomer_Password(String cust_Password) {
		Customer_Password = cust_Password;
	}

	public String getCustomer_ProfilePicture() {
		return Customer_ProfilePicture;
	}

	public void setCustomer_ProfilePicture(String cust_ProfilePicture) {
		Customer_ProfilePicture = cust_ProfilePicture;
	}

	public String getCustomer_DoB() {
		return Customer_DoB;
	}

	public void setCustomer_DoB(String cust_DoB) {
		Customer_DoB = cust_DoB;
	}

	public String getCustomer_PhoneNumber() {
		return Customer_PhoneNumber;
	}

	public void setCustomer_PhoneNumber(String cust_PhoneNumber) {
		Customer_PhoneNumber = cust_PhoneNumber;
	}
	
	
	public CustomerModel(String username,String password) 
	{
		this.Customer_Username=username;
		this.Customer_Password=password;
	}
	
}
