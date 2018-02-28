package com.srct.ril.poas.service.ai;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srct.ril.poas.ai.NLPItem;
import com.srct.ril.poas.dao.pojo.BbsBD;
import com.srct.ril.poas.dao.pojo.BbsGC;
import com.srct.ril.poas.dao.pojo.BbsJF;
import com.srct.ril.poas.dao.pojo.StoreAMZ;
import com.srct.ril.poas.dao.pojo.StoreJD;
import com.srct.ril.poas.dao.pojo.StoreTB;
import com.srct.ril.poas.dao.pojo.StoreTM;
import com.srct.ril.poas.service.bbs.BbsBDService;
import com.srct.ril.poas.service.bbs.BbsGCService;
import com.srct.ril.poas.service.bbs.BbsJFService;
import com.srct.ril.poas.service.store.StoreAMZService;
import com.srct.ril.poas.service.store.StoreJDService;
import com.srct.ril.poas.service.store.StoreTBService;
import com.srct.ril.poas.service.store.StoreTMService;
import com.srct.ril.poas.utils.ServiceException;
import com.srct.ril.poas.utils.log.Log;

@Service
public class StoreBbsService {
	@Autowired
	private StoreJDService storeJDService;
	@Autowired
	private StoreTBService storeTBService;
	@Autowired
	private StoreTMService storeTMService;
	@Autowired
	private StoreAMZService storeAMZService;
	@Autowired
	private BbsBDService bbsBDService;
	@Autowired
	private BbsGCService bbsGCService;
	@Autowired
	private BbsJFService bbsJFService;
	@Autowired
	private NLPAnalysisService nlpAnalysisService;
	
	public List<NLPItem> select(String modelName, String startTime, String endTime) throws ServiceException {
    	
		List<NLPItem> NLPItemList = new ArrayList<>();
		List<StoreJD> storeJDList = storeJDService.select(modelName,startTime,endTime, false);
		for(StoreJD it : storeJDList) {
			NLPItemList.add(nlpAnalysisService.NLPitemFactory(modelName, "JD", it));
		}
		List<StoreAMZ> storeAMZList = storeAMZService.select(modelName,startTime,endTime, false);
		for(StoreAMZ it : storeAMZList) {
			NLPItemList.add(nlpAnalysisService.NLPitemFactory(modelName, "AMZ", it));
		}
		List<StoreTB> storeTBList = storeTBService.select(modelName,startTime,endTime, false);
		for(StoreTB it : storeTBList) {
			NLPItemList.add(nlpAnalysisService.NLPitemFactory(modelName, "TB", it));
		}
		List<StoreTM> storeTMList = storeTMService.select(modelName,startTime,endTime, false);
		for(StoreTM it : storeTMList) {
			NLPItemList.add(nlpAnalysisService.NLPitemFactory(modelName, "TM", it));
		}
		List<BbsBD> bbsBDList = bbsBDService.select(modelName,startTime,endTime, false);
		for(BbsBD it : bbsBDList) {
			NLPItemList.add(nlpAnalysisService.NLPitemFactory(modelName, "BD", it));
		}
		List<BbsGC> bbsGCList = bbsGCService.select(modelName,startTime,endTime, false);
		for(BbsGC it : bbsGCList) {
			NLPItemList.add(nlpAnalysisService.NLPitemFactory(modelName, "GC", it));
		}
		List<BbsJF> bbsJFList = bbsJFService.select(modelName,startTime,endTime, false);
		for(BbsJF it : bbsJFList) {
			NLPItemList.add(nlpAnalysisService.NLPitemFactory(modelName, "JF", it));
		}
		
		nlpAnalysisService.saveExcel(modelName, NLPItemList);
        return NLPItemList;
    }
}