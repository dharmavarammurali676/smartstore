package com.smartstore.core.models;

import com.smartstore.core.models.carousel.CarouselItems;
import com.smartstore.core.models.hover.HoverList;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.annotation.PostConstruct;
import java.util.List;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselModel {

    @ChildResource
    private List<CarouselItems> carouselItems;
    @ChildResource
    private int carouselCount;

    @PostConstruct
    public void init() {

        carouselCount = (carouselItems != null) ? carouselItems.size() : 0;
    }

    public int getCarouselCount() {
        return carouselCount;
    }

    public List<CarouselItems> getCarouselItems() {
        return carouselItems;
    }
}
