package com.smartstore.core.servlets;

import com.smartstore.core.constants.Constants;
import com.smartstore.core.service.EmailService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, immediate = true,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=" + Constants.EMAIL_SEND_NORMAL
        })
public class SendEmailServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = -7287396217733304212L;

    @Reference
    EmailService emailService;


    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) throws ServletException, IOException {

        emailService.sendEmail(Constants.TO_EMAIL,
                new String[]{Constants.CC_EMAIL},
                "Employee Detail's",
                "Hi Team, " +
                        "<br/> Name : Dharmavaram Murali.<br/>" +
                        " Mobile No : 9100459554 <br/> " +
                        "Company : Sutrix Solutions <br/>" +
                        " Role :  JAVA INTERN <br/>" +
                        " Location : Chennai <br/> " +
                        "  Thanks and Regards,<br/>Dharmavaram Murali.");

        response.getWriter().write("Email Sent Successfully Done !");

    }

}
