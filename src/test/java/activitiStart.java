import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Test;

import java.util.List;

public class activitiStart {
    @Test
    public void testGenTable(){
        //创建processengineConfiguration对象
        ProcessEngineConfiguration configuration =
                ProcessEngineConfiguration
                        .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
      //获取流程引擎对象
      ProcessEngine processEngine= configuration.buildProcessEngine();
        System.out.println(processEngine);
    }

    /**
     * 部署流程
     */
    @Test
    public  void deployProcess(){
        //创建processengineConfiguration对象
        ProcessEngineConfiguration configuration =
                ProcessEngineConfiguration
                        .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //获取流程引擎对象
        ProcessEngine processEngine= configuration.buildProcessEngine();
        //获取repositoryService
      RepositoryService repositoryService= processEngine.getRepositoryService();
      Deployment deployment= repositoryService.createDeployment()

              .addClasspathResource("bpmn/holiday.bpmn")
              .addClasspathResource("bpmn/holiday.png")
              .name("请假申请流程2")
              .deploy();
        System.out.println("流程部署Id"+deployment.getId());
        System.out.println("流程部署名称"+deployment.getName());
    }
    /**
     * 查询流程定义
     */
    @Test
    public  void queryProcess(){
        //创建processengineConfiguration对象
        ProcessEngineConfiguration configuration =
                ProcessEngineConfiguration
                        .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //获取流程引擎对象
        ProcessEngine processEngine= configuration.buildProcessEngine();
        //获取repositoryService
      RepositoryService repositoryService= processEngine.getRepositoryService();
      //获取定义查询对象
      ProcessDefinitionQuery processDefinitionQuery= repositoryService.createProcessDefinitionQuery();

        List<ProcessDefinition> list = processDefinitionQuery
                .processDefinitionKey("holiday")
                .orderByProcessDefinitionVersion()
                .desc().list();
        for (ProcessDefinition definition:list){
            System.out.println(definition.getId());
            System.out.println(definition.getName());
            System.out.println(definition.getDeploymentId());
            System.out.println(definition.getVersion());
            System.out.println(definition.getKey());
        }
    }/**
     * 查询流程定义
     */
    @Test
    public  void deleteProcessDefinition(){
        //创建processengineConfiguration对象
        ProcessEngineConfiguration configuration =
                ProcessEngineConfiguration
                        .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //获取流程引擎对象
        ProcessEngine processEngine= configuration.buildProcessEngine();
        //获取repositoryService
      RepositoryService repositoryService= processEngine.getRepositoryService();
        //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级别删除方式，如果流程
      repositoryService.deleteDeployment("2501",false);

    }
}
