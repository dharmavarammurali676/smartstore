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

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : Dharmavaram Murali,
 * @Date : 07-03-2023,
 * @Time : 18:32
 */
@Component(service = Servlet.class, immediate = true,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=" + Constants.SHOW_DELETE_ORDER_PATH,
                "sling.servlet.selectors=js"
        })
public class ShowandDeleteOrdersServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = -7287394343636304212L;

    @Reference
    EmailService emailService;

    @Override
    protected void doPost(SlingHttpServletRequest request,
                          SlingHttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String regEmail = request.getParameter("regEmail");
        String orderId = request.getParameter("orderId");
        String deleteEmail = request.getParameter("deleteEmail");


        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource parentResource = resourceResolver.getResource("/database/orders/bookings");
        Resource showEmailPath = resourceResolver.getResource("/database/orders/bookings/" + email);
        Resource deleteIDWithEmailPath = resourceResolver.getResource("/database/orders/bookings/" + regEmail);
        Resource deleteEmailPath = resourceResolver.getResource("/database/orders/bookings/" + deleteEmail);


        try {
            /////////// Show all Orders Based on (Email)
            if (parentResource != null) {
                Resource childResource = parentResource.getChild(email);
                if (childResource != null) {
                    Node child = childResource.adaptTo(Node.class);
                    NodeIterator childNodes = child.getNodes();
                    StringBuilder orderIds = new StringBuilder();
                    while (childNodes.hasNext()) {
                        Node childNode = childNodes.nextNode();
                        String childNodeName = childNode.getName();
                        orderIds.append("<br><b style=\"color:#e31010f5;\"" +
                                " font-family: serif; font-family: 'Google Sans';> " +
                                "ORDER ID : { ").append(childNodeName).append(" }</b><br>");
                        String propertyFirstname = childNode.getProperty("Firstname").getString();
                        String propertyLastname = childNode.getProperty("Lastname").getString();
                        String propertyBrand = childNode.getProperty("Brand").getString();
                        String propertyModel = childNode.getProperty("Model").getString();
                        String propertyPhoneNumber = childNode.getProperty("PhoneNumber").getString();
                        orderIds.append("Firstname : ").append(propertyFirstname).append("<br>");
                        orderIds.append("Lastname : ").append(propertyLastname).append("<br>");
                        orderIds.append("Brand : ").append(propertyBrand).append("<br>");
                        orderIds.append("Model : ").append(propertyModel).append("<br>");
                        orderIds.append("PhoneNumber : ").append(propertyPhoneNumber).append("<br>");
                    }
                    String childIds = orderIds.toString();
                    emailService.sendEmail(Constants.TO_EMAIL,
                            new String[]{Constants.CC_EMAIL},
                            "Order ID",
                            "Your [ " + email + " ] Order ID's : <br>" +
                                    " [<b style=\"color:#706a5fb0;\" font-size: medium; font-family: serif;> " + childIds + "</b>]");
                    response.getWriter().write("Your Orders ID's sent to your registered Email");
                }
            } else {
                response.getWriter().write("Order with the given email does not exist");
            }
            /////////////// Delete Order Based on (ID)
            if (regEmail != null && orderId != null) {
                if (deleteIDWithEmailPath != null) {
                    Resource orderIdResource = deleteIDWithEmailPath.getChild(orderId);
                    if (orderIdResource != null) {
                        orderIdResource.getResourceResolver().delete(orderIdResource);
                        orderIdResource.getResourceResolver().commit();
                        response.getWriter().write("Order [ " + orderId + " ] is deleted");

                    }
                }
            } else {
                response.getWriter().write("[" + regEmail + "] [" + orderId + "] does not exist");
            }

            /////////// Delete all Orders Based on (Email)
            Iterable<Resource> childResources = parentResource.getChildren();
            for (Resource childResource : childResources) {
                String childNodeName = childResource.getName();
                if (childNodeName.equals(deleteEmail)) {
                    resourceResolver.delete(childResource);
                    resourceResolver.commit();
                    break;

                }
                response.getWriter().write("All orders with email [ " + deleteEmail + " ] have been deleted");
            }

        } catch (RepositoryException e) {

        }
    }
}









