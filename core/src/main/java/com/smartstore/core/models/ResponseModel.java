package com.smartstore.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ResponseModel {

    @ChildResource
    private String imagePath;

    @ChildResource
    private String successStatus;

    @ChildResource
    private String errorStatus;

    @ChildResource
    private String warningStatus;

    @ChildResource
    private String alert;

    @ChildResource
    private String message;


    @ChildResource
    private String reDirectTitle;

    @ChildResource
    private String reDirectLink;

    @ChildResource
    private String statuses;

    @ChildResource
    private String rightButtonName;

    @ChildResource
    private String leftButtonName;

    public String getRightButtonName() {
        return rightButtonName;
    }

    public String getLeftButtonName() {
        return leftButtonName;
    }

    public String getStatuses() {
        return statuses;
    }

    public void setStatuses(String statuses) {
        this.statuses = statuses;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getSuccessStatus() {
        return successStatus;
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public String getWarningStatus() {
        return warningStatus;
    }

    public String getAlert() {
        return alert;
    }

    public String getMessage() {
        return message.replaceAll("\\r|\\n", "");
    }

    public String getReDirectTitle() {
        return reDirectTitle;
    }

    public String getReDirectLink() {
        return reDirectLink;
    }
}
