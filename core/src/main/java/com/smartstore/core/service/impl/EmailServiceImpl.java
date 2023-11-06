package com.smartstore.core.service.impl;

import com.adobe.granite.workflow.exec.HistoryItem;
import com.adobe.granite.workflow.exec.WorkItem;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.smartstore.core.constants.Constants;
import com.smartstore.core.service.EmailService;
import com.smartstore.core.service.SmartStoreService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Component(service = EmailService.class, immediate = true)
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private static final String SUBJECT_TEXT = "Subject";

    private static final String SPECIAL_REGEX = "\\$\\{.*\\}";

    @Reference
    MessageGatewayService messageGatewayService;

    @Reference
    SmartStoreService smartStoreService;

    @Override
    public EmailService sendEmail(String addTo, String[] addCc, String setSubject, String setContent) {

        try {
            if (messageGatewayService != null) {
                MessageGateway<HtmlEmail> gateway = messageGatewayService.getGateway(HtmlEmail.class);
                HtmlEmail htmlEmail = new HtmlEmail();
                htmlEmail.addTo(addTo);
                htmlEmail.addCc(addCc);
                htmlEmail.setSubject(setSubject);
                htmlEmail.setContent(setContent, "text/html");
                gateway.send(htmlEmail);
            }
        } catch (EmailException e) {

        }
        return null;
    }

    @Override
    public void sendComment(String recipient, String comment, String action, Date date,
                            HistoryItem nextHistory, HistoryItem previousHistory, String userId,
                            WorkItem workflowItem) {

        String subject = action;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Recipient : ").append(recipient).append(Constants.BREAK_LINE_TAG);
        stringBuilder.append("Comment : ").append(comment).append(Constants.BREAK_LINE_TAG);
        stringBuilder.append("Date : ").append(comment).append(Constants.BREAK_LINE_TAG);
        stringBuilder.append("Next_History : ").append(comment).append(Constants.BREAK_LINE_TAG);
        stringBuilder.append("Previous_History : ").append(comment).append(Constants.BREAK_LINE_TAG);
        stringBuilder.append("User_Id : ").append(comment).append(Constants.BREAK_LINE_TAG);
        stringBuilder.append("Workflow_Item : ").append(comment).append(Constants.BREAK_LINE_TAG);
        sendEmail(Constants.TO_EMAIL, new String[]{Constants.CC_EMAIL}, subject, stringBuilder.toString());

    }

}
