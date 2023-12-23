//package com.smartstore.core.servlets;
//
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.api.SlingHttpServletResponse;
//import org.apache.sling.api.servlets.HttpConstants;
//import org.apache.sling.api.servlets.SlingAllMethodsServlet;
//import org.apache.sling.api.resource.Resource;
//import org.apache.sling.api.resource.ResourceResolver;
//import org.osgi.framework.Constants;
//import org.osgi.service.component.annotations.Component;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.Servlet;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component(
//        service = Servlet.class,
//        property = {
//                Constants.SERVICE_DESCRIPTION + "=Custom Servlet",
//                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
//                "sling.servlet.paths=" + "/bin/sendemail"
//        }
//)
//public class SendEmailWithTemplate extends SlingAllMethodsServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Override
//    protected void doGet(final SlingHttpServletRequest req,
//                         final SlingHttpServletResponse resp) throws IOException {
//
//        String emailTemplatePath = "/etc/notification/email/practice/practice.html";
//
//        // Get a resource resolver to access AEM resources
//        ResourceResolver resolver = req.getResourceResolver();
//
//        // Get the HTML file from DAM
//        Resource templateResource = resolver.getResource(emailTemplatePath);
//        InputStream templateStream = templateResource.adaptTo(InputStream.class);
//
//        // Read the content of the HTML file
//        // Note: You might want to use a proper method to read the content of the InputStream
//        // For simplicity, I'm using a placeholder method here (readTemplateContent)
//        String emailTemplateContent = readTemplateContent(templateStream);
//
//        // Get values from the map
//        Map<String, String> emailParams = new HashMap<>();
//        emailParams.put("subject", "Practice AEM Send Email");
//        emailParams.put("message", "This is practice email having attachment !!!");
//        emailParams.put("senderEmailAddress", "toimrank@gmail.com");
//        emailParams.put("senderName", "Imran Khan");
//        emailParams.put("recipientName", "Test User");
//
//        // Replace placeholders with actual values from the map
//        for (Map.Entry<String, String> entry : emailParams.entrySet()) {
//            String placeholder = "${" + entry.getKey() + "}";
//            emailTemplateContent = emailTemplateContent.replace(placeholder, entry.getValue());
//        }
//
//        // Add your logic to send the email with the modified template
//
//        resp.setStatus(SlingHttpServletResponse.SC_OK);
//        resp.setContentType("application/json;charset=UTF-8");
//        resp.getWriter().print("{\"response message\" : \" Email Successfully Sent !!!\"}");
//    }
//
//    private String readTemplateContent(InputStream templateStream) throws IOException {
//        // Implement the logic to read the content of the InputStream
//        // For simplicity, I'm using a placeholder method here
//        // Make sure to handle stream closing properly in a production scenario
//        // For example, you can use try-with-resources
//        // Note: This is a placeholder method, and you should replace it with the actual logic
//        return "This is a placeholder content";
//    }
//}
