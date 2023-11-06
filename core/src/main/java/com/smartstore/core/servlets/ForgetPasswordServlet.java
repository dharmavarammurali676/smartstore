package com.smartstore.core.servlets;

import com.smartstore.core.constants.Constants;
import com.smartstore.core.service.EmailService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, immediate = true,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=" + Constants.UPDATE_PASSWORD_PATH
        })
public class ForgetPasswordServlet extends SlingAllMethodsServlet {

    private final static Logger Log = LoggerFactory.getLogger(GenerateOtpServlet.class);

    private static final long serialVersionUID = -876547394343633452L;

    @Reference
    EmailService emailService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String newPassword = request.getParameter("newpassword");
        String confirmPassword = request.getParameter("confirmpassword");
        String oldEmail = request.getParameter("oldemail");
        String oldPassword = request.getParameter("oldpassword");
        String newPass = request.getParameter("newpass");
        String confirmPass = request.getParameter("newpass");

        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource("/database/smartstore/members");
        boolean emailFound = false;

        if (resource != null) {
            for (Resource childResource : resource.getChildren()) {
                if (ResourceUtil.isNonExistingResource(childResource)) {
                    continue;
                }
                ValueMap properties = childResource.getValueMap();
                String storedEmail = properties.get("Email", String.class);
                String storedPassword = properties.get("Password", String.class);
                String storedConfirmPassword = properties.get("ConfirmPassword", String.class);

                if (storedEmail.equals(email) || storedEmail.equals(oldEmail)) {
                    emailFound = true;
                }

                // Email not found
                if (!emailFound || storedEmail.equals(oldEmail)) {

                }

                if (storedEmail.equals(email)) {
                    if (newPassword.equals(confirmPassword)) {
                        String updatedPassword = confirmPassword;
                        updatePassword(childResource, confirmPassword, resourceResolver);
                        SendSuccessEmail(email);
                        response.getWriter().write("Password Updated");
                        break;
                    } else {
                        SendFailureEmail(email);
                        return;
                    }
                } else {
                    if (storedEmail.equals(oldEmail)) {
                        if (storedPassword.equals(oldPassword)) {
                            if (newPass.equals(confirmPass)) {
                                String replacePass = confirmPass;
                                updatePassword(childResource, confirmPass, resourceResolver);
                                SendSuccessEmail(oldEmail);
                                response.getWriter().write("Password has been updated");
                              break;
                            } else {
                                SendFailureEmail(oldEmail);
                                return;
                            }
                        }
                    }
                }
            }
        }

        SendFailureEmail(email); // Mail received to Author
    }

    private void updatePassword(Resource resource, String newPassword, ResourceResolver resourceResolver) throws PersistenceException {
        ModifiableValueMap modifiableValueMap = resource.adaptTo(ModifiableValueMap.class);
        modifiableValueMap.put("Password", newPassword);
        modifiableValueMap.put("ConfirmPassword", newPassword);
        resourceResolver.commit();
    }

    public void SendSuccessEmail(String email) {
        emailService.sendEmail(email,
                new String[]{Constants.TO_EMAIL, Constants.CC_EMAIL},
                Constants.EMAIL_SUBJECT,
                Constants.FORGET_PASSWORD_SUCCESS_EMAIL_CONTENT);
    }

    public void SendFailureEmail(String email) {
        emailService.sendEmail(email,
                new String[]{Constants.TO_EMAIL, Constants.CC_EMAIL},
                Constants.EMAIL_SUBJECT,
                Constants.FORGET_PASSWORD_FAILURE_EMAIL_CONTENT);
    }
}

