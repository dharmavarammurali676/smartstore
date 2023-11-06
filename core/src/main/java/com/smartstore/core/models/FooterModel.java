package com.smartstore.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {

    @ChildResource
    private String message;
    @ChildResource
    private String headingOne;

    @ChildResource
    private String aboutUs;

    @ChildResource
    private String ourServices;

    @ChildResource
    private String privacyPolicy;

    @ChildResource
    private String headingTwo;

    @ChildResource
    private String faq;

    @ChildResource
    private String shipping;


    @ChildResource
    private String returns;


    @ChildResource
    private String orderStatus;

    @ChildResource
    private String paymentOption;
    @ChildResource
    private String aboutUsLink;

    @ChildResource
    private String ourServicesLink;

    @ChildResource
    private String privacyPolicyLink;

    @ChildResource
    private String faqLink;

    @ChildResource
    private String shippingLink;

    @ChildResource
    private String returnsLink;


    @ChildResource
    private String orderStatusLink;


    @ChildResource
    private String paymentOptionLink;

    @ChildResource
    private String headingThree;

    @ChildResource
    private String order;

    @ChildResource
    private String orderLink;

    @ChildResource
    private String orderDelete;

    @ChildResource
    private String orderDeleteLink;

    @ChildResource
    private String headingFour;

    @ChildResource
    private String headingFive;

    @ChildResource
    private String leftButton;
    @ChildResource
    private String leftButtonName;
    @ChildResource
    private String rightButton;
    @ChildResource
    private String rightButtonName;

    @ChildResource
    private String name;

    @ChildResource
    private String email;

    @ChildResource
    private String phoneNumber;

    @ChildResource
    private String address;

    public String getMessage() {
        return message;
    }

    public String getHeadingOne() {
        return headingOne;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public String getOurServices() {
        return ourServices;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public String getHeadingTwo() {
        return headingTwo;
    }

    public String getFaq() {
        return faq;
    }

    public String getShipping() {
        return shipping;
    }

    public String getReturns() {
        return returns;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public String getHeadingThree() {
        return headingThree;
    }

    public String getOrder() {
        return order;
    }

    public String getOrderLink() {
        return orderLink;
    }

    public String getOrderDelete() {
        return orderDelete;
    }

    public String getOrderDeleteLink() {
        return orderDeleteLink;
    }

    public String getHeadingFour() {
        return headingFour;
    }

    public String getAboutUsLink() {
        return aboutUsLink;
    }

    public String getOurServicesLink() {
        return ourServicesLink;
    }

    public String getPrivacyPolicyLink() {
        return privacyPolicyLink;
    }

    public String getFaqLink() {
        return faqLink;
    }

    public String getShippingLink() {
        return shippingLink;
    }

    public String getReturnsLink() {
        return returnsLink;
    }

    public String getOrderStatusLink() {
        return orderStatusLink;
    }

    public String getPaymentOptionLink() {
        return paymentOptionLink;
    }

    public String getHeadingFive() {
        return headingFive;
    }

    public String getLeftButton() {
        return leftButton;
    }

    public String getLeftButtonName() {
        return leftButtonName;
    }

    public String getRightButton() {
        return rightButton;
    }

    public String getRightButtonName() {
        return rightButtonName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}