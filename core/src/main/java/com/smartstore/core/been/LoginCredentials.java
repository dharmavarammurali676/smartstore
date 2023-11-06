package com.smartstore.core.been;

public class LoginCredentials {

    private String memberEmail;

    private String memberPassword;

    private String SpecialMemberEmail;
    private String SpecialMemberPassword;

    private String loginEmail;

    private String loginOtp;

    public LoginCredentials() {
    }

    public LoginCredentials(String memberEmail, String memberPassword, String specialMemberEmail, String specialMemberPassword, String loginEmail, String loginOtp) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.SpecialMemberEmail = specialMemberEmail;
        this.SpecialMemberPassword = specialMemberPassword;
        this.loginEmail = loginEmail;
        this.loginOtp = loginOtp;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getSpecialMemberEmail() {
        return SpecialMemberEmail;
    }

    public void setSpecialMemberEmail(String specialMemberEmail) {
        SpecialMemberEmail = specialMemberEmail;
    }

    public String getSpecialMemberPassword() {
        return SpecialMemberPassword;
    }

    public void setSpecialMemberPassword(String specialMemberPassword) {
        SpecialMemberPassword = specialMemberPassword;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getLoginOtp() {
        return loginOtp;
    }

    public void setLoginOtp(String loginOtp) {
        this.loginOtp = loginOtp;
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "memberEmail='" + memberEmail + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", SpecialMemberEmail='" + SpecialMemberEmail + '\'' +
                ", SpecialMemberPassword='" + SpecialMemberPassword + '\'' +
                ", loginEmail='" + loginEmail + '\'' +
                ", loginOtp='" + loginOtp + '\'' +
                '}';
    }
}
