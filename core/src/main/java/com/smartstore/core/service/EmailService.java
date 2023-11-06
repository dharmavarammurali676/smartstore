package com.smartstore.core.service;

import com.adobe.granite.workflow.exec.HistoryItem;
import com.adobe.granite.workflow.exec.WorkItem;
import com.day.cq.workflow.metadata.MetaDataMap;

import javax.jcr.RepositoryException;
import java.util.Date;
import java.util.Map;

public interface EmailService {


    EmailService sendEmail(String addTo, String[] addCc, String setSubject, String setContent);

    void sendComment(String recipient, String comment, String action, Date date,
                     HistoryItem nextHistory, HistoryItem previousHistory, String userId,
                     WorkItem workflowItem);
}
