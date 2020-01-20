package com.whli.autumn.oa.controller;

import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.oa.entity.CustomModel;
import com.whli.autumn.oa.service.ICustomModelService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * <p>流程模型API</p>
 * @author whli
 * @date 2019/1/16 10:21
 */
@RestController
@RequestMapping(value = "/activity/customModel")
public class CustomModelController {

    @Autowired
    private ICustomModelService modelService;

    /**
     * 分页查询流程模型
     * @return
     * @throws Exception
     */
    @PostMapping("/listByPage")
    public ResponseBean findByPage(@RequestBody CustomModel entity) throws Exception{
        int count = modelService.getCount(entity);
        List<Model> models = modelService.listByPage(entity);
        return ResponseBean.getInstance(0,null,models,count);
    }

    /**
     * 查询所有流程模型
     * @return
     * @throws Exception
     */
    @PostMapping("/listAll")
    public ResponseBean listAll() throws Exception{
        List<Model> models = modelService.listAll();
        return ResponseBean.success(models);
    }

    /**
     * 新建一个空模型
     */
    @PostMapping("/addModel")
    public ResponseBean addModel(@RequestBody CustomModel entity) throws IOException {
        String modelId = modelService.addModel(entity);
        if (StringUtils.isNotEmpty(modelId)){
            return ResponseBean.success("新建流程模型成功",modelId);
        }
        return ResponseBean.fail("新建流程模型失败");
    }

    /**
     * 删除模型
     * @param entity
     * @return
     * @throws IOException
     */
    @PostMapping("/deleteModel")
    public ResponseBean deleteModel(@RequestBody CustomModel entity) throws IOException {
        int rows = modelService.deleteModel(entity);
        if (rows > 0){
            return ResponseBean.success("删除流程模型成功");
        }
        return ResponseBean.fail("删除流程模型失败");
    }

    /**
     * 发布流程模型
     */
    @PostMapping("/deployModel")
    public ResponseBean deployModel(@RequestBody CustomModel entity) throws Exception {
        int rows = modelService.deployModel(entity);
        if (rows > 0){
            return ResponseBean.success("发布流程模型成功");
        }
        return ResponseBean.fail("发布流程模型失败");
    }
}
