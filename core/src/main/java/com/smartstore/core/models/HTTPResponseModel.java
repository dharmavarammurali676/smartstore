package com.smartstore.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class,
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HTTPResponseModel {

    @ChildResource
    private String httpErrorTittle;

    @ChildResource
    private String httpErrorDescription;

    public String getHttpErrorTittle() {
        return httpErrorTittle;
    }

    public String getHttpErrorDescription() {
        return httpErrorDescription;
    }
}
