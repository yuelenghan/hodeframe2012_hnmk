/*** write by xdju
 */
package com.hode.train.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.commons.util.DateUtil;
import com.hode.framework.commons.util.StringUtil;

public class CommonUtil {
    public static int getDirPath(int intID) {
        return intID / 1000;
    }

    /**
     * intStartLine:从第几行开始
     *
     */
    public static String[][] getBodyListByExcelName(String strExcelName,
                                                    int intStartLine) {
        Workbook rwb = null;
        try {
            strExcelName = SysConfig.strHodeRealPath + strExcelName;
            InputStream stream = new FileInputStream(strExcelName);
            rwb = Workbook.getWorkbook(stream);
            Sheet sheet = rwb.getSheet(0);
            Cell cell = null;
            int columns = sheet.getColumns();
            int rows = sheet.getRows();

            String dataArray[][] = new String[rows - intStartLine][columns];
            System.out.println(rows - intStartLine + 1);
            System.out.println(columns);
            int intRow = 0, intCol = 0;

            for (int i = intStartLine; i < rows; i++) {
                intCol = 0;
                for (int j = 0; j < columns; j++) {
                    cell = sheet.getCell(j, i);
                    String str00 = cell.getContents();
                    if (str00 != null && !str00.trim().equals("")) {
                        dataArray[intRow][intCol] = str00.replaceAll("'", "")
                                .trim();
                    } else {
                        dataArray[intRow][intCol] = "";
                    }
                    intCol++;
                }
                intRow++;
            }
            stream.close();

            return dataArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            rwb.close();
        }
    }

    /**
     * blnNeedFirst ：是否需要第一行，如果需要第一行， intLimitRow 显示的时候控制函数
     */
    public static String getHTMLByExcelName(String strExcelName,
                                            boolean blnNeedFirst, int intLimitRow) {
        Workbook rwb = null;
        StringBuffer strBuffer = new StringBuffer();
        try {
            strExcelName = SysConfig.strHodeRealPath + strExcelName;
            InputStream stream = new FileInputStream(strExcelName);

            rwb = Workbook.getWorkbook(stream);
            Sheet sheet = rwb.getSheet(0);
            Cell cell = null;
            int columns = sheet.getColumns();
            int rows = sheet.getRows();
            int intFirstRow = 0;
            if (blnNeedFirst) {
                strBuffer
                        .append("<TABLE borderColorDark=#000000 borderColorLight=#000000 style='border-collapse: collapse; line-height:20px' border='1' cellspacing='1' cellpadding='3'>");
                intFirstRow = 1;
            }

            for (int i = intFirstRow; i < rows; i++) {
                String strTmpval3 = sheet.getCell(0, i).getContents();
                if (strTmpval3 != null && !strTmpval3.equals("")) {
                    strBuffer.append("<tr>");
                    for (int j = 0; j < columns; j++) {
                        strBuffer.append("<td>");
                        cell = sheet.getCell(j, i);
                        String str00 = cell.getContents();
                        strBuffer.append(str00);
                        strBuffer.append("</td>");
                    }
                    strBuffer.append("</tr>");
                    if (intLimitRow > 0 && (i > intLimitRow)) {
                        strBuffer.append("<tr><td colspan=" + columns
                                + ">&nbsp;超过" + intLimitRow
                                + "条目，已经被省略</td></tr>");
                        break;
                    }
                }
            }
            if (blnNeedFirst) {
                strBuffer.append("</table>");
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {
            if (rwb != null) {
                rwb.close();
            }

        }
        return strBuffer.toString();
    }

    /**
     * 获取格式化日期
     *
     * @param strDate
     * @return
     */
    public static String strDateFormat(String strNewDate) {
        if (strNewDate != null && !strNewDate.equals("")
                && strNewDate.length() > 3) {
            int intFormatType = StringUtil.ObjectToInt(strNewDate.substring(0,
                    4));
            if (strNewDate.indexOf("/") > 0) {
                strNewDate = strNewDate.replaceAll("/", "-");
            }

            if (strNewDate.indexOf("-") > 0) {
                if (intFormatType > 0) {
                    strNewDate = DateUtil.getStringByStringAndFormat(
                            strNewDate, "yyyy-MM-dd");
                } else {
                    strNewDate = DateUtil.getStringByStringAndFormat(
                            strNewDate, "dd-MM-yyyy");
                    strNewDate = strNewDate.substring(6, 10) + "-"
                            + strNewDate.substring(3, 5) + "-"
                            + strNewDate.substring(0, 2);
                }
            }
        } else {
            strNewDate = "0000-00-00";
        }
        return strNewDate;
    }
}