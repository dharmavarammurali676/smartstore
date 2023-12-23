package com.smartstore.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LoginModel {

    @ChildResource
    private String webDescription;

    @ChildResource
    private String webAbout;

    @ChildResource
    private String message;

    @ChildResource
    private String heading;

    @ChildResource
    private String loginMessage;

    @ChildResource
    private String leftHeading;

    @ChildResource
    private String leftMessage;

    @ChildResource
    private String reDirectButton;

    @ChildResource
    private String regLink;

    @ChildResource
    private String forgetHeading;

    @ChildResource
    private String forgetButton;
    @ChildResource
    private String headerLogo;

    public String getHeaderLogo() {
        return headerLogo;
    }


    public String getWebDescription() {
        return webDescription;
    }

    public String getWebAbout() {
        return webAbout;
    }

    public String getMessage() {
        return message;
    }

    public String getHeading() {
        return heading;
    }

    public String getLoginMessage() {
        return loginMessage;
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

    public String getForgetHeading() {
        return forgetHeading;
    }

    public String getForgetButton() {
        return forgetButton;
    }
}
