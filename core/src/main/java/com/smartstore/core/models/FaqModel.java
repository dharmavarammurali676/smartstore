package com.smartstore.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.List;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FaqModel {


    @ChildResource
    private String imagePath;

    @ChildResource
    private String heading;

    public String getImagePath() {
        return imagePath;
    }

    public String getHeading() {
        return heading;
    }

    @ChildResource
    private List<FaqMultiList> faqMultiLists;
}
