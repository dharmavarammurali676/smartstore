package com.smartstore.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class InfoGraphicModel {

    private static final Logger log = LoggerFactory.getLogger(InfoGraphicModel.class);

    @SlingObject
    private Resource componentResource;

    private List<Map<String, String>> tabLists = new ArrayList<>();

    public List<Map<String, String>> getTabLists() {
        return tabLists;
    }

    @PostConstruct
    protected void init() {

        Resource support = componentResource.getChild("tabLists");

        if (support!=null){
            for (Resource supports : support.getChildren()){

                Map<String, String> multiData = new HashMap<>();

                multiData.put("title",
                        supports.getValueMap().get("title",String.class));
                multiData.put("description",
                        supports.getValueMap().get("description",String.class));
                multiData.put("link",
                        supports.getValueMap().get("link",String.class));
                multiData.put("borderColor",
                        supports.getValueMap().get("borderColor",String.class));

                tabLists.add(multiData);
            }
        }

    }

}
