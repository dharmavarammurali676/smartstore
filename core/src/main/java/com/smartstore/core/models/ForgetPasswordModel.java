package com.smartstore.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ForgetPasswordModel {


    @ChildResource
    private String email;

    @ChildResource
    private String newPassword;

    @ChildResource
    private String confirmPassword;

    @ChildResource
    private String oldPassword;

    @ChildResource
    private String newPass;

    @ChildResource
    private String confirmPass;

    @ChildResource
    private String webAbout;
    @ChildResource
    private String webDescription;

    @ChildResource
    private String leftHeading;

    @ChildResource
    private String leftMessage;

    @ChildResource
    private String reDirectButton;

    @ChildResource
    private String regLink;
    public String getEmail() {
        return email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPass() {
        return newPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public String getWebAbout() {
        return webAbout;
    }

    public String getWebDescription() {
        return webDescription;
    }

    public String getLeftHeading() {
        return leftHeading;
    }

    public String getLeftMessage() {
        return leftMessage;
    }

    public String getReDirectButton() {
        return reDirectButton;
    }

    public String getRegLink() {
        return regLink;
    }
}
