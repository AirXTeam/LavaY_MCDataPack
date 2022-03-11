package xyz.airxdev.lavaymal.mcdp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static String FGF = "\u00a7";
    public static void main(String[] args) {
        Code MC = new Code();
        MC.Init("","temp.yaml");
        MC.Load();
        //List A = new ArrayList();
        //Tools.FindFileListWithEName(new File("output_dir"),A,".mcfunction");
        //System.out.println(A.toString());

    }
}
