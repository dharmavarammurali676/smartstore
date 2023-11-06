package com.smartstore.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Country {

    @ChildResource
    private String country;

    @ChildResource
    private List<LanguageCode> languageMF;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<LanguageCode> getLanguageMF() {
        return languageMF;
    }

    public void setLanguageMF(List<LanguageCode> languageMF) {
        this.languageMF = languageMF;
    }
}
