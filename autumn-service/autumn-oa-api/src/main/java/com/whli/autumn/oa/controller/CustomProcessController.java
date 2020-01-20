package com.whli.autumn.oa.controller;

import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.oa.entity.CustomProcess;
import com.whli.autumn.oa.service.ICustomProcessService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>流程定义控制层</p>
 * @author whli
 * @date 2019/1/25 15:33
 */
@RestController
@RequestMapping(value = "/activity/customProcess")
public class CustomProcessController {

    @Autowired
    private ICustomProcessService processService;

    /**
     * 分页查询流程模型
     * @return
     * @throws Exception
     */
    @PostMapping("/listByPage")
    public ResponseBean findByPage(@RequestBody CustomProcess entity) throws Exception{
        int count = processService.getCount(entity);
        List<CustomProcess> processes = processService.listByPage(entity);
        return ResponseBean.getInstance(0,null,processes,count);
    }

    /**
     * 查询所有流程模型
     * @return
     * @throws Exception
     */
    @PostMapping("/listAll")
    public ResponseBean listAll() throws Exception{
        List<CustomProcess> processes = processService.listAll();
        return ResponseBean.success(processes);
    }

    /**
     * 删除流程定义
     * @param entity
     * @return
     */
    @PostMapping("/deleteProcess")
    public ResponseBean deleteProcess(@RequestBody CustomProcess entity) throws Exception{
        int rows = processService.deleteProcess(entity.getKey());
        if (rows > 0){
            return ResponseBean.success("删除流程定义成功");
        }
        return ResponseBean.fail("删除流程定义失败");
    }

    @PostMapping(value = "/uploadProcess")
    @ApiOperation("使用ZIP部署流程")
    public ResponseBean uploadProcess(@RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "key", required = false) String key,
                                      @RequestParam(value = "uploadFile", required = false) MultipartFile file) throws Exception {

        int rows = processService.uploadProcess(name,key,file.getInputStream());
        if (rows > 0) {
            return ResponseBean.success("导入流程定义成功！");
        }
        return ResponseBean.fail("导入流程定义失败");
    }

    /**
     * 获取流程资源
     * @param processDefinitionId
     * @param resourceType
     * @param response
     */
    @GetMapping(value = "/getResource")
    public void getResourceByDeployment(@RequestParam("processDefinitionId") String processDefinitionId,
                                         @RequestParam(value = "resourceType", required = false) String resourceType,
                                         HttpServletResponse response) throws Exception{
        processService.getResourceByDeployment(processDefinitionId,resourceType,response);
    }

    /**
     * 激活流程定义
     * @param entity
     * @return
     */
    @PostMapping(value = "/activate")
    public ResponseBean activateProcessDefinition(@RequestBody CustomProcess entity) throws Exception{
        int rows = processService.activateProcessDefinition(entity.getId());
        if (rows > 0){
            return ResponseBean.success("激活流程成功");
        }
        return ResponseBean.fail("激活流程失败");
    }

    /**
     * 挂起流程定义
     * @param entity
     * @return
     */
    @PostMapping(value = "/suspend")
    public ResponseBean suspendProcessDefinition(@RequestBody CustomProcess entity) throws Exception{
        int rows = processService.suspendProcessDefinition(entity.getId());
        if (rows > 0){
            return ResponseBean.success("挂起流程成功");
        }
        return ResponseBean.success("挂起流程失败");
    }
}
