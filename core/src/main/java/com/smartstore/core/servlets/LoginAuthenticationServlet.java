package com.smartstore.core.servlets;

import com.smartstore.core.been.LoginCredentials;
import com.smartstore.core.constants.Constants;
import com.smartstore.core.service.EmailService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component(service = Servlet.class, immediate = true,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=" + Constants.USER_SIGNIN_PATH
        })
public class LoginAuthenticationServlet extends SlingAllMethodsServlet {

    private final static Logger Log = LoggerFactory.getLogger(LoginAuthenticationServlet.class);

    private static final long serialVersionUID = -7287396217733304212L;
    private static final String Email = "email";
    private static final String Password = "password";
    private static final String ValidOtp = "validOtp";
    private static final String loginEmailWIthotp = "loginEmailWIthotp";

    @Reference
    EmailService emailService;

    @Override
    protected void doPost(SlingHttpServletRequest request,
                          SlingHttpServletResponse response) throws ServletException, IOException {

        boolean isCheckedValue = Boolean.parseBoolean(request.getParameter("isChecked"));

        String email = request.getParameter(Email);
        String password = request.getParameter(Password);
        String loginEmail = request.getParameter(loginEmailWIthotp);
        String validOtp = request.getParameter(ValidOtp);

/*
        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setLoginEmail(loginEmail);*/

        ResourceResolver resourceResolver = request.getResourceResolver();
        boolean isChecked = true;
        if (isChecked) {
            boolean isSuccess = specialMemberLogin(resourceResolver, email, password, request, response, loginEmail, validOtp);
            if (isSuccess) {
//                emailService.sendEmail(email,
//                        new String[]{Constants.TO_EMAIL, Constants.CC_EMAIL},
//                        "Alert",
//           generateLoginSuccessEmailContent(email,Constants.SPECIAL_MEMBER,"Murali"));
                response.sendRedirect(Constants.SPECIAL_MEMBERS_HOME_PAGE_PATH);
            } else {
                sendAlertEmail(email);
                request.setAttribute("errorMessage", "Login failed. Please check your credentials.");
            }
        } else {
            boolean isSuccess = memberLogin(resourceResolver, email, password, request, response, loginEmail, validOtp);
            if (isSuccess) {
                sendSuccessLoginEmail(email);
                response.sendRedirect(Constants.MEMBERS_HOME_PAGE_PATH);
            } else {
                sendAlertEmail(email);
                request.setAttribute("errorMessage", "Login failed. Please check your credentials.");
            }
        }
    }


    private boolean memberLogin(ResourceResolver resourceResolver, String email, String password,
                                SlingHttpServletRequest request, SlingHttpServletResponse response, String loginEmail, String validOtp) throws IOException {

        Resource membersResourse = resourceResolver.getResource(Constants.ROOT_PATH_MEMBERS);
        LoginCredentials loginCredentials = new LoginCredentials();
        String storedOtp = loginCredentials.getLoginOtp();
        Map<String, String> storedCredentials = credentials(resourceResolver, membersResourse);
        boolean isValidCredentials = false;
        for (Map.Entry<String, String> credentials : storedCredentials.entrySet()) {
            String storedEmail = credentials.getKey();
            String storedPassword = credentials.getValue();
            if (storedEmail.equals(email) && storedPassword.equals(password)) {
                isValidCredentials = true;
                break;
            } else if (storedEmail.equals(loginEmail) && storedOtp.equals(validOtp)) {
                isValidCredentials = true;
                break;
            } else if (storedEmail.equals(email) && !storedOtp.equals(validOtp)) {
                request.setAttribute("errorMessage", "Incorrect OTP.");
                isValidCredentials = false;
                break;
            }
        }
        return isValidCredentials;
    }

