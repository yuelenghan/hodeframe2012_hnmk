//Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
//Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
//Decompiler options: packimports(3) fieldsfirst ansi
//Source File Name:   Expand.java
//

/*** write by xdju
 */
package com.hode.train.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.types.selectors.SelectorUtils;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class UnZip extends Task {
    @SuppressWarnings("rawtypes")
    public UnZip() {
        overwrite = true;
        patternsets = new Vector();
        filesets = new Vector();
        encoding = "UTF8";
    }

    private final static Log log = LogFactory.getLog(UnZip.class);

    private boolean overwrite;
    @SuppressWarnings("rawtypes")
    private Vector patternsets;
    @SuppressWarnings("rawtypes")
    private Vector filesets;
    private String encoding;

    public void setOverwrite(boolean b) {
        overwrite = b;
    }

    @SuppressWarnings("unchecked")
    public void addPatternset(PatternSet set) {
        patternsets.addElement(set);
    }

    @SuppressWarnings("unchecked")
    public void addFileset(FileSet set) {
        filesets.addElement(set);
    }

    public void setEncoding(String encoding) {
        if ("native-encoding".equals(encoding))
            encoding = null;
        this.encoding = encoding;
    }

    @SuppressWarnings("deprecation")
	public void zip(String strFromName, String strToName) {
        File source = new File(strFromName);
        File dest = new File(strToName);

        try {
            if (dest.exists() && !dest.isDirectory())
                throw new BuildException("Dest must be a directory.",
                        getLocation());
            FileUtils fileUtils = FileUtils.newFileUtils();
            if (source != null) {
                if (source.isDirectory())
                    throw new BuildException(
                            "Src must not be a directory. Use nested filesets instead.",
                            getLocation());
                expandFile(fileUtils, source, dest);
            }
            if (filesets.size() > 0) {
                for (int j = 0; j < filesets.size(); j++) {
                    FileSet fs = (FileSet) filesets.elementAt(j);
                    DirectoryScanner ds = fs.getDirectoryScanner(getProject());
                    File fromDir = fs.getDir(getProject());
                    String files[] = ds.getIncludedFiles();
                    for (int i = 0; i < files.length; i++) {
                        File file = new File(fromDir, files[i]);
                        expandFile(fileUtils, file, dest);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("file unzip error");
        }
    }

    @SuppressWarnings("rawtypes")
    private void expandFile(FileUtils fileUtils, File srcF, File dir) {
        ZipFile zf = null;
        try {
            zf = new ZipFile(srcF, encoding);
            ZipEntry ze;
            for (Enumeration e = zf.getEntries(); e.hasMoreElements(); extractFile(
                    fileUtils, srcF, dir, zf.getInputStream(ze), ze.getName(),
                    new Date(ze.getTime()), ze.isDirectory()))
                ze = (ZipEntry) e.nextElement();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new BuildException("Error while expanding " + srcF.getPath(),
                    ioe);
        } finally {
            if (zf != null)
                try {
                    zf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    @SuppressWarnings("deprecation")
	private void extractFile(FileUtils fileUtils, File srcF, File dir,
                             InputStream compressedInputStream, String entryName,
                             Date entryDate, boolean isDirectory) throws IOException {
        File f;
        if (patternsets != null && patternsets.size() > 0) {
            String name = entryName.replace('/', File.separatorChar).replace(
                    '\\', File.separatorChar);
            boolean included = false;
            for (int v = 0; v < patternsets.size(); v++) {
                PatternSet p = (PatternSet) patternsets.elementAt(v);
                String incls[] = p.getIncludePatterns(getProject());
                if (incls == null || incls.length == 0)
                    incls = (new String[] { "**" });
                for (int w = 0; w < incls.length; w++) {
                    String pattern = incls[w].replace('/', File.separatorChar)
                            .replace('\\', File.separatorChar);
                    if (pattern.endsWith(File.separator))
                        pattern = pattern + "**";
                    included = SelectorUtils.matchPath(pattern, name);
                    if (included)
                        break;
                }

                if (!included)
                    break;
                String excls[] = p.getExcludePatterns(getProject());
                if (excls != null) {
                    for (int w = 0; w < excls.length; w++) {
                        String pattern = excls[w].replace('/',
                                File.separatorChar).replace('\\',
                                File.separatorChar);
                        if (pattern.endsWith(File.separator))
                            pattern = pattern + "**";
                        included = !SelectorUtils.matchPath(pattern, name);
                        if (!included)
                            break;
                    }

                }
            }

            if (!included)
                return;
        }
        f = fileUtils.resolveFile(dir, entryName);
        if (!overwrite && f.exists() && f.lastModified() >= entryDate.getTime()) {
            return;
        }
        try {
            File dirF = fileUtils.getParentFile(f);
            if (dirF != null)
                dirF.mkdirs();
            if (isDirectory) {
                f.mkdirs();
            } else {
                byte buffer[] = new byte[1024];
                int length = 0;
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(f);
                    while ((length = compressedInputStream.read(buffer)) >= 0)
                        fos.write(buffer, 0, length);
                    fos.close();
                    fos = null;
                } finally {
                    if (fos != null)
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
            fileUtils.setFileLastModified(f, entryDate.getTime());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            log.error("Unable to expand to file " + f.getPath());
        }
        return;
    }

    public static void main(String[] argv) {
        String strToFile = "C:/041102/2004-11-02-09-25-46/xdju";
        String strFromDir = "C:/041102/2004-11-02-09-25-46/1.zip";
        UnZip u = new UnZip();
        u.setEncoding("GB2312");
        u.setOverwrite(true);
        u.zip(strFromDir, strToFile);
    }
}