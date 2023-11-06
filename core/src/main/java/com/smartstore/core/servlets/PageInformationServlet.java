package com.smartstore.core.servlets;

import com.smartstore.core.constants.Constants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

@Component(service = Servlet.class, immediate = true,
        property = {
                "sling.servlet.methods="+ HttpConstants.METHOD_GET,
                "sling.servlet.paths="+ Constants.PAGE_INFORMATION,
        })
public class PageInformationServlet extends SlingAllMethodsServlet {

    private final static Logger Log = LoggerFactory.getLogger(PageInformationServlet.class);

    private static final long serialVersionUID = -7287396217733304212L;

    @Override
    protected void doGet(SlingHttpServletRequest request,
                          SlingHttpServletResponse response) throws ServletException, IOException {

        String memberInfo = "";

        String Parameter = request.getParameter("tagName");
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource(Parameter);
        for (Resource childResource : resource.getChildren()) {
            if (ResourceUtil.isNonExistingResource(childResource)) {
                continue;
            }
            ValueMap properties = childResource.getValueMap();
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                String propertyName = entry.getKey();
                Object propertyValue = entry.getValue();
                memberInfo += propertyName + ": " + propertyValue + Constants.LINE_BREAK;
            }
        }
        response.getWriter().write(memberInfo);


        }
    }


