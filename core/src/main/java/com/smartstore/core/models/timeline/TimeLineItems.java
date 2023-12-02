package com.smartstore.core.models.timeline;

import com.smartstore.core.constants.Constants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TimeLineItems {

    @ChildResource
    private String date;
    @ChildResource
    private String pageTitle;
    @ChildResource
    private String title;
    @ChildResource
    private String description;
    @ChildResource
    private String pageLink;


    public String getDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        date = simpleDateFormat.format(new Date());
        return date;
    }

    public String getPageLink() {
        return pageLink + Constants.DOT_HTML;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
