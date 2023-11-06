package com.smartstore.core.models;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CountryLanguageModel {

    @ChildResource
    private List<Country> countryMF;
    @SlingObject
    private Resource currentResource;

    private String nonExistingUrl;

    private String redirectUrl;

    private String replaceRedirectUrl;

    @PostConstruct
    protected void init() {
        PageManager pageManager = currentResource.getResourceResolver().adaptTo(PageManager.class);
        Page currentPageProp = pageManager.getContainingPage(currentResource);

        if (currentPageProp != null) {
            ValueMap currPageProperties = currentPageProp.getProperties();
            String currentPath = currentPageProp.getPath();
            replaceRedirectUrl = properRedirectUrl(currentPath);

        }
    }

    private String properRedirectUrl(String currentPath) {
        String redirectPath = null;
        Country country = new Country();
        nonExistingUrl = country.getCountry();
        if (currentPath.equals(nonExistingUrl)) {
            boolean isUrl= Boolean.parseBoolean(redirectUrl);
            redirectPath = isUrl ? redirectUrl :StringUtils.EMPTY;
        }

        return redirectPath;
    }



    public List<Country> getCountryMF() {
        return countryMF;
    }

    public void setCountryMF(List<Country> countryMF) {
        this.countryMF = countryMF;
    }

}