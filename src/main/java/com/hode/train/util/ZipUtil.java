package com.hode.train.util;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

public class ZipUtil {

    /**
     * 压缩文件夹
     */
    public static void compress(String srcPath, String zipFile) {
        File srcdir = new File(srcPath);
        if (!srcdir.exists())
            throw new RuntimeException(srcPath + "不存在！");

        Project prj = new Project();
        Zip zip = new Zip();
        zip.setProject(prj);
        if(new File(zipFile).exists()) {
            new File(zipFile).delete();
        }
        zip.setDestFile(new File(zipFile));
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcdir);
        // fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹
        // eg:zip.setIncludes("*.java");
        // fileSet.setExcludes(...); 排除哪些文件或文件夹
        zip.addFileset(fileSet);

        zip.execute();
    }

    public static void main(String[] args) {
        compress(
                "F:/server/apache-tomcat-7.0.35/webapps/hodeframe2012_hnmk/information/train/student/103",
                "E:\\103.zip");
    }
}
