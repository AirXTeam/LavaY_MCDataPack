package xyz.airxdev.lavaymal.mcdp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tools {
    //System.arraycopy()方法
    public static byte[] CBytes(byte[] bt1, byte[] bt2){
        byte[] bt3 = new byte[bt1.length+bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }
    public static void FindFileList(File dir, List<String> fileNames) {
        if (!dir.exists() || !dir.isDirectory()) {// 判断是否存在目录
            return;
        }
        String[] files = dir.list();// 读取目录下的所有目录文件信息
        for (int i = 0; i < files.length; i++) {// 循环，添加文件名或回调自身
            File file = new File(dir, files[i]);
            if (file.isFile()) {// 如果文件
                fileNames.add(dir + "/" + file.getName());// 添加文件全路径名
            } else {// 如果是目录
                FindFileList(file, fileNames);// 回调自身继续查询
            }
        }
    }

    public static void FindFileListWithEName(File dir, List<String> fileNames, String EName) {
        if (!dir.exists() || !dir.isDirectory()) {// 判断是否存在目录
            return;
        }
        FileList FL = new FileList();
        FL.A_EndsWith = EName;
        String[] files = dir.list();// 读取目录下的所有目录文件信息
        File[] files2 = dir.listFiles(FL);
        for (int i = 0; i < files2.length; i++) {
            fileNames.add(dir + "/" + files2[i].getName());
        }
        for (int i = 0; i < files.length; i++) {// 循环，添加文件名或回调自身
            File file = new File(dir, files[i]);
            if (file.isFile()) {// 如果文件
                //fileNames.add(file.getName());// 添加文件全路径名
            } else {// 如果是目录
                FindFileListWithEName(file, fileNames,EName);// 回调自身继续查询
            }
        }
    }

}
