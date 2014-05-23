package com.hode.train.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hode.train.model.TrainStudentDetailModel;

/**
 * 图片处理工具类
 * 
 * @author yuelenghan
 * 
 */
public class ProcessImageUtil {

	/**
	 * 导入图片信息时，处理人员和图片的对应关系
	 * 
	 * @param fileList
	 *            图片list
	 * @param studentDetailList
	 *            人员list
	 * @param listType
	 *            fileList类型 1--pic1 2--pic2 3--pic3 4--pic4
	 * 
	 * @return 0--处理成功, -1--处理出错, 1--图片少于人员, 2--图片多于人员
	 */
	public static int processImageRelation(List<File> fileList,
			List<TrainStudentDetailModel> studentDetailList, int listType) {
		String picRootPath = ConstantUtil.TRAIN_PIC_ROOT_PATH
				+ studentDetailList.get(0).getIntMainID() + "/";

		int fileListSize = fileList.size();
		int studentDetailListSize = studentDetailList.size();

		for (int i = 0; i < studentDetailListSize; i++) {
			TrainStudentDetailModel trainStudentDetailModel = (TrainStudentDetailModel) studentDetailList
					.get(i);

			if(trainStudentDetailModel.getIntIndex() == 0) {
				trainStudentDetailModel.setIntIndex(i + 1);
			}
			
			String newFileName = "";

			for (int j = 0; j < fileListSize; j++) {
				File file = fileList.get(j);
				String fileName = file.getName().trim().toUpperCase();

				String fileDirectory = file.getParent();

				if (MyFileUtil.checkFileName(fileName, true)) {
					// 更新数据库信息
					switch (listType) {
					case 1:
						if (fileName.equals(trainStudentDetailModel
								.getIntIndex() + ".JPG")) {
							// 序号，把序号换成相应的身份证号
							newFileName = picRootPath
									+ "pic1/"
									+ trainStudentDetailModel
											.getStrStudentIDCode() + ".JPG";
						} else if (fileName.equals(trainStudentDetailModel
								.getStrStudentIDCode() + ".JPG")) {
							// 不是序号，并且身份证号匹配，把jpg改为大写
							newFileName = picRootPath + "pic1/" + fileName;
						}
						break;
					case 2:
						if (fileName.equals(trainStudentDetailModel
								.getIntIndex() + ".JPG")) {
							// 序号，把序号换成相应的身份证号
							newFileName = picRootPath
									+ "pic2/"
									+ trainStudentDetailModel
											.getStrStudentIDCode() + ".JPG";
						} else if (fileName.equals(trainStudentDetailModel
								.getStrStudentIDCode() + ".JPG")) {
							// 不是序号，并且身份证号匹配，把jpg改为大写
							newFileName = picRootPath + "pic2/" + fileName;
						}
						break;
					case 3:
						if (fileName.equals(trainStudentDetailModel
								.getIntIndex() + ".JPG")) {
							// 序号，把序号换成相应的身份证号
							newFileName = picRootPath
									+ "pic3/"
									+ trainStudentDetailModel
											.getStrStudentIDCode() + ".JPG";
						} else if (fileName.equals(trainStudentDetailModel
								.getStrStudentIDCode() + ".JPG")) {
							// 不是序号，并且身份证号匹配，把jpg改为大写
							newFileName = picRootPath + "pic3/" + fileName;
						}
						break;
					case 4:
						if (fileName.equals(trainStudentDetailModel
								.getIntIndex() + ".JPG")) {
							// 序号，把序号换成相应的身份证号
							newFileName = picRootPath
									+ "pic4/"
									+ trainStudentDetailModel
											.getStrStudentIDCode() + ".JPG";
						} else if (fileName.equals(trainStudentDetailModel
								.getStrStudentIDCode() + ".JPG")) {
							// 不是序号，并且身份证号匹配，把jpg改为大写
							newFileName = picRootPath + "pic4/" + fileName;
						}
						break;
					}
				}

				if (!MyStringUtil.isNullStr(newFileName)) {
					// file.renameTo(new File(newFileName));
					MyFileUtil.copyFile(fileDirectory + "/" + fileName,
							newFileName, true);

					switch (listType) {
					case 1:
						trainStudentDetailModel.setStrStudentPic1("pic1/"
								+ trainStudentDetailModel.getStrStudentIDCode()
								+ ".JPG");
						break;
					case 2:
						trainStudentDetailModel.setStrStudentPic2("pic2/"
								+ trainStudentDetailModel.getStrStudentIDCode()
								+ ".JPG");
						break;
					case 3:
						trainStudentDetailModel.setStrStudentPic3("pic3/"
								+ trainStudentDetailModel.getStrStudentIDCode()
								+ ".JPG");
						break;
					case 4:
						trainStudentDetailModel.setStrStudentPic4("pic4/"
								+ trainStudentDetailModel.getStrStudentIDCode()
								+ ".JPG");
						break;
					}

					try {
						TrainUtil.updateStudentPic(trainStudentDetailModel);
					} catch (Exception e) {
						e.printStackTrace();
						return -1;
					}

					break;
				}
			}
		}

		if (fileListSize > studentDetailListSize) {
			return 2;
		} else if (fileListSize < studentDetailListSize) {
			return 1;
		}
		return 0;
	}

	/**
	 * 校验图片信息和人员基础信息的对应关系
	 * 
	 * @param studentDetailList
	 *            人员列表
	 * @param path
	 *            图片路径
	 * @return
	 * @throws Exception
	 */
	public static String checkImage(
			List<TrainStudentDetailModel> studentDetailList, String path)
			throws Exception {
		List<File> fileList = new ArrayList<File>();
		if (!new File(path).exists()) {
			System.out.println("=======================path = " + path);
			return "null";
		} else {
			fileList = MyFileUtil.getAllJPGFiles(new File(path), fileList);

			StringBuffer resultStr = new StringBuffer();

			if (fileList != null && fileList.size() > 0) {
				for (int i = 0; i < studentDetailList.size(); i++) {
					TrainStudentDetailModel trainStudentDetailModel = studentDetailList
							.get(i);
					String studentID = trainStudentDetailModel
							.getStrStudentIDCode();

					String filePath = path + "/" + studentID + ".JPG";
					if (!MyFileUtil.checkFileExists(filePath)) {
						resultStr.append(studentID + ",");
					}
				}

				if (!MyStringUtil.isNullStr(resultStr.toString())) {
					resultStr.deleteCharAt(resultStr.length() - 1);
					return resultStr.toString();
				} else {
					return "success";
				}
			} else {
				return "null";
			}
		}
	}
}
