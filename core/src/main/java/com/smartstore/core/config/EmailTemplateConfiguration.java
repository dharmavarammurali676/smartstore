package com.smartstore.core.config;

public interface EmailTemplateConfiguration {
    String getSmartStoreSuccessEmail();

    String getSmartStoreFailureEmail();

    String getLoginSpecialMemberSuccessAlertEmail();

    String getLoginSpecialMemberFailureAlertEmail();

    String getRegistrationSuccessEmail();

    String getLoginOtpEmail();

    String getProductBookingSuccessEmail();

    String getProductBookingDeleteSuccessEmail();

    String getForgetPasswordUpdateEmail();

    String getCsvFileGeneratedAlertEmail();
}
