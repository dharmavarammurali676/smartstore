package com.smartstore.core.service;

import com.day.cq.dam.api.Asset;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.Map;

public interface SmartStoreService {


    /**
     * Get Resource resolver
     *
     * @return resource resolver
     */


    /**
     * Get Resource resolver from subservice
     *
     * @param userMap
     * @return
     */
    public ResourceResolver getResourceResolver(Map<String, Object> userMap);

    /**
     * Get the resource for the given path
     *
     * @param path from which the resource will return
     * @return resource associated with the path
     */
    public Resource getResource(String path, ResourceResolver resourceResolver);

    /**
     * Provide method for easily access the PageManger object
     *
     * @return page manager object
     */
    public PageManager getPageManager(ResourceResolver resolver);


    ResourceResolver getResourceResolver();

    /**
     * Get resource resolver object
     *
     * @return resource resolver object or null if cannot create
     */
    ResourceResolver getWritableResourceResolver();

    /**
     * Get the asset for the given path
     *
     * @param path from which the asset will return
     * @return resource associated with the path
     */
    public Asset getAsset(String path, ResourceResolver resolver);

    /**
     * Method close {@link ResourceResolver} after used.
     *
     */
    void closeResourceResolver(final ResourceResolver resolver);
}
