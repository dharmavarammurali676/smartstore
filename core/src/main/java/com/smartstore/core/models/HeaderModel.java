package com.smartstore.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.smartstore.core.constants.Constants;
import com.smartstore.core.servlets.LoginAuthenticationServlet;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Reference;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Session;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeaderModel {


    @ChildResource
    private String headerPropertyCSS;
    @Inject
    private Page currentPage;

    @SlingObject
    private SlingHttpServletRequest request;
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
    private String members;


    @ChildResource
    private String leftButtonName;

    @ChildResource
    private String currentUserType;
    @ChildResource
    private String specialMemberImage;
    @ChildResource
    private String memberImage;
    @ChildResource
    private String unknownImage;

    @ChildResource
    private String previousPage;


    @PostConstruct
    public void init() {
        String targetNodeName = "timeLinesItems";
        Session session = request.getResourceResolver().adaptTo(Session.class);
        String pagePath = currentPage.getPath();
        String currentPageName = currentPage.getName();
        String prevPage = currentPage.getParent().getPath();
        if (!prevPage.equals("/content/smartstore")){
            previousPage = prevPage;
        } else {
            previousPage = currentPage.getPath();
        }
        List<String> validPageNames = Arrays.asList("booking-root-page", "authentication_root-page");

        if (validPageNames.contains(currentPageName)) {
            headerPropertyCSS = checkCurrentPageName(currentPageName);
        } else {
            headerPropertyCSS = "background-container";
        }
    }
    private String checkCurrentPageName(String currentPageName){

        switch (currentPageName){
            case "authentication_root-page":
                return "background-container_root";
            case "booking-root-page":
                return "background-container_root";
            case "h2":
                return "payment-method";
            case "h3":
                return "default-cmp-header3";
            case "h4":
                return "default-cmp-header4";
            case "h5":
                return "default-cmp-header5";
            case "h6":
                return "default-cmp-header6";

            case "strong":
                return "default-cmp-strong";
            default:
                return StringUtils.EMPTY;
        }
    }


    public String getMembers() {
        String loggedInUser = (String) request.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            return loggedInUser;
        } else {
            return "";
        }
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public String getHeaderPropertyCSS() {
        return headerPropertyCSS;
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

    public String getCurrentUserType() {
        return currentUserType;
    }

    public String getSpecialMemberImage() {
        return specialMemberImage;
    }

    public String getMemberImage() {
        return memberImage;
    }

    public String getUnknownImage() {
        return unknownImage;
    }
}
