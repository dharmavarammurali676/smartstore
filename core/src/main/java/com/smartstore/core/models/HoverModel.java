package com.smartstore.core.models;

import com.smartstore.core.models.hover.HoverList;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.List;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HoverModel {

    @ChildResource
    private List<HoverList> hoverList;

    public List<HoverList> getHoverList() {
        return hoverList;
    }
}
