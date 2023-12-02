package com.smartstore.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
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

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class Description {

    @ChildResource
    private String description;

    @ChildResource
    private int pagesCount;

    @Inject
    private Page currentPage;

    @SlingObject
    private SlingHttpServletRequest request;

    @PostConstruct
    public void init() {
        String targetNodeName = "timeLinesItems";
        Session session = request.getResourceResolver().adaptTo(Session.class);
        String pagePath = currentPage.getPath();
        try {
            String targetNodePath = getTargetNodePath(pagePath, session);
            if (!targetNodePath.isEmpty()) {
                Resource resource = request.getResourceResolver().getResource(targetNodePath);
                Iterable<Resource> childs = resource.getChildren();
                pagesCount = 0;
                for (Resource child : childs) {
                    pagesCount++;
                }
            } else {
                // Handle the case where the target node does not exist
            }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTargetNodePath(String pagePath, Session session) throws RepositoryException {
        String targetResource = "";
        try {
            String queryString = "SELECT * FROM [nt:base] AS s " +
                    "WHERE ISDESCENDANTNODE(s, '" + pagePath + "') " +
                    "AND s.[sling:resourceType] = 'smartstore/components/responsive-timeline_oss'";

            // Create the query object
            QueryManager queryManager = session.getWorkspace().getQueryManager();
            Query query = queryManager.createQuery(queryString, Query.JCR_SQL2);
            QueryResult result = query.execute();

            NodeIterator nodeIterator = result.getNodes();
            while (nodeIterator.hasNext()) {
                Node pageNode = nodeIterator.nextNode();
                targetResource = pageNode.getPath();
            }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        return targetResource;
    }

    public String getDescription() {
        return description;
    }

    public int getPagesCount() {
        return pagesCount;
    }
}
