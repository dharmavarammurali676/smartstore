package com.smartstore.core.models;

import com.smartstore.core.servlets.LoginAuthenticationServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.osgi.service.component.annotations.Reference;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {


    @Reference
    SlingHttpServletRequest request;

    @Reference
    LoginAuthenticationServlet authenticationServlet;

    @ChildResource
    private String logo;

    @ChildResource
    private String heading;

    @ChildResource
    private String registerButton;

    @ChildResource
    private String withPassword;

    @ChildResource
    private String withOtp;

    @ChildResource
    private String rightButton;

    @ChildResource
    private String rightButtonName;


    @ChildResource
    private String leftButton;
    @ChildResource
    private String members ;


    @ChildResource
    private String leftButtonName;


    public String getMembers() {
        String loggedInUser = (String) request.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            return loggedInUser;
        } else {
            return "";
        }
    }
    public String getLogo() {
        return logo;
    }

    public String getHeading() {
        return heading;
    }

    public String getRegisterButton() {
        return registerButton;
    }

    public String getWithPassword() {
        return withPassword;
    }

    public String getWithOtp() {
        return withOtp;
    }

    public String getRightButton() {
        return rightButton;
    }

    public String getRightButtonName() {
        return rightButtonName;
    }

    public String getLeftButton() {
        return leftButton;
    }

    public String getLeftButtonName() {
        return leftButtonName;
    }
}
