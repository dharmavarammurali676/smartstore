package com.smartstore.core.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.smartstore.core.been.MemberInformation;
import com.smartstore.core.been.SpecialMemberInformation;
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
import java.util.List;

@Component(
        service = Servlet.class,
        immediate = true,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=" + Constants.MEMBER_INFORMATION
        }
)
public class MemberInformationServlet extends SlingAllMethodsServlet {

    private static final Logger Log = LoggerFactory.getLogger(MemberInformationServlet.class);
    private static final long serialVersionUID = -7287396217733304212L;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) throws ServletException, IOException {
        try {
            List<SpecialMemberInformation> specialMembers = getSpecialMembersSmartStoreUsers(request);
            List<MemberInformation> members = getMembersSmartStoreUsers(request);

            JsonObject jsonResponse = new JsonObject();
            jsonResponse.add("Members", createJsonArray(members));
            jsonResponse.add("Special Members", createSPJsonArray(specialMembers));

            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
        } catch (Exception e) {
            Log.error("Error processing request", e);
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private JsonArray createJsonArray(List<MemberInformation> memberList) {
        JsonArray jsonArray = new JsonArray();
        for (MemberInformation memberInfo : memberList) {
            JsonObject memberObject = new JsonObject();
            memberObject.addProperty("username", memberInfo.getUsername());
            memberObject.addProperty("contactNumber", memberInfo.getContactNumber());
            memberObject.addProperty("registrationDate", memberInfo.getRegistrationDate());
            memberObject.addProperty("category", memberInfo.getCategory());
            memberObject.addProperty("email", memberInfo.getEmail());
            jsonArray.add(memberObject);
        }
        return jsonArray;
    }

    private JsonArray createSPJsonArray(List<SpecialMemberInformation> memberList) {
        JsonArray jsonArray = new JsonArray();
        for (SpecialMemberInformation memberInfo : memberList) {
            JsonObject memberObject = new JsonObject();
            memberObject.addProperty("username", memberInfo.getUsername());
            memberObject.addProperty("contactNumber", memberInfo.getContactNumber());
            memberObject.addProperty("registrationDate", memberInfo.getRegistrationDate());
            memberObject.addProperty("category", memberInfo.getCategory());
            memberObject.addProperty("email", memberInfo.getEmail());
            jsonArray.add(memberObject);
        }
        return jsonArray;
    }


    private List<SpecialMemberInformation> getSpecialMembersSmartStoreUsers(SlingHttpServletRequest request) {
        List<SpecialMemberInformation> specialMembers = new ArrayList<>();
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource(Constants.ROOT_PATH_SPECIAL_MEMBERS);

        if (resource != null) {
            for (Resource childResource : resource.getChildren()) {
                if (ResourceUtil.isNonExistingResource(childResource)) {
                    continue;
                }
                ValueMap properties = childResource.getValueMap();

                SpecialMemberInformation memberInfo = new SpecialMemberInformation();
                memberInfo.setUsername(properties.get(Constants.USERNAME, String.class));
                memberInfo.setContactNumber(properties.get(Constants.CONTACT_NUMBER, String.class));
                memberInfo.setRegistrationDate(properties.get(Constants.REGISTRATION_DATE, String.class));
                memberInfo.setCategory(properties.get(Constants.CATEGORY, String.class));
                memberInfo.setEmail(properties.get(Constants.EMAIL, String.class));

                specialMembers.add(memberInfo);
            }
        } else {
            Log.warn("Resource not found at path: {}", Constants.ROOT_PATH_SPECIAL_MEMBERS);
        }
        return specialMembers;
    }

    private List<MemberInformation> getMembersSmartStoreUsers(SlingHttpServletRequest request) {
        List<MemberInformation> members = new ArrayList<>();
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource(Constants.ROOT_PATH_MEMBERS);

        if (resource != null) {
            for (Resource childResource : resource.getChildren()) {
                if (ResourceUtil.isNonExistingResource(childResource)) {
                    continue;
                }
                ValueMap properties = childResource.getValueMap();

                MemberInformation memberInfo = new MemberInformation();
                memberInfo.setUsername(properties.get(Constants.USERNAME, String.class));
                memberInfo.setContactNumber(properties.get(Constants.CONTACT_NUMBER, String.class));
                memberInfo.setRegistrationDate(properties.get(Constants.REGISTRATION_DATE, String.class));
                memberInfo.setCategory(properties.get(Constants.CATEGORY, String.class));
                memberInfo.setEmail(properties.get(Constants.EMAIL, String.class));

                members.add(memberInfo);
            }
        } else {
            Log.warn("Resource not found at path: {}", Constants.ROOT_PATH_MEMBERS);
        }
        return members;
    }
}