    private boolean specialMemberLogin(ResourceResolver resourceResolver, String email, String password,
                                       SlingHttpServletRequest request, SlingHttpServletResponse response,String loginEmail, String validOtp) throws IOException {

        Resource specialMembersResourse = resourceResolver.getResource(Constants.ROOT_PATH_SPECIAL_MEMBERS);
        LoginCredentials loginCredentials = new LoginCredentials();
        String storedOtp = loginCredentials.getLoginOtp();
        Map<String, String> storedCredentials = credentials(resourceResolver, specialMembersResourse);
        boolean isValidCredentials = false;
        for (Map.Entry<String, String> credentials : storedCredentials.entrySet()) {
            String storedEmail = credentials.getKey();
            String storedPassword = credentials.getValue();
            if (storedEmail.equals(email) && storedPassword.equals(password)) {
                isValidCredentials = true;
                break;
            } else if (storedEmail.equals(loginEmail) && storedOtp.equals(validOtp)) {
                isValidCredentials = true;
                break;
            } else if (storedEmail.equals(email) && !storedOtp.equals(validOtp)) {
                request.setAttribute("errorMessage", "Incorrect OTP.");
                isValidCredentials = false;
                break;
            }
        }
        return isValidCredentials;
    }

    private Map<String, String> credentials(ResourceResolver resourceResolver, Resource resource) {

        Map<String, String> credentials = new HashMap<>();

        Iterator<Resource> children = resource.listChildren();
        while (children.hasNext()) {
            Resource childResource = children.next();
            ValueMap properties = childResource.getValueMap();
            String email = properties.get(Constants.EMAIL, String.class);
            String password = properties.get(Constants.PASSWORD, String.class);
            credentials.put(email, password);
        }

        return credentials;
    }

    public void sendSuccessLoginEmail(String email) {
        emailService.sendEmail(email,
                new String[]{Constants.TO_EMAIL, Constants.CC_EMAIL},
                Constants.EMAIL_SUBJECT,
                Constants.SUCCESS_LOGIN_SPECIAL_MEMBER_EMAIL_CONTENT);
    }

