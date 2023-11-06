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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@Component(service = Servlet.class, immediate = true,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=" + Constants.USER_REGISTRATION_PATH
        })
public class RegistrationAuthenticationServlet extends SlingAllMethodsServlet {
    private static final Logger Log = LoggerFactory.getLogger(RegistrationAuthenticationServlet.class);
    @Reference
    EmailService emailService;

    private static final String Username = "username";
    private static final String Password = "password";
    private static final String ConfirmPassword = "repassword";
    private static final String Email = "email";
    private static final String MobileNumber = "mobileno";
    private static final String ContentType = "text/html";

    @Override
    protected void doPost(SlingHttpServletRequest request,
                          SlingHttpServletResponse response) throws ServletException, IOException {

        response.setContentType(ContentType);
        PrintWriter printWriter = response.getWriter();
        String regUserName = request.getParameter(Username);
        String regPassword = request.getParameter(Password);
        String regRepass = request.getParameter(ConfirmPassword);
        String regEmail = request.getParameter(Email);
        String regMobileNumber = request.getParameter(MobileNumber);
        Calendar calendar = Calendar.getInstance();
        ResourceResolver resourceResolver = request.getResourceResolver();


        boolean isChecked = true;

        if (isChecked) {
            specialMembersRegistration(resourceResolver, regEmail, regUserName,
                    regPassword, regRepass, regMobileNumber, calendar, response, Constants.SPECIAL_MEMBER);
        } else {
            membersRegistration(resourceResolver, regEmail, regUserName,
                    regPassword, regRepass, regMobileNumber, calendar, response, Constants.MEMBER);
        }
    }


    public void membersRegistration(ResourceResolver resourceResolver, String regEmail, String regUserName, String regPassword,
                                    String regRepass, String regMobileNumber, Calendar calendar, SlingHttpServletResponse response, String memberType) throws IOException {

        try {
            Resource members = resourceResolver.getResource(Constants.ROOT_PATH_MEMBERS);
            Resource resource = resourceResolver.getResource(Constants.ROOT_PATH_MEMBERS + Constants.SLASH + regEmail);
            if (resource == null) {
                Node node = members.adaptTo(Node.class);
                Node childnodes = node.addNode(regEmail, Constants.NT_UNSTRUCTURED);
                childnodes.setProperty(Constants.CATEGORY, Constants.MEMBER);
                childnodes.setProperty(Constants.USERNAME, regUserName);
                childnodes.setProperty(Constants.PASSWORD, regPassword);
                childnodes.setProperty(Constants.CONFIRM_PASSWORD, regRepass);
                childnodes.setProperty(Constants.EMAIL, regEmail);
                childnodes.setProperty(Constants.CONTACT_NUMBER, regMobileNumber);
                childnodes.setProperty(Constants.REGISTRATION_DATE, calendar);
                resourceResolver.commit();
                response.sendRedirect(Constants.MEMBERS_SUCCESS_RESPONSE_REDIRECT);
            } else {
                response.sendRedirect(Constants.MEMBERS_FAILURE_RESPONSE_REDIRECT);
            }

            // Send Email
            emailService.sendEmail(regEmail,
                    new String[]{Constants.TO_EMAIL},
                    Constants.REGISTRATION_STATUS,
                    generateSuccessEmailContent(regUserName, memberType));

        } catch (RepositoryException e) {
            Log.error("Error during member registration: {}", e.getMessage());
        }
    }

    public void specialMembersRegistration(ResourceResolver resourceResolver, String regEmail, String regUserName, String regPassword,
                                           String regRepass, String regMobileNumber, Calendar calendar, SlingHttpServletResponse response, String memberType) throws IOException {

        try {
            Resource specialMembers = resourceResolver.getResource(Constants.ROOT_PATH_SPECIAL_MEMBERS);
            Resource resource = resourceResolver.getResource(Constants.ROOT_PATH_SPECIAL_MEMBERS + Constants.SLASH + regEmail);
            if (resource == null) {
                Node node = specialMembers.adaptTo(Node.class);
                Node childnodes = node.addNode(regEmail, Constants.NT_UNSTRUCTURED);
                childnodes.setProperty(Constants.CATEGORY, Constants.SPECIAL_MEMBER);
                childnodes.setProperty(Constants.USERNAME, regUserName);
                childnodes.setProperty(Constants.PASSWORD, regPassword);
                childnodes.setProperty(Constants.CONFIRM_PASSWORD, regRepass);
                childnodes.setProperty(Constants.EMAIL, regEmail);
                childnodes.setProperty(Constants.CONTACT_NUMBER, regMobileNumber);
                childnodes.setProperty(Constants.REGISTRATION_DATE, calendar);
                resourceResolver.commit();
                response.sendRedirect(Constants.SPECIAL_MEMBERS_SUCCESS_RESPONSE_REDIRECT);
            } else {
                response.sendRedirect(Constants.SPECIAL_MEMBERS_FAILURE_RESPONSE_REDIRECT);
            }

            // Send Email
            emailService.sendEmail(regEmail,
                    new String[]{Constants.TO_EMAIL},
                    Constants.REGISTRATION_STATUS,
                    generateSuccessEmailContent(regUserName, memberType));

        } catch (RepositoryException e) {
            Log.error("Error during special member registration: {}", e.getMessage());
        }
    }

