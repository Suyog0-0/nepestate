// src/com/nepestate/model/PropertyModel.java
package com.nepestate.model;

import java.util.Date;

public class PropertyModel {
    private int PropertyID;
    private String Property_Title;
    private String Property_Type;
    private int Property_Price;
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

    public PropertyModel() {}

    public PropertyModel(int PropertyID, String Property_Title, String Property_Type, int Property_Price,
                         float Property_Area, String Property_Address, String Property_City,
                         String Property_Municipality, int Property_Ward, String Property_Status,
                         String Property_Description, String Property_Amentities, Date Property_DateAdded,
                         String Property_Photos) {
        this.PropertyID = PropertyID;
        this.Property_Title = Property_Title;
        this.Property_Type = Property_Type;
        this.Property_Price = Property_Price;
        this.Property_Area = Property_Area;
        this.Property_Address = Property_Address;
        this.Property_City = Property_City;
        this.Property_Municipality = Property_Municipality;
        this.Property_Ward = Property_Ward;
        this.Property_Status = Property_Status;
        this.Property_Description = Property_Description;
        this.Property_Amentities = Property_Amentities;
        this.Property_DateAdded = Property_DateAdded;
        this.Property_Photos = Property_Photos;
    }

    public int getPropertyID() { return PropertyID; }
    public void setPropertyID(int PropertyID) { this.PropertyID = PropertyID; }

    public String getProperty_Title() { return Property_Title; }
    public void setProperty_Title(String Property_Title) { this.Property_Title = Property_Title; }

    public String getProperty_Type() { return Property_Type; }
    public void setProperty_Type(String Property_Type) { this.Property_Type = Property_Type; }

    public float getProperty_Price() { return Property_Price; }
    public void setProperty_Price(int Property_Price) { this.Property_Price = Property_Price; }

    public float getProperty_Area() { return Property_Area; }
    public void setProperty_Area(float Property_Area) { this.Property_Area = Property_Area; }

    public String getProperty_Address() { return Property_Address; }
    public void setProperty_Address(String Property_Address) { this.Property_Address = Property_Address; }

    public String getProperty_City() { return Property_City; }
    public void setProperty_City(String Property_City) { this.Property_City = Property_City; }

    public String getProperty_Municipality() { return Property_Municipality; }
    public void setProperty_Municipality(String Property_Municipality) { this.Property_Municipality = Property_Municipality; }

    public int getProperty_Ward() { return Property_Ward; }
    public void setProperty_Ward(int Property_Ward) { this.Property_Ward = Property_Ward; }

    public String getProperty_Status() { return Property_Status; }
    public void setProperty_Status(String Property_Status) { this.Property_Status = Property_Status; }

    public String getProperty_Description() { return Property_Description; }
    public void setProperty_Description(String Property_Description) { this.Property_Description = Property_Description; }

    public String getProperty_Amentities() { return Property_Amentities; }
    public void setProperty_Amentities(String Property_Amentities) { this.Property_Amentities = Property_Amentities; }

    public Date getProperty_DateAdded() { return Property_DateAdded; }
    public void setProperty_DateAdded(Date Property_DateAdded) { this.Property_DateAdded = Property_DateAdded; }

    public String getProperty_Photos() { return Property_Photos; }
    public void setProperty_Photos(String Property_Photos) { this.Property_Photos = Property_Photos; }
}