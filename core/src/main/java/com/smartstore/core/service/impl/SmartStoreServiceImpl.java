package com.smartstore.core.service.impl;

import com.day.cq.dam.api.Asset;
import com.day.cq.wcm.api.PageManager;
import com.smartstore.core.constants.Constants;
import com.smartstore.core.service.SmartStoreService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(service = SmartStoreService.class, immediate = true)
public class SmartStoreServiceImpl implements SmartStoreService {


    /**
     * Class logger
     */
    private static final Logger logger = LoggerFactory.getLogger(SmartStoreServiceImpl.class);

    /**
     * Inject resource
     */
    @Reference
    ResourceResolverFactory resourceFactory;

    @Override
    public ResourceResolver getResourceResolver() {
        ResourceResolver resourceResolver;

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(ResourceResolverFactory.SUBSERVICE, Constants.SMARTSTORE_SERVICE);
        resourceResolver = getResourceResolver(paramMap);

        logger.info("[getResourceResolver]: {}", resourceResolver);
        return resourceResolver;
    }
    @Override
    public ResourceResolver getWritableResourceResolver() {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put(ResourceResolverFactory.SUBSERVICE, Constants.SMARTSTORE_WRITE_SERVICE);
        return getResourceResolver(userMap);
    }


    @Override
    public ResourceResolver getResourceResolver(Map<String, Object> userMap) {
        ResourceResolver resolver = null;
        try {
            resolver = resourceFactory.getServiceResourceResolver(userMap);
        }
        catch (LoginException e) {
            logger.error(
                    "Error in getResourceResolver() method. Can't get resource resolver with subservice: {}. Detailed error {}",
                    userMap.get(ResourceResolverFactory.SUBSERVICE), e);
        }
        logger.info("[getResourceResolver]: {}", resolver);
        return resolver;
    }

    @Override
    public Resource getResource(String path, ResourceResolver resolver) {
        if (resolver != null) {
            Resource resource = resolver.getResource(path);
            logger.info("[getResource]: {}", resource);
            return resource;
        } else {
            logger.error("No resource resolver found for service {} to resolve the resource at {}",
                    Constants.SMARTSTORE_SERVICE, path);
            return null;
        }
    }

    @Override
    public PageManager getPageManager(ResourceResolver resolver) {
        PageManager pageManager = resolver != null ? resolver.adaptTo(PageManager.class) : null;
        logger.info("[getPageManager]: {}", pageManager);
        return pageManager;
    }

    @Override
    public Asset getAsset(String path, ResourceResolver resolver) {
        if (resolver != null) {
            Resource res = resolver.getResource(path);

            if (res != null) {
                Asset asset = res.adaptTo(Asset.class);
                logger.info("[getAsset]: {}", asset);
                return asset;
            } else {
                logger.error("No asset found at {}", path);
                return null;
            }
        } else {
            logger.error("No resource resolver found for service {} to resolve the resource at {}",
                    Constants.SMARTSTORE_SERVICE, path);
            return null;
        }
    }

    @Override
    public void closeResourceResolver(final ResourceResolver resolver) {
        if (resolver != null && resolver.isLive()) {
            resolver.close();
        }
    }
}
