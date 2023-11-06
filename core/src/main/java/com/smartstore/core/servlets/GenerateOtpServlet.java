package com.smartstore.core.servlets;

import com.smartstore.core.constants.Constants;
import com.smartstore.core.service.EmailService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class, immediate = true,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=" + Constants.OTP_SENDER_PATH
        })
public class GenerateOtpServlet extends SlingAllMethodsServlet {

    private final static Logger log = LoggerFactory.getLogger(GenerateOtpServlet.class);


    private static final long serialVersionUID = -876547394343633452L;

    @Reference
    EmailService emailService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

      /*  LoginCredentials loginCredentials = new LoginCredentials();
        String email = loginCredentials.getLoginEmail();*/
        String email = request.getParameter("email");

        if (email == null || email.isEmpty()) {
            response.sendError(SlingHttpServletResponse.SC_BAD_REQUEST, "Email address is missing");
            return;
        }
        int fourDigitPass = (int) (Math.random() * 1000000); // Generates a 4-digit OTP
        int sixDigitPass = (int) (Math.random() * 900000) + 100000; // Generates a 6-digit OTP

        try {
            emailService.sendEmail(email,
                    new String[]{Constants.TO_EMAIL, Constants.CC_EMAIL},
                    "SmartStore Authontication",
                    generateEmailContent(sixDigitPass));

            response.getWriter().write("OTP sent to email: " + email);
        } catch (Exception e) {
            log.error("Error sending OTP email:", e);
            response.sendError(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to send OTP email");
        }
    }

    private String generateEmailContent(int sixDigitPass) {
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
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:32px;font-weight:bold;line-height:1;text-align:center;color:#555;\">" +
                "SmartStore Login Authentication OTP"+
                "</div>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;padding-bottom:0;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:16px;line-height:22px;text-align:center;color:#555;\">" +
                " Yes, we know." +
                "</div>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:16px;line-height:22px;text-align:center;color:#555;\">" +
                "An email to confirm an login otp. \uD83E\uDD2A" +
                "</div>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;padding-bottom:20px;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:16px;line-height:22px;text-align:center;color:#555;\">" +
                " Please validate your otp for login your smartstore website authonetication login." +
                "</div>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;padding-top:30px;padding-bottom:40px;word-break:break-word;\">" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:separate;line-height:100%;\">" +
                "<tr>" +
                "<td align=\"center\" bgcolor=\"#2F67F6\" role=\"presentation\" style=\"border:none;border-radius:3px;color:#ffffff;cursor:auto;padding:15px 85px;\" valign=\"middle\">" +
                "<p style=\"background:#2F67F6;color:#ffffff;font-family:'Helvetica Neue',Arial,sans-serif;font-size:15px;font-weight:normal;line-height:120%;Margin:0;text-decoration:none;text-transform:none;\">" +
                "OTP : " + sixDigitPass  +
                "</p>" +
                "</td>" +
                "</tr>" +
                "</table>" +
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
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;padding-bottom:40px;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:16px;line-height:22px;text-align:center;color:#555;\">" +
                "<a href=\"/content/smartstore/english/authentication-loginpage.html\" style=\"color:#2F67F6\">/content/smartstore/english/authentication-loginpage.html</a>" +
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
