package com.schoolyard.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class ZipByAnt  {
	private final static int buffer = 1024;
      
    public static boolean doZip(String srcPathName,String zipFileName) {
    	boolean flag = false;
    	File zipFile =  new File(zipFileName);  
        File srcdir = new File(srcPathName);  
        if (!srcdir.exists()){
            return flag;       	
        }
        try {
        	Project prj = new Project();  
            Zip zip = new Zip();  
            zip.setProject(prj);  
            zip.setDestFile(zipFile);  
            FileSet fileSet = new FileSet();
            fileSet.setProject(prj);  
            fileSet.setDir(srcdir);  
            //fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹 eg:zip.setIncludes("*.java");  
            //fileSet.setExcludes(...); 排除哪些文件或文件夹  
            zip.addFileset(fileSet);  
              
            zip.execute(); 
            flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}      
        return flag;
    }
    
    public static boolean unZip(String zipFilePath, String outputDirectory) {
        boolean flag = false;
        try {
            ZipFile zipFile = new ZipFile(zipFilePath);
            Enumeration<?> e = zipFile.getEntries();
            ZipEntry zipEntry = null;
            createDirectory(outputDirectory, "");
            while (e.hasMoreElements()) {
                zipEntry = (ZipEntry) e.nextElement();
//                System.out.println("unzip " + zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    String name = zipEntry.getName().trim();
                    name = name.substring(0, name.length() - 1);
                    File f = new File(outputDirectory + File.separator + name);
                    if (!f.exists()) {
                        f.mkdir();
                    }
 
                } else {
                    String fileName = zipEntry.getName();
                    fileName = fileName.replace('\\', '/');
                    if (fileName.indexOf("/") != -1) {
                        createDirectory(outputDirectory, fileName.substring(0,
                                fileName.lastIndexOf("/")));
                        fileName = fileName
                                .substring(fileName.lastIndexOf("/") + 1);
                    }
                    File f = new File(outputDirectory + File.separator
                            + zipEntry.getName());
                    f.createNewFile();
                    InputStream in = zipFile.getInputStream(zipEntry);
                    FileOutputStream out = new FileOutputStream(f);
                    byte[] by = new byte[buffer];
                    int c;
                    while ((c = in.read(by)) != -1) {
                        out.write(by, 0, c);
                    }
                    out.flush();
                    out.close();
                    in.close();
                }
            }
            flag = true;
            zipFile.close();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }       
        return flag;
    }
    
    private static void createDirectory(String directory, String subDirectory) {
        String dir[];
        File fl = new File(directory);
        try {
            if (subDirectory == "" && fl.exists() != true) {
                fl.mkdir();
            } else if (subDirectory != "") {
                dir = subDirectory.replace('\\', '/').split("/");
                for (int i = 0; i < dir.length; i++) {
                    File subFile = new File(directory + File.separator + dir[i]);
                    if (subFile.exists() == false) {
                        subFile.mkdir();
                    }
                    directory += File.separator + dir[i];
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	String zipName1 = "E:\\test\\szhzipant2.zip";
    	String zipName2 = "E:\\test\\szhzipant3.zip";
    	ZipByAnt.doZip("E:\\test", zipName1);
    	String str = Base64Convert.fileToBase64(zipName1); 
    	System.out.println(str);
    	Base64Convert.base64ToFile(str, zipName2);
    	ZipByAnt.unZip(zipName2, "E:\\test\\test");  
    }    
}
