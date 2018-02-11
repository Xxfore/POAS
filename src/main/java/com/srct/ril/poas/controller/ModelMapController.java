package com.srct.ril.poas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srct.ril.poas.dbconfig.DS;
import com.srct.ril.poas.dbconfig.DataSourceEnum;
import com.srct.ril.poas.http.CommonResponse;
import com.srct.ril.poas.http.Response;
import com.srct.ril.poas.service.ModelMapService;
import com.srct.ril.poas.utils.ServiceException;

@RestController
@RequestMapping("/config")
public class ModelMapController {

	@Autowired
    private ModelMapService modelMapService;
	
	private static final Logger log = LoggerFactory.getLogger(ModelMapController.class);
	
	@RequestMapping("model/{id}")
	@DS(DataSourceEnum.CONFIG)
	public CommonResponse getProduct(@PathVariable("id") int modelId) throws ServiceException {
        return Response.generateResponse(modelMapService.select(modelId));
    }
	
	@RequestMapping("/name")
	@DS(DataSourceEnum.CONFIG)
	public List<String> getProduct() throws ServiceException {
        return modelMapService.getNameList();
    }
	
	@RequestMapping("/model/add")
	@DS(DataSourceEnum.CONFIG)
	public CommonResponse addProduct(
			@RequestParam(value = "modelName", required = false)
			String modelName) throws ServiceException {
		return Response.generateResponse(modelName+"的ID为 " + modelMapService.addModel(modelName));
    }
}