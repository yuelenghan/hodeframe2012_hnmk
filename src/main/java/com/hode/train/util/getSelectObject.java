package com.hode.train.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hode.train.model.TrainApplyModel;

public class getSelectObject {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <K, V> Map trainApplySelect(int intGroupID){
		Map selectMap = new HashMap<K, V>();
		List<TrainApplyModel> list = new ArrayList();
		list = TrainUtil.getTrainApplyListByIntTrainLow(intGroupID);
		for(TrainApplyModel o:list){
			selectMap.put(o.getIntID(), o.getStrTrainCount());
		}
		return selectMap;
	}
}