    private String generateSuccessEmailContent(String userName, String memberType) {
        return "<html>\n" +
                "    <body style=\"margin: 20;\">\n" +
                "        <table style=\"background-color: #edf0f3;width: 100%;font-family: Arial, Helvetica, sans-serif;padding: 20px;\">\n" +
                "            <tr>\n" +
                "                <td align=\"center\" valign=\"top\">\n" +
                "                    <table style=\"background-color: #ffffff; max-width: 750px; width: 750px;\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                        <tr>\n" +
                "                            <td align=\"left\" style=\"padding: 10px;\">\n" +
                "                                <a href=\"#\"><img height=\"60px\" src=\"https://cdn.freebiesupply.com/logos/large/2x/smartstore-logo-png-transparent.png\"></a>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td>\n" +
                "                                <table style=\"background-color: #004b7c;width: 100%;\">\n" +
                "                                    <tr>\n" +
                "                                        <td style=\"text-align: center; height: 60px;font-size: 25px;color: white;box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;\">Welcome to SmartStore</td>\n" +
                "                                    </tr>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td style=\"padding: 10px;font-size: 14px;color: black;\">\n" +
                "                                <p>&nbsp;</p>\n" +
                "                                <p>Dear </p>\n" + userName +
                "                                <p>You have successfully registered for a <b>" + memberType + "</b> Account on this website. As a member, you gain access to a wide range of mobiles and laptops. You can choose from our extensive selection of products.</p>\n" +
                "                                <p> Additionally, we offer convenient discounts, exciting sales, and excellent services to enhance your shopping experience. Enjoy your membership benefits and explore our offerings to find the perfect mobile or laptop for your needs.</p>\n" +
                "                                <p> The perks of being a Special Member extend beyond product availability. We strive to provide a seamless shopping experience by offering convenient discounts that are tailored to enhance your savings.</p>\n" +
                "                                <p> So, feel free to browse, choose, and indulge in the best offers on mobiles and laptops. Your satisfaction is our priority, and we're here to make your shopping experience memorable and rewarding. </p>\n" +
                "                                <p><b>Happy shopping!</b></p>\n" +
                "                                <p align=\"center\">\n" +
                "                                   <a href=\"#\" style=\"text-decoration: none;\">\n" +
                "                                    <table><tr><td style=\"text-align: center; height: 40px;background-color: #004b7c;width: 250px;color: white; position: relative; bottom: 400px; left: 470px; box-shadow: rgba(0, 0, 0, 0.25) 0px 14px 28px, rgba(0, 0, 0, 0.22) 0px 10px 10px;\">" + memberType + "</td></tr></table>\n" +
                "                                   </a>\n" +
                "                                </p>\n" +
                "                                <p>&nbsp;</p>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "<tr>" +
                "<td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">" +
                "<div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:14px;line-height:22px;text-align:center;color:#555;\">" +
                "Please send any feedback or bug info to <a href=\"smartstore.online.m@example.com\" style=\"color:#2F67F6\">smartstore.online.m@gmail.com</a>" +
                "</div>" +
                "</td>" +
                "</tr>" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width: 100%;\" id=\"eml-footer\">\n" +
                "                        <tbody><tr>\n" +
                "                          <td style=\"text-align:center;\">\n" +
                "                          <br>\n" +
                "                          <br>\n" +
                "                            <a href=\"#\"><img height=\"32\" src=\"images/fb.png\"></a>\n" +
                "                            <a href=\"#\"><img height=\"32\" src=\"images/insta.png\"></a>\n" +
                "                          <br>\n" +
                "                          <br>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td style=\"color:#4d4d4d; text-align:center;font-size: 13px;\">\n" +
                "                            <a href=\"#\" style=\"color:#575757;\">Website</a> | <a href=\"#\" style=\"color:#575757;\">Contact us</a> | <a style=\"color:575757;\" href=\"{{unsubscribe_link}}\">Unsubscribe</a>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td style=\"font-size:12px;\">\n" +
                "                            &nbsp;\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody></table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </body>\n" +
                "</html>";

    }
}
