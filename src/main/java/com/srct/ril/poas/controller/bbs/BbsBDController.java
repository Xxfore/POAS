package com.srct.ril.poas.controller.bbs;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srct.ril.poas.http.CommonResponse;
import com.srct.ril.poas.http.Response;
import com.srct.ril.poas.service.bbs.BbsBDService;
import com.srct.ril.poas.utils.ServiceException;
import com.srct.ril.poas.utils.log.Log;

@RestController
@RequestMapping("/BD")
public class BbsBDController {

	@Autowired
    private BbsBDService bbsBDService;
	
	@RequestMapping("/modelinfo")
	public CommonResponse getProduct(
			@RequestParam(value="modelname", required = true) String modelName,
			@RequestParam(value="start", required = true) String startTime,
			@RequestParam(value="end", required = true) String endTime) throws ServiceException {
		Log.i("======BBsBDController=========");
        return Response.generateResponse(bbsBDService.select(modelName,startTime,endTime));
    }
	
    @RequestMapping("/modelinfo/download")
	public void getProduct(
			@RequestParam(value="modelname", required = true) String modelName,
			@RequestParam(value="start", required = true) String startTime,
			@RequestParam(value="end", required = true) String endTime,
			HttpServletRequest request, 
			HttpServletResponse response) throws ServiceException {
		String fileName = modelName+"_"+"BD"+"_"+startTime+"_"+endTime+".xls";
		response.setContentType("application/octet-stream");  
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);//默认Excel名称  
		try {
			response.flushBuffer();
			Response.generateResponse(bbsBDService.select(modelName,startTime,endTime,response));
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
