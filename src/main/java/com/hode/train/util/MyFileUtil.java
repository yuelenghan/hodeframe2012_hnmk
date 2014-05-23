package com.hode.train.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFileUtil {
    /**
     * 得到路径path下的所有文件
     *
     * @param path
     * @return
     */
    public static List<File> getAllFiles(File path, List<File> fileList)
            throws Exception {
        if (path.isFile()) {
            fileList.add(path);
            return fileList;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            getAllFiles(new File(files[i].getCanonicalPath()), fileList);
        }
        return fileList;
    }

    /**
     * 得到路径path下的所有JPG文件
     *
     * @param path
     * @return
     */
    public static List<File> getAllJPGFiles(File path, List<File> fileList)
            throws Exception {
        if (path.isFile()) {
            if (path.toString().toUpperCase().endsWith("JPG")) {
                fileList.add(path);
            }
            return fileList;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            getAllJPGFiles(new File(files[i].getCanonicalPath()), fileList);
        }
        return fileList;
    }

    /**
     * 得到path路径下的所有文件夹
     *
     * @param path
     * @param fileList
     * @return
     * @throws Exception
     */
    public static List<File> getAllDirectories(File path, List<File> fileList)
            throws Exception {
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                fileList.add(files[i]);
                getAllDirectories(new File(files[i].getCanonicalPath()),
                        fileList);
            }
        }
        return fileList;
    }

    /**
     * 得到纯文本
     *
     * @param text
     * @return
     */
    public static String getPureText(String text) {
        return text.replaceAll("<([^>]*)>", "").replaceAll("\\s*|\t|\r|\n", "")
                .replaceAll("&nbsp;", "");
    }

    /**
     * 删除文件
     *
     * @param list
     */
    public static void deleteFile(List<File> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                File f = list.get(i);
                if (f.exists()) {
                    f.delete();
                }
            }
        }
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 删除文件夹
     *
     * @param path
     * @throws Exception
     */
    public static void deleteDirectory(File path) {
        if (path.isFile()) {
            path.delete();
        } else {
            File[] files = path.listFiles();
            if (files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    deleteDirectory(files[i]);
                }
            }
            path.delete();
        }
    }

    /**
     * 清空文件夹
     *
     * @param path
     */
    public static void clearDirectory(File path) {
        if (path.isFile()) {
            return;
        }
        File[] files = path.listFiles();
        if (files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                deleteDirectory(files[i]);
            }
        }

    }

    /**
     * 得到文件内容
     *
     * @param path
     * @return
     */
    public static String getFileContent(String path) {
        try {
            if (path == null || path.trim().equals("")) {
                System.out.println("路径为空！");
                return null;
            }
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("文件不存在！");
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(path)));
            String line = new String();
            StringBuffer temp = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                temp.append(line);
            }
            reader.close();
            return temp.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件没有找到！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取错误！");
        }

        return "";
    }

    /**
     * 向文件中写入内容
     *
     * @param text
     * @param path
     */
    public static void writeToFile(String text, String path) {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(text);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件写入错误！");
        }
    }

    /**
     * 复制单个文件
     *
     * @param srcFileName
     *            待复制的文件名
     * @param destFileName
     *            目标文件名
     * @param overlay
     *            如果目标文件存在，是否覆盖
     * @return 如果复制成功，则返回true，否则返回false
     */
    public static boolean copyFile(String srcFileName, String destFileName,
                                   boolean overlay) {
        // 判断原文件是否存在
        File srcFile = new File(srcFileName);
        if (!srcFile.exists()) {
            System.out.println("复制文件失败：原文件" + srcFileName + "不存在！");
            return false;
        } else if (!srcFile.isFile()) {
            System.out.println("复制文件失败：" + srcFileName + "不是一个文件！");
            return false;
        }
        // 判断目标文件是否存在
        File destFile = new File(destFileName);
        if (destFile.exists()) {
            // 如果目标文件存在，而且复制时允许覆盖。
            if (overlay) {
                // 删除已存在的目标文件，无论目标文件是目录还是单个文件
                System.out.println("目标文件已存在，准备删除它！");
                try {
                    deleteFile(destFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("复制文件失败：删除目标文件" + destFileName + "失败！");
                    return false;
                }
            } else {
                System.out.println("复制文件失败：目标文件" + destFileName + "已存在！");
                return false;
            }
        } else {
            if (!destFile.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建目录
                System.out.println("目标文件所在的目录不存在，准备创建它！");
                System.out.println(destFile.getParentFile());
                if (!destFile.getParentFile().mkdirs()) {
                    System.out.println("复制文件失败：创建目标文件所在的目录失败！");
                    return false;
                }
            }
        }
        // 准备复制文件
        int byteread = 0;// 读取的位数
        InputStream in = null;
        OutputStream out = null;
        try {
            // 打开原文件
            in = new FileInputStream(srcFile);
            // 打开连接到目标文件的输出流
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            // 一次读取1024个字节，当byteread为-1时表示文件已经读完
            while ((byteread = in.read(buffer)) != -1) {
                // 将读取的字节写入输出流
                out.write(buffer, 0, byteread);
            }
            System.out.println("复制单个文件" + srcFileName + "至" + destFileName
                    + "成功！");
            return true;
        } catch (Exception e) {
            System.out.println("复制文件失败：" + e.getMessage());
            return false;
        } finally {
            // 关闭输入输出流，注意先关闭输出流，再关闭输入流
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 校验解压缩得到的文件夹结构是否满足模版的要求
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static boolean checkExampleConstruct(String path) throws Exception {
        List<File> directoryList = new ArrayList<File>();
        directoryList = MyFileUtil.getAllDirectories(new File(path),
                directoryList);
        if (directoryList.size() == 4) {
            List<String> directoryNameList = new ArrayList<String>();
            directoryNameList.add("pic1");
            directoryNameList.add("pic2");
            directoryNameList.add("pic3");
            directoryNameList.add("pic4");

            for (int i = 0; i < directoryList.size(); i++) {
                String directoryName = directoryList.get(i).getName();
                if (directoryNameList.contains(directoryName)) {
                    directoryNameList.remove(directoryName);
                } else {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验文件是否存在
     *
     * @param filePath
     *            文件全路径
     * @return
     */
    public static boolean checkFileExists(String filePath) {
        if (new File(filePath).exists()) {
            return true;
        }
        return false;
    }

    /**
     * 校验图片名称是否合法
     *
     * @param fileName 文件名
     * @param flag 是否校验序号
     * @return
     */
    public static boolean checkFileName(String fileName, boolean flag) {
        // 文件名
        String iname = fileName.substring(0, fileName.lastIndexOf("."));
        // 扩展名
        String ename = fileName.substring(fileName.lastIndexOf(".") + 1);

        // 身份证号正则表达式匹配
        String strIDCardReg = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
        Pattern patternIdCard = Pattern.compile(strIDCardReg);
        Matcher matcherIdCard = patternIdCard.matcher(iname);

        // 序号正则表达式匹配(最多4位数字)
        String indexReg = "[1-9]\\d{0,3}";
        Pattern patternIndex = Pattern.compile(indexReg);
        Matcher matcherIndex = patternIndex.matcher(iname);

        // 扩展名为jpg或JPG
        if (ename.equals("jpg") || ename.equals("JPG")) {
            if (iname.length() > 10) {
                if (matcherIdCard.matches()) {
                    System.out.println("身份证号匹配！");
                    return true;
                }
            } else {
                if (flag) {
                    if (matcherIndex.matches()) {
                        System.out.println("序号匹配！");
                        return true;
                    }
                }

            }
        }
        return false;
    }

//	public static void main(String[] args) {
//		System.out.println(checkFileName("100.jpg", false));
//	}
}
