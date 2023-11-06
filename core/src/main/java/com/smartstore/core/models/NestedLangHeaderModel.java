package com.smartstore.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.List;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NestedLangHeaderModel {
    @ChildResource
    private List<String> country;

    @ChildResource
    private List<LanguageCode> languageCodes;

    public List<String> getCountry() {
        return country;
    }

    public List<LanguageCode> getLanguageCodes() {
        return languageCodes;
    }
}
