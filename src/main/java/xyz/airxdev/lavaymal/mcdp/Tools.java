package xyz.airxdev.lavaymal.mcdp;

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
}
