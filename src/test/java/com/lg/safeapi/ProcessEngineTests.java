package com.lg.safeapi;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;

/**
 * @author amazfit
 * @date 2022-07-27 上午12:01
 **/
public class ProcessEngineTests {
    
    @Test
    public void testProcessEngine1(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(defaultProcessEngine);
    }

    @Test
    public void testProcessEngine2(){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        //定义一个流程
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("holiday.bpmn")
                .addClasspathResource("holiday.png")
                .name("请假流程")
                .key("Process_181gnrf")
                .deploy();

        System.out.println(deployment.getId());
    }
}
