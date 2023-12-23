package com.smartstore.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RegistrationModel {

    @ChildResource
    private String webDescription;

    @ChildResource
    private String webAbout;
    @ChildResource
    private String heading;

    @ChildResource
    private String loginMessage;

    @ChildResource
    private String leftHeading;

    @ChildResource
    private String reDirectButton;

    @ChildResource
    private String loginLink;
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


    public String getHeading() {
        return heading;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public String getLeftHeading() {
        return leftHeading;
    }

    public String getReDirectButton() {
        return reDirectButton;
    }

    public String getLoginLink() {
        return loginLink;
    }
}
