package com.smartstore.core.models.carousel;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CarouselItems {

    @ChildResource
    private String imagePath;
    @ChildResource
    private String title;



    public String getImagePath() {
        return imagePath;
    }

    public String getTitle() {
        return title;
    }
}
