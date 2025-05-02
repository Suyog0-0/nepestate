package com.nepestate.model;

import java.util.Date;

public class PropertyModel {
    private int PropertyID;
    private String Property_Title;
    private String Property_Type;
    private float Property_Price;
    private float Property_Area;
    private String Property_Address;
    private String Property_City;
    private String Property_Municipality;
    private int Property_Ward;
    private String Property_Status;
    private String Property_Description;
    private String Property_Amentities;
    private Date Property_DateAdded;
    private String Property_Photos;
    
    // Default constructor
    public PropertyModel() {}
    
    // Full constructor
    public PropertyModel(int propertyID, String property_Title, String property_Type, float property_Price,
            float property_Area, String property_Address, String property_City, String property_Municipality,
            int property_Ward, String property_Status, String property_Description, String property_Amentities,
            Date property_DateAdded, String property_Photos) {
        PropertyID = propertyID;
        Property_Title = property_Title;
        Property_Type = property_Type;
        Property_Price = property_Price;
        Property_Area = property_Area;
        Property_Address = property_Address;
        Property_City = property_City;
        Property_Municipality = property_Municipality;
        Property_Ward = property_Ward;
        Property_Status = property_Status;
        Property_Description = property_Description;
        Property_Amentities = property_Amentities;
        Property_DateAdded = property_DateAdded;
        Property_Photos = property_Photos;
    }
    
    // Constructor without ID (useful for insertion)
    public PropertyModel(String property_Title, String property_Type, float property_Price,
            float property_Area, String property_Address, String property_City, String property_Municipality,
            int property_Ward, String property_Status, String property_Description, String property_Amentities,
            Date property_DateAdded, String property_Photos) {
        Property_Title = property_Title;
        Property_Type = property_Type;
        Property_Price = property_Price;
        Property_Area = property_Area;
        Property_Address = property_Address;
        Property_City = property_City;
        Property_Municipality = property_Municipality;
        Property_Ward = property_Ward;
        Property_Status = property_Status;
        Property_Description = property_Description;
        Property_Amentities = property_Amentities;
        Property_DateAdded = property_DateAdded;
        Property_Photos = property_Photos;
    }
    public PropertyModel(String property_Title, String property_Type, float property_Price,
            float property_Area, String property_Address, String property_City, 
             String property_Description,String property_Status, String property_Amentities,
            Date property_DateAdded, String property_Photos) {
        Property_Title = property_Title;
        Property_Type = property_Type;
        Property_Price = property_Price;
        Property_Area = property_Area;
        Property_Address = property_Address;
        Property_City = property_City;
        Property_Status = property_Status;
        Property_Description = property_Description;
        Property_Amentities = property_Amentities;
        Property_DateAdded = property_DateAdded;
        Property_Photos = property_Photos;
    }

    public int getPropertyID() {
        return PropertyID;
    }

    public void setPropertyID(int propertyID) {
        PropertyID = propertyID;
    }

    public String getProperty_Title() {
        return Property_Title;
    }

    public void setProperty_Title(String property_Title) {
        Property_Title = property_Title;
    }

    public String getProperty_Type() {
        return Property_Type;
    }

    public void setProperty_Type(String property_Type) {
        Property_Type = property_Type;
    }

    public float getProperty_Price() {
        return Property_Price;
    }

    public void setProperty_Price(float property_Price) {
        Property_Price = property_Price;
    }

    public float getProperty_Area() {
        return Property_Area;
    }

    public void setProperty_Area(float property_Area) {
        Property_Area = property_Area;
    }

    public String getProperty_Address() {
        return Property_Address;
    }

    public void setProperty_Address(String property_Address) {
        Property_Address = property_Address;
    }

    public String getProperty_City() {
        return Property_City;
    }

    public void setProperty_City(String property_City) {
        Property_City = property_City;
    }

    public String getProperty_Municipality() {
        return Property_Municipality;
    }

    public void setProperty_Municipality(String property_Municipality) {
        Property_Municipality = property_Municipality;
    }

    public int getProperty_Ward() {
        return Property_Ward;
    }

    public void setProperty_Ward(int property_Ward) {
        Property_Ward = property_Ward;
    }

    public String getProperty_Status() {
        return Property_Status;
    }

    public void setProperty_Status(String property_Status) {
        Property_Status = property_Status;
    }

    public String getProperty_Description() {
        return Property_Description;
    }

    public void setProperty_Description(String property_Description) {
        Property_Description = property_Description;
    }

    public String getProperty_Amentities() {
        return Property_Amentities;
    }

    public void setProperty_Amentities(String property_Amentities) {
        Property_Amentities = property_Amentities;
    }

    public Date getProperty_DateAdded() {
        return Property_DateAdded;
    }

    public void setProperty_DateAdded(Date property_DateAdded) {
        Property_DateAdded = property_DateAdded;
    }

    public String getProperty_Photos() {
        return Property_Photos;
    }

    public void setProperty_Photos(String property_Photos) {
        Property_Photos = property_Photos;
    }
}