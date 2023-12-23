package com.smartstore.core.models;

import com.smartstore.core.models.home.LeftItems;
import com.smartstore.core.models.home.RightItems;
import com.smartstore.core.models.hover.HoverList;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HomePageModel {

    @ChildResource
    private List<RightItems> rightItems;
    @ChildResource
    private List<LeftItems> leftItems;

    @ChildResource
    private String rightLink;

    @Inject
    private String leftLink;

    @PostConstruct
    protected void init(){

        for (RightItems items : rightItems) {
            rightLink = items.getButtonLink();
        }

        for (LeftItems items : leftItems) {
            leftLink = items.getButtonLink();
        }

    }


    public String getRightLink() {
        return rightLink;
    }

    public String getLeftLink() {
        return leftLink;
    }

    public List<RightItems> getRightItems() {
        return rightItems;
    }

    public List<LeftItems> getLeftItems() {
        return leftItems;
    }
}
