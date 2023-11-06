package com.smartstore.core.config.impl;


import com.smartstore.core.config.EmailTemplateConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : Dharmavaram Murali,
 * @Date : 10-03-2023,
 * @Time : 10:58
 */

@Component(service = EmailTemplateConfiguration.class,
        immediate = true)

@Designate(ocd = EmailTemplateConfigurationImpl.WorkFlowApproverConfiguration.class)
public class EmailTemplateConfigurationImpl implements EmailTemplateConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateConfigurationImpl.class);

    private String smartStoreSuccessEmail;

    private String smartStoreFailureEmail;

    private String loginSpecialMemberSuccessAlertEmail;

    private String loginSpecialMemberFailureAlertEmail;

    private String registrationSuccessEmail;

    private String loginOtpEmail;

    private String productBookingSuccessEmail;

    private String productBookingDeleteSuccessEmail;

    private String forgetPasswordUpdateEmail;

    private String csvFileGeneratedAlertEmail;


    @Activate
    @Modified
    protected void Activate(final WorkFlowApproverConfiguration configuration) {

        this.smartStoreSuccessEmail = configuration.getSmartStoreSuccessEmail();
        this.smartStoreFailureEmail = configuration.getSmartStoreFailureEmail();
        this.loginSpecialMemberSuccessAlertEmail = configuration.getLoginSpecialMemberSuccessAlertEmail();
        this.loginSpecialMemberFailureAlertEmail = configuration.getLoginSpecialMemberFailureAlertEmail();
        this.registrationSuccessEmail = configuration.getRegistrationSuccessEmail();
        this.loginOtpEmail = configuration.getLoginOtpEmail();
        this.productBookingSuccessEmail = configuration.getProductBookingSuccessEmail();
        this.productBookingDeleteSuccessEmail = configuration.getProductBookingDeleteSuccessEmail();
        this.forgetPasswordUpdateEmail = configuration.getForgetPasswordUpdateEmail();
        this.csvFileGeneratedAlertEmail = configuration.getCSVFileGeneratedAlertEmail();


    }

    @ObjectClassDefinition(name = "SmartStore Email Templates Configuration", description = "Here, have email templates")
    public @interface WorkFlowApproverConfiguration {

        /**
         * @return
         */
        @AttributeDefinition(
                name = "SmartStore Success Email",
                description = "This is smartstore success email",
                type = AttributeType.STRING)
        public String getSmartStoreSuccessEmail();

        @AttributeDefinition(
                name = "SmartStore Failure Email",
                description = "This is smartstore failure email",
                type = AttributeType.STRING)
        public String getSmartStoreFailureEmail();

        @AttributeDefinition(name = "Login SpecialMember Success Alert Email",
                description = "This is login specialmember success alert email",
                type = AttributeType.STRING)
        public String getLoginSpecialMemberSuccessAlertEmail();

        @AttributeDefinition(name = "Login SpecialMember Failure Alert Email",
                description = "This is login specialmember failure alert email",
                type = AttributeType.STRING)
        public String getLoginSpecialMemberFailureAlertEmail();

        @AttributeDefinition(
                name = "Registration Success Email",
                description = "This is registration success email",
                type = AttributeType.STRING)
        public String getRegistrationSuccessEmail();

        @AttributeDefinition(name = "Login Otp Email",
                description = "This is login otp email",
                type = AttributeType.STRING)
        public String getLoginOtpEmail();

        @AttributeDefinition(
                name = "Product Booking Success Email",
                description = "This is Product Booking Success Email",
                type = AttributeType.STRING)
        public String getProductBookingSuccessEmail();

        @AttributeDefinition(name = "Product Booking Delete Success Email",
                description = "This is product booking delete success email",
                type = AttributeType.STRING)
        public String getProductBookingDeleteSuccessEmail();

        @AttributeDefinition(name = "Forget Password Update Email",
                description = "This is forget password update email",
                type = AttributeType.STRING)
        public String getForgetPasswordUpdateEmail();

        @AttributeDefinition(name = "CSV File Generated Alert Email",
                description = "This is csv file generated alert email",
                type = AttributeType.STRING)
        public String getCSVFileGeneratedAlertEmail();
    }

    @Override
    public String getSmartStoreSuccessEmail() {
        return smartStoreSuccessEmail;
    }
    @Override
    public String getSmartStoreFailureEmail() {
        return smartStoreFailureEmail;
    }
    @Override
    public String getLoginSpecialMemberSuccessAlertEmail() {
        return loginSpecialMemberSuccessAlertEmail;
    }
    @Override
    public String getLoginSpecialMemberFailureAlertEmail() {
        return loginSpecialMemberFailureAlertEmail;
    }
    @Override
    public String getRegistrationSuccessEmail() {
        return registrationSuccessEmail;
    }
    @Override
    public String getLoginOtpEmail() {
        return loginOtpEmail;
    }
    @Override
    public String getProductBookingSuccessEmail() {
        return productBookingSuccessEmail;
    }
    @Override
    public String getProductBookingDeleteSuccessEmail() {
        return productBookingDeleteSuccessEmail;
    }
    @Override
    public String getForgetPasswordUpdateEmail() {
        return forgetPasswordUpdateEmail;
    }
    @Override
    public String getCsvFileGeneratedAlertEmail() {
        return csvFileGeneratedAlertEmail;
    }
}
