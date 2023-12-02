package com.smartstore.core.models.hover;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HoverList {

    @ChildResource
    private String imagePath;
    @ChildResource
    private String heading;

    @ChildResource
    private String description;

    @ChildResource
    private String buttonLabel;

    @ChildResource
    private String buttonLink;

    public String getImagePath() {
        return imagePath;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public String getButtonLink() {
        return buttonLink;
    }
}
