package com.smartstore.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.annotation.PostConstruct;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TitleModel {
    @ChildResource
    private String title;

    @ChildResource
    private String textFormat;

    private String applyCss;

    private String selectedFormat(String textName){

        switch (textName){
            case "paragraph":
                return "default-cmp-paragraph";
            case "h1":
                return "default-cmp-h1";
            case "h2":
                return "default-cmp-h2";
            case "h3":
                return "default-cmp-h3";
            case "h4":
                return "default-cmp-h4";
            case "h5":
                return "default-cmp-h5";
            case "h6":
                return "default-cmp-h6";
            case "blockquoter":
                return "default-cmp-blockquoter";
            case "strong":
                return "default-cmp-strong";
            default:
                return StringUtils.EMPTY;
        }
    }

    public String getApplyCss() {
        return applyCss = selectedFormat(textFormat);
    }

    public String getTitle() {
        return title;
    }

    public String getTextFormat() {
        return textFormat;
    }
}
