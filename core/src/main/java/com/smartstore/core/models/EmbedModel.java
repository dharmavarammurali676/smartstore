package com.smartstore.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EmbedModel {

    @ChildResource
    private String html;

    @ChildResource
    private String videoid;

    @ChildResource
    private String autoplay;

    @PostConstruct
    protected void init() {
        if (StringUtils.isNotEmpty(videoid) && StringUtils.isNotEmpty(html)) {
            if (html.contains("embed")) {
                html = html.replaceFirst("embed/[a-zA-Z0-9_-]+", "embed/" + videoid);
                if (!StringUtils.isEmpty(autoplay)){
                    html = html.replaceFirst("embed/[a-zA-Z0-9_-]+", "embed/" + videoid + "?&autoplay=" + autoplay);
                }
            }
        }

        if (StringUtils.isNotEmpty(videoid) && StringUtils.isNotEmpty(html)) {
            if (html.contains("vimeo")) {
                html = html.replaceFirst("video/[a-zA-Z0-9_-]+", "video/" + videoid);
                if (!StringUtils.isEmpty(autoplay)){
                    html = html.replaceFirst("video/[a-zA-Z0-9_-]+", "video/" + videoid + "?&autoplay=" + autoplay);
                }
            }
        }
    }

    public String getHtml() {
        return html;
    }

    public String getVideoid() {
        return videoid;
    }

    public String getAutoplay() {
        return autoplay;
    }
}
