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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = Servlet.class, immediate = true,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=" + Constants.SEARCH_PATH,
        })
public class SearchServlet extends SlingAllMethodsServlet {

    private final static Logger Log = LoggerFactory.getLogger(SearchServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request,
                          SlingHttpServletResponse response) throws IOException, ServletException {

        String search = request.getParameter("search");
        String usernameFilter = request.getParameter("userName");

        boolean isMemberSelected = "true".equals(request.getParameter("memberCheckbox"));
        boolean isSpecialMemberSelected = "true".equals(request.getParameter("specialMemberCheckbox"));

        List<String> searchResults = performSearch(search, usernameFilter, isMemberSelected, isSpecialMemberSelected);

        // Process and return search results as needed...
    }

    private List<String> performSearch(String searchTerm, String usernameFilter, boolean memberSelected, boolean specialMemberSelected) {
        List<String> searchResults = new ArrayList<>();
        // Implement search logic based on searchTerm and usernameFilter...

        // Apply membership type filters
        if (memberSelected) {
            // Filter for regular members...
        }
        if (specialMemberSelected) {
            // Filter for special members...
        }

        return searchResults;
    }

    private Map<String, String> getUsersData(SlingHttpServletRequest request) {
        Map<String, String> dataList = new HashMap<>();

        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource("/database/smartstore/members");
        if (resource != null) {
            for (Resource childResource : resource.getChildren()) {
                if (ResourceUtil.isNonExistingResource(childResource)) {
                    continue;
                }
                ValueMap properties = childResource.getValueMap();
                for (Map.Entry<String, Object> entry : properties.entrySet()) {
                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    dataList.put(propertyName, propertyValue.toString());
                }
            }
        }

        return dataList;
    }


}