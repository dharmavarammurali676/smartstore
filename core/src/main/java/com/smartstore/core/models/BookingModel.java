package com.smartstore.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BookingModel {


    @ChildResource
    private String imagePath;

    @ChildResource
    private String heading;

    @ChildResource
    private String message;

    @ChildResource
    private String terms;

    @ChildResource
    private String button;

    @ChildResource
    private String paymentButton;

    @ChildResource
    private String policies;

    public String getImagePath() {
        return imagePath;
    }

    public String getHeading() {
        return heading;
    }

    public String getMessage() {
        return message;
    }

    public String getTerms() {
        return terms;
    }

    public String getButton() {
        return button;
    }

    public String getPolicies() {
        return policies;
    }

    public String getPaymentButton() {
        return paymentButton;
    }
}
