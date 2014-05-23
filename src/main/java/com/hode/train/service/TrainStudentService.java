/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.service;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.commons.util.DateUtil;
import com.hode.framework.commons.util.StringUtil;
import com.hode.framework.component.Pagination;
import com.hode.framework.service.IbatisService;
import com.hode.train.model.TrainPersonModel;
import com.hode.train.model.TrainStudentDetailModel;
import com.hode.train.model.TrainStudentModel;
import com.hode.train.util.CommonUtil;
import com.hode.train.util.MyFileUtil;
import com.hode.train.util.MyStringUtil;

public class TrainStudentService extends IbatisService {

	private static final Logger logger = Logger
			.getLogger(TrainStudentService.class);

	public List<TrainStudentDetailModel> addImportFinish(
			TrainStudentModel trainStudentModel, String strScoreFile)
			throws Exception {
		int intYear = StringUtil.ObjectToInt(DateUtil
				.getNowDateByFormat("yyyy"));

		System.out.println(strScoreFile + "=============strScoreFile");
		String dataArr[][] = CommonUtil.getBodyListByExcelName(strScoreFile, 1);
		int intCount = 0;
		String strEndDate = DateUtil.getNowDateByFormat("yyyy-MM-dd");

		// 档案库中已经存在的人员信息
		List<TrainStudentDetailModel> existStudents = new ArrayList<TrainStudentDetailModel>();

		for (int i = 0; i < dataArr.length; i++) {
			String strIDCard = dataArr[i][8].trim(); // 身份证号
			String strIDCardReg = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$"; // 身份证号正则表达式
			Pattern pattern = Pattern.compile(strIDCardReg);
			Matcher matcher = pattern.matcher(strIDCard);

			if (!MyStringUtil.isNullStr(strIDCard) && matcher.matches()) {
				// 小写转大写,处理身份证号中带x的
				strIDCard = strIDCard.toUpperCase();

				int intIndex = 0;
				String strIndex = dataArr[i][0]; // 序号
				try {
					intIndex = Integer.parseInt(strIndex);
					if (intIndex < 0) {
						logger.error("序号必须为正整数！序号为：" + intIndex);
						continue;
					}
				} catch (Exception e) {
					logger.error("序号格式错误！序号为: " + strIndex);
					continue;
				}
				String strStudentPost = dataArr[i][1]; // 单位及部门
				String strStudentIDAddr = dataArr[i][2]; // 身份证地址
				String strStudentName = dataArr[i][3]; // 姓名
				String strStudentSex = dataArr[i][4]; // 性别
				String strStudentAge = dataArr[i][5]; // 年龄
				if (MyStringUtil.isNullStr(strStudentAge)) {
					strStudentAge = "0";
				}
				String strStudentTitles = dataArr[i][6]; // 职务
				String strStudentDegree = dataArr[i][7]; // 文化程度
				String strStudentCertCode = dataArr[i][9]; // 证书编号
				String strStudentHealth = dataArr[i][10]; // 职业健康状况
				String strStudentFirstDate = dataArr[i][11]; // 初次领证时间
				String strStudentViolate = dataArr[i][12]; // 违章作业情况
				String strStudentRelation = dataArr[i][13]; // 联系电话
				String strStudentUnitType = dataArr[i][14]; // 单位类型

				TrainStudentDetailModel trainStudentDetailModel = new TrainStudentDetailModel();
				trainStudentDetailModel.setIntIndex(intIndex);
				trainStudentDetailModel.setIntMainID(trainStudentModel
						.getIntID());
				trainStudentDetailModel.setStrTrainCount(trainStudentModel
						.getStrTrainCount());// 培训期次
				trainStudentDetailModel.setIntTrainYear(intYear);
				trainStudentDetailModel.setStrStudentName(strStudentName);// 姓名
				trainStudentDetailModel.setStrStudentSex(strStudentSex);// 性别
				trainStudentDetailModel.setStrStudentIDCode(strIDCard);// 身份证号
				trainStudentDetailModel.setStrStudentPost(strStudentPost);// 单位及部门
				trainStudentDetailModel.setStrStudentTitles(strStudentTitles);// 职务
				trainStudentDetailModel.setStrStudentDegree(strStudentDegree);// 文化程度
				trainStudentDetailModel.setStrStudentHealth(strStudentHealth);// 身体状况
				trainStudentDetailModel
						.setStrStudentRelation(strStudentRelation);// 联系方式
				trainStudentDetailModel
						.setStrStudentCertCode(strStudentCertCode);// 证书编号（仅再培训填写）
				trainStudentDetailModel.setStrStudentIDAddr(strStudentIDAddr); // 身份证地址
				trainStudentDetailModel.setIntStudentAge(Integer
						.parseInt(strStudentAge)); // 年龄
				trainStudentDetailModel
						.setStrStudentFirstDate(strStudentFirstDate); // 初次领证时间（仅再培训填写）
				trainStudentDetailModel.setStrStudentViolate(strStudentViolate); // 违章作业情况
				trainStudentDetailModel
						.setStrStudentUnitType(strStudentUnitType); // 单位类型
				trainStudentDetailModel.setStrEndDate(strEndDate);

				String strBirthDay = "1900-00-00";
				if (strIDCard.length() == 18) {
					strBirthDay = strIDCard.substring(6, 10) + "-"
							+ strIDCard.substring(10, 12) + "-"
							+ strIDCard.substring(12, 14);
				} else if (strIDCard.length() == 15) {
					strBirthDay = "19" + strIDCard.substring(6, 8) + "-"
							+ strIDCard.substring(8, 10) + "-"
							+ strIDCard.substring(10, 12);
				}
				trainStudentDetailModel.setStrBirthDay(strBirthDay);

				trainStudentDetailModel.setStrStudentPic1("");
				trainStudentDetailModel.setStrStudentPic2("");
				trainStudentDetailModel.setStrStudentPic3("");
				trainStudentDetailModel.setStrStudentPic4("");
				if (this.getRecordCount("checkBlackObject",
						trainStudentDetailModel) > 0) {
					trainStudentDetailModel.setIntIsBlackUser(1);
				} else {
					trainStudentDetailModel.setIntIsBlackUser(0);
				}

				intCount++;

				// 检查人员档案库中是否存在该人的信息
				TrainPersonModel person = (TrainPersonModel) this
						.getObjectInfo("checkPersonRecord",
								trainStudentDetailModel);
				if (person != null) {
					// 档案库中存在此人的信息
					// 把此人的信息存放在existStudents中，不插入数据库
					existStudents.add(trainStudentDetailModel);
					continue;
				} else {
					// 插入数据库
					this.insertObject("insertExcelObject",
							trainStudentDetailModel);
				}
			}
		}

		trainStudentModel.setIntTotalUserNum(intCount);

		this.updateObject("updateCount", trainStudentModel);
		MyFileUtil
				.deleteFile(new File(SysConfig.strHodeRealPath + strScoreFile));

		return existStudents;
	}

	@SuppressWarnings("unchecked")
	public List<TrainStudentModel> showStudentList(
			TrainStudentModel trainStudentModel, int intSelectType) {
		trainStudentModel.setIntStatus(intSelectType);
		return (List<TrainStudentModel>) this.getObjectList("getStudentList",
				trainStudentModel);
	}

	@SuppressWarnings("unchecked")
	public List<TrainStudentModel> showPageStudentList(
			TrainStudentModel trainStudentModel, int intSelectType,
			Pagination pagination) {
		trainStudentModel.setIntStatus(intSelectType);
		return (List<TrainStudentModel>) this.getPageObjectList(
				"showPageStudentList", trainStudentModel,
				pagination.getIntStartNum(), pagination.getIntLineNum());

	}

	/**
	 * 检查人员信息是否可以撤销
	 * 通过考核申请的人员信息不能撤销
	 * @param trainStudentModel
	 * @return
	 */
	public boolean revokeCheck(TrainStudentModel trainStudentModel) {
		int count = this.getRecordCount("revokeCheck", trainStudentModel);
		if (count == 0) {
			return true;
		}
		return false;
	}
}