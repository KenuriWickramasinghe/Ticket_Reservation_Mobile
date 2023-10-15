package com.example.mobile_travelreservation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Traveler {
    @SerializedName("Id")
    @Expose
    private String Id;

    @SerializedName("NIC")
    @Expose
    private String NIC;

    @SerializedName("FirstName")
    @Expose
    private String FirstName;

    @SerializedName("LastName")
    @Expose
    private String LastName ;

    @SerializedName("Title")
    @Expose
    private String Title;

    @SerializedName("Email")
    @Expose
    private String Email ;

    @SerializedName("PhoneNumber")
    @Expose
    private String PhoneNumber ;

    @SerializedName("Address")
    @Expose
    private String Address ;

    @SerializedName("Password")
    @Expose
    private String Password;

    public Traveler(){
    }
    public Traveler(String id, String NIC, String firstName, String lastName, String title, String email, String phoneNumber, String address, String password) {
        Id = id;
        this.NIC = NIC;
        FirstName = firstName;
        LastName = lastName;
        Title = title;
        Email = email;
        PhoneNumber = phoneNumber;
        Address = address;
        Password = password;
    }

    public String getId() {
        return Id;
    }

    public String getNIC() {
        return NIC;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getTitle() {
        return Title;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public String getPassword() {
        return Password;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
