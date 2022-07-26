package com.lg.safeapi;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    ProcessEngineConfiguration configuration1;

    ProcessEngine processEngine1 ;


    RepositoryService repositoryService1;

    @BeforeEach
    public void testBegin(){
        configuration1 = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        processEngine1 = configuration1.buildProcessEngine();
        repositoryService1 = processEngine1.getRepositoryService();
    }

    @Test
    public void testProcessEngine3(){
        //查询部署流程
        Deployment deployment = repositoryService1.createDeploymentQuery().deploymentKey("Process_181gnrf").singleResult();

        //查询流程定义
        ProcessDefinition processDefinition = repositoryService1.createProcessDefinitionQuery().deploymentId("1").singleResult();

        //流程定义是否被挂起
        boolean suspended = processDefinition.isSuspended();
        System.out.println("suspended:"+suspended);

        //删除部署
//        repositoryService1.deleteDeployment("1");

        //激活流程定义
//        repositoryService1.activateProcessDefinitionById(processDefinition.getId());

        //  挂起/暂停流程定义
        repositoryService1.suspendProcessDefinitionById(processDefinition.getId());

        //  查看流程图图片
        InputStream is = repositoryService1.getResourceAsStream(deployment.getId(), processDefinition.getDiagramResourceName());

    }


    /**
     * 测试流程实例
     */
    @Test
    public void test4(){
        RuntimeService runtimeService = processEngine1.getRuntimeService();

        Map<String, Object> map = new HashMap<>();
        map.put("assignee2","zhangsan");
        map.put("assignee1","lisi");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_181gnrf", map);

        System.out.println(processInstance.getProcessInstanceId());
        System.out.println(processInstance.getProcessDefinitionId());
    }

    /**
     * 查询任务
     */
    @Test
    public void test5(){
        TaskService taskService = processEngine1.getTaskService();

        //查询待办任务
        List<Task> lisi = taskService.createTaskQuery().taskAssignee("lisi").list();

        for (Task task:lisi){
            //完成任务
            taskService.complete(task.getId());
        }
    }
}
