package com.smartstore.core.models;

import com.day.cq.wcm.api.Page;
import com.smartstore.core.been.TimeLine;
import com.smartstore.core.models.timeline.TimeLineItems;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.util.List;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ResponsiveTimeLineModel {

    @ChildResource
    private String showTimeLine;
//    @ChildResource
//    private String deletePropertyCSS;

    @ChildResource
    private List<TimeLineItems> timeLinesItems;

//    @Inject
//    private Page currentPage;
//
//    @SlingObject
//    private SlingHttpServletRequest request;


//    @PostConstruct
//    public void init() {
//        String targetNodeName = "timeLinesItems";
//        Session session = request.getResourceResolver().adaptTo(Session.class);
//        String pagePath = currentPage.getPath();
//        try {
//            String targetNodePath = getTargetNodePath(pagePath, session);
//            if (!targetNodePath.isEmpty()) {
//                Resource resource = request.getResourceResolver().getResource(targetNodePath + "/timeLinesItems");
//                ValueMap valueMap = resource.getValueMap();
//                Iterable<Resource> childs = resource.getChildren();
//
//                for (Resource child : childs) {
//                    String deleteProp = String.valueOf(child.getValueMap().get("pageTitle"));
//                    if (deleteProp.equals("Delete Smart Store Account")) {
//                        deletePropertyCSS = "showPopupBtn";
//                        return;
//                    } else {
//                        deletePropertyCSS = "button-6";
//                    }
//                }
//            } else {
//
//            }
//        } catch (RepositoryException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    private String getTargetNodePath(String pagePath, Session session) throws RepositoryException {
//        String targetResource = "";
//        try {
//            String queryString = "SELECT * FROM [nt:base] AS s " +
//                    "WHERE ISDESCENDANTNODE(s, '" + pagePath + "') " +
//                    "AND s.[sling:resourceType] = 'smartstore/components/responsive-timeline_oss'";
//
//            // Create the query object
//            QueryManager queryManager = session.getWorkspace().getQueryManager();
//            Query query = queryManager.createQuery(queryString, Query.JCR_SQL2);
//            QueryResult result = query.execute();
//
//            NodeIterator nodeIterator = result.getNodes();
//            while (nodeIterator.hasNext()) {
//                Node pageNode = nodeIterator.nextNode();
//                targetResource = pageNode.getPath();
//            }
//        } catch (RepositoryException e) {
//            throw new RuntimeException(e);
//        }
//        return targetResource;
//    }
//
//
//    public String getDeletePropertyCSS() {
//        return deletePropertyCSS;
//    }

    public List<TimeLineItems> getTimeLinesItems() {
        return timeLinesItems;
    }

    public String getShowTimeLine() {
        return showTimeLine;
    }

}
