package xyz.airxdev.lavaymal.mcdp;

import java.io.File;
import java.io.FilenameFilter;

public class FileList implements FilenameFilter {//此次使用本类作为接口
    public String A_EndsWith = "";

    @Override
    public boolean accept(File Files, String Name) {//重写接口方法
        // TODO Auto-generated method stub
        return Name.endsWith(A_EndsWith);
    }
}
