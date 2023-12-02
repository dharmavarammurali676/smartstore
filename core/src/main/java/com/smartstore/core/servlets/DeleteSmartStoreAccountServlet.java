package com.smartstore.core.servlets;

import com.smartstore.core.constants.Constants;
import com.smartstore.core.service.EmailService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.jcr.RepositoryException;

/**
 * @author: Dharmavaram Murali,
 * @Date: 07-03-2023,
 * @Time: 18:32
 */
@Component(service = Servlet.class, immediate = true,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=" + Constants.DELETE_SMART_STORE_ACCOUNT_PATH,
        })
public class DeleteSmartStoreAccountServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = -7287394343636304212L;

    @Reference
    EmailService emailService;

    @Override
    protected void doPost(SlingHttpServletRequest request,
                          SlingHttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource parentResource = resourceResolver.getResource("/database/smartstore/special-members");

        // Delete SmartStore Account Based on (Email & Password)
        Iterable<Resource> childResources = parentResource.getChildren();
        for (Resource childResource : childResources) {
            String childNodeName = childResource.getName();
            if (childNodeName.equals(email)) {
                resourceResolver.delete(childResource);
                resourceResolver.commit();
                response.getWriter().write("SmartStore Account with email [ " + email + " ] has been deleted");
//                emailService.sendEmail(Constants.TO_EMAIL,
//                        new String[]{Constants.CC_EMAIL},
//                        "Account Status",
//                        "Your SmartStore Account with email [ " + email + " ] has been deleted");
                response.getWriter().write("Account deletion notification sent to your registered Email");
                break;
            }
        }
    }
}
