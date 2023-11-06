package com.smartstore.core.been;

public class MemberInformation {
    private String username;
    private String contactNumber;
    private String registrationDate;
    private String category;
    private String email;

    public MemberInformation() {
    }

    public MemberInformation(String username, String contactNumber, String registrationDate, String category, String email) {
        this.username = username;
        this.contactNumber = contactNumber;
        this.registrationDate = registrationDate;
        this.category = category;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
