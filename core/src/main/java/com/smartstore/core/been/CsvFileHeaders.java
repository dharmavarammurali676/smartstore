package com.smartstore.core.been;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.sling.models.annotations.Default;

public class CsvFileHeaders {

@JsonProperty("sNumber")
@Default(values = "SNO")
    private String sNumber;
    @JsonProperty("fullName")
    @Default(values = "FullName")
    private String fullName;
    @JsonProperty("role")
    @Default(values = "Role")
    private String role;
    @JsonProperty("company")
    @Default(values = "Company")
    private String company;
    @JsonProperty("phoneNumber")
    @Default(values = "PhoneNumber")
    private String phoneNumber;

    public String getsNumber() {
        return sNumber;
    }

    public void setsNumber(String sNumber) {
        this.sNumber = sNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CsvFileHeaders{" +
                "sNumber='" + sNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role='" + role + '\'' +
                ", company='" + company + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
