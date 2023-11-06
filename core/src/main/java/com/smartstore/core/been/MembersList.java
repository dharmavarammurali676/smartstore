package com.smartstore.core.been;

public class MembersList {

    private String userName;
    private String email;
    private String Password;
    private String registerDate;
    private String contactNumber;

    public MembersList(String userName, String email, String password, String registerDate, String contactNumber) {
        this.userName = userName;
        this.email = email;
        Password = password;
        this.registerDate = registerDate;
        this.contactNumber = contactNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "MembersList{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", Password='" + Password + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
