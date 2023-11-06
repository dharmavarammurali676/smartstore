package com.smartstore.core.config.impl;


import com.smartstore.core.config.WorkFlowConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : Dharmavaram Murali,
 * @Date : 10-03-2023,
 * @Time : 10:58
 */

@Component(service = WorkFlowConfiguration.class,
        immediate = true)

@Designate(ocd = WorkFlowConfigurationImpl.WorkFlowApproverConfiguration.class)
public class WorkFlowConfigurationImpl implements WorkFlowConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkFlowConfigurationImpl.class);

    private String firstApprover;

    private String secondaryApprover;


    @Activate
    @Modified
    protected void Activate(final WorkFlowApproverConfiguration configuration){

    this.firstApprover = configuration.getFirstApprover();
    this.secondaryApprover =configuration.getSecondaryApprover();

}

    @ObjectClassDefinition(name = "WorkFlow Approvers Configuration",description = "Here, have workflow approvers [Authors]")
    public @interface WorkFlowApproverConfiguration{

        /**
         * @return
         */
    @AttributeDefinition(
            name = "First Approver",
            description = "This is first approver",
            type = AttributeType.STRING)
    public String getFirstApprover();

   @AttributeDefinition(name = "Secondary approver",
                      description = "This is secondary approver",
                     type = AttributeType.STRING)
        public String getSecondaryApprover();
}

    @Override
    public String getFirstApprover() {
        return firstApprover;
    }

    @Override
    public String getSecondaryApprover() {
        return secondaryApprover;
    }
}