    public void sendAlertEmail(String email) {
        emailService.sendEmail(email,
                new String[]{Constants.TO_EMAIL, Constants.CC_EMAIL},
                Constants.EMAIL_SUBJECT,
                Constants.ALERT_EMAIL_CONTENT_SPECIAL_MEMBER);
    }
    public String generateLoginSuccessEmailContent(String email, String member,String userName) {
        return "<html>" +
                "<head>" +
                "<style type=\"text/css\">" +
                "#outlook a {" +
                "padding: 0;" +
                "}" +
                ".ReadMsgBody {" +
                "width: 100%;" +
                "}" +
                ".ExternalClass {" +
                "width: 100%;" +
                "}" +
                ".ExternalClass * {" +
                "line-height: 100%;" +
                "}" +
                "body {" +
                "margin: 0;" +
                "padding: 0;" +
                "-webkit-text-size-adjust: 100%;" +
                "-ms-text-size-adjust: 100%;" +
                "}" +
                "table, td {" +
                "border-collapse: collapse;" +
                "mso-table-lspace: 0pt;" +
                "mso-table-rspace: 0pt;" +
                "}" +
                "img {" +
                "border: 0;" +
                "height: auto;" +
                "line-height: 100%;" +
                "outline: none;" +
                "text-decoration: none;" +
                "-ms-interpolation-mode: bicubic;" +
                "}" +
                "p {" +
                "display: block;" +
                "margin: 13px 0;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body style=\"background-color:#f9f9f9;\">" +
                "<div style=\"background-color:#f9f9f9;\">" +
                "<!--[if mso | IE]>" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px;\" width=\"600\">" +
                "<tr>" +
                "<td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">" +
                "<![endif]-->" +
                "<div style=\"background:#f9f9f9;background-color:#f9f9f9;Margin:0px auto;max-width:600px;\">" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#f9f9f9;background-color:#f9f9f9;width:100%;\">" +
                "<tbody>" +
                "<tr>" +
                "<td style=\"border-bottom:#333957 solid 5px;direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;\">" +
                "<!--[if mso | IE]>" +
                "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">" +
                "<tr>" +
                "</tr>" +
                "</table>" +
                "<![endif]-->" +
                "</td>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "</div>" +
                "<!--[if mso | IE]>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px;\" width=\"600\">" +
                "<tr>" +
                "<td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">" +
                "<![endif]-->" +
                "<div style=\"background:#fff;background-color:#fff;Margin:0px auto;max-width:600px;\">" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#fff;background-color:#fff;width:100%;\">" +
                "<tbody>" +
                "<tr>" +
                "<td style=\"border:#dddddd solid 1px;border-top:0px;direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;\">" +
                "<!--[if mso | IE]>" +
                "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">" +
                "<tr>" +
                "<td style=\"vertical-align:bottom;width:600px;\">" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "<![endif]-->" +
                "<div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:bottom;width:100%;\">" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:bottom;\" width=\"100%\">" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">" +
                "<tbody>" +
                "<tr>" +
                "<td style=\"width:64px;\">" +
                "<img height=\"auto\" src=\"https://lh3.googleusercontent.com/ogw/AGvuzYbWpga5e0AquSy0ORrE6FN513GU8AXaJ5jlOgMp=s32-c-mo\" style=\"border:0;display:block;outline:none;text-decoration:none;width:100%;\" width=\"64\" />" +
                "</td>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;padding-bottom:40px;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:32px;font-weight:bold;line-height:1;box-shadow: rgba(17, 17, 26, 0.1) 0px 8px 24p,rgba(17, 17, 26, 0.1) 0px 16px 56px;text-align:center;color:#555;color:green;border: 2px solid black;transition: border-color 0.3s;padding: 10px;cursor: pointer;\">" +
                "Welcome to SmartStore"+
                "</div>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;padding-top:30px;padding-bottom:40px;word-break:break-word;\">" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:separate;line-height:100%;\">" +
                "<tr>" +
                "<td align=\"center\" bgcolor=\"#FFDAB9\" role=\"presentation\" style=\"border:none;border-radius:20px;color:#ffffff;position: relative; bottom: 34px;cursor:auto;padding:15px 85px;\" valign=\"middle\">" +
                "<p style=\"background:#2F67F6;color:#f80707;font-family:'Helvetica Neue',Arial,sans-serif;font-size:18px;font-weight:normal;line-height:120%;Margin:0;text-decoration:none;text-transform:none;\">" +
                "Type : " + member  +
                "</p>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:16px;line-height:22px;text-align:center;color:#555;\">" +
                "Hello <b><i>"+ userName +"</i></b> You're successfully logged in into smartStore website." +
                "</div>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;padding-bottom:0;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:16px;line-height:22px;text-align:center;color:#555;\">" +
                "Or verify using this link:" +
                "</div>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:26px;font-weight:bold;line-height:1;text-align:center;color:#555;\">" +
                "Need Help?" +
                "</div>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:14px;line-height:22px;text-align:center;color:#555;\">" +
                "Please send any feedback or bug info to <a href=\"mailto:info@example.com\" style=\"color:#2F67F6\">smartstore.online.m@gmail.com</a>" +
                "</div>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "</div>" +
                "</td>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "</div>" +
                "<!--[if mso | IE]>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px;\" width=\"600\">" +
                "<tr>" +
                "<td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">" +
                "<![endif]-->" +
                "<div style=\"Margin:0px auto;max-width:600px;\">" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">" +
                "<tbody>" +
                "<tr>" +
                "<td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;\">" +
                "<!--[if mso | IE]>" +
                "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">" +
                "<tr>" +
                "<td style=\"vertical-align:bottom;width:600px;\">" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "<![endif]-->" +
                "<div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:bottom;width:100%;\">" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
                "<tbody>" +
                "<tr>" +
                "<td style=\"vertical-align:bottom;padding:0;\">" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:0;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:12px;font-weight:300;line-height:1;text-align:center;color:#575757;\">" +
                "Some Firm Ltd, 35 Avenue. City 10115, USA" +
                "</div>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:12px;font-weight:300;line-height:1;text-align:center;color:#575757;\">" +
                "<a href=\"\" style=\"color:#575757\">Unsubscribe</a> from our emails" +
                "</div>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "</td>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "</div>" +
                "</td>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "</div>" +
                "<!--[if mso | IE]>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "<![endif]-->" +
                "</div>" +
                "</body>" +
                "</html>";
    }


}
