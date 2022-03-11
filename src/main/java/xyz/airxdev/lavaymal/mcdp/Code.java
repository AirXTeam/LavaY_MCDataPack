package xyz.airxdev.lavaymal.mcdp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import static java.nio.file.StandardCopyOption.*;
import java.util.logging.Logger;

public class Code {
    protected static String FGF = "\u00a7";
    protected Yaml YL = new Yaml();
    protected String DExpName = "lymd";
    protected String RunDir = "";
    protected String MainFP = "";
    public String A_LoggerName = "MCMP";
    protected Logger Log = Logger.getLogger(this.A_LoggerName);
    protected String D_SubData = "false";
    protected String D_OutPut = "output_dir";
    protected String D_Icon = "";
    protected byte[] D_IconD ;
    protected String D_Name = "";
    protected String D_Version = "0";
    protected DConfig DC = new DConfig();
    protected JConfig JC = new JConfig();
    protected String NameSpace;
    protected JSONArray JA = new JSONArray();
    protected JSON Json = new JSONObject();
    public boolean A_ImportFromOther = false;
    public boolean A_UseOtherOutPut = false;
    public String A_UseOtherOutPut_Name = "output_dir";
    public void Init(String RunDir,String MainFP){
        this.RunDir = RunDir;
        this.MainFP = MainFP;
    }
    public boolean Load(){
        try {
            Map M0,M1,M2,M3,M4,M5,M6,M7,M8,M9;
            Map M10,M11,M12,M13,M14,M15,M16,M17,M18,M19;
            Map M20,M21,M22,M23,M24,M25,M26,M27,M28,M29;
            Map M30,M31,M32,M33,M34,M35,M36,M37,M38,M39;
            Map M40,M41,M42,M43,M44,M45,M46,M47,M48,M49;
            Map M50;
            List L0,L1,L2,L3,L4,L5,L6,L7,L8,L9;
            List L10,L11,L12,L13,L14,L15,L16,L17,L18,L19;
            List L20,L21,L22,L23,L24,L25,L26,L27,L28,L29;
            List L30,L31,L32,L33,L34,L35,L36,L37,L38,L39;
            List L40,L41,L42,L43,L44,L45,L46,L47,L48,L49;
            List L50;
            Map<String, Object> Pros;
            File MF = new File(MainFP);
            Object OB = YL.load(new FileReader(MF));//读入文件
            Log.info("Loadinga File : " + MF.getPath());
            if(!MF.exists()){//如果文件不存在
                Log.warning("Error : File '" + MF.getPath() + "' Nof Found !");
                return false;
            }
            Map MP = (Map)OB;//转换到Map类型
            //String NameSpace = (String) MP.get("Namespace");
            this.NameSpace = (String) MP.get("Namespace");
            if(this.NameSpace == null){
                this.NameSpace = (String) MP.get("NS");
                if(this.NameSpace == null){
                    Log.warning("NameSpace Error : NameSpace was Nof Found !");
                    return false;
                }
            }
            Log.info("NameSpace : " + this.NameSpace);
            M1 = (Map) MP.get("Package");
            M2 = (Map) MP.get("Build");
            if(M2 != null) {
                this.D_SubData = (String) M2.get("SubData");
                /*if (this.D_SubData == null) {
                    if (this.A_ImportFromOther) {
                        this.D_SubData = "true";
                    } else {
                        this.D_SubData = "false";
                    }

                }*/
                this.D_OutPut = (String) M2.get("OutPut");
                /*if (A_UseOtherOutPut) {
                    this.D_OutPut = this.A_UseOtherOutPut_Name;
                } else if (this.D_OutPut == null) {
                    this.D_OutPut = "output_dir";
                }*/

                List Temp01,Temp02;
                Temp01 = (List) M2.get("FileImport");
                Temp02 = (List) M2.get("DirImport");
                Log.info("Importing Build Resources......");
                if(Temp01 == null){
                Temp01 = (List) M2.get("ImportFile");
            }
                if(Temp02 == null){
                Temp02 = (List) M2.get("ImportDir");
            }
                if(Temp01 != null){

                for (int i1 = 0; i1 < Temp01.size(); i1++) {
                    String Temp03 = (String) Temp01.get(i1);
                    String Temp03InP1 = Temp03;
                    String Temp03OutP1 = this.D_OutPut + "/" + Temp03;
                    FileChannel Temp03IC1 = null;
                    FileChannel Temp03OC1 = null;
                    File Temp03TIF1 = new File(Temp03InP1);
                    if(!Temp03TIF1.exists()){
                        Log.warning("File Error : Input File is not exists In Build Import '" + Temp03 + "' !");
                        return false;
                    }
                    File Temp03TOF1 = new File(Temp03OutP1);
                    Temp03TOF1.mkdirs();
                    Temp03TOF1.delete();
                    Temp03TOF1.createNewFile();
                    try {
                        try {
                            Temp03IC1 = new FileInputStream(new File(Temp03InP1)).getChannel();
                            Temp03OC1 = new FileOutputStream(Temp03TOF1).getChannel();
                            Temp03OC1.transferFrom(Temp03IC1, 0, Temp03IC1.size());
                        } finally {
                            //IC1.close();
                            //OC1.close();
                            if(Temp03IC1 != null){
                                Temp03IC1.close();
                            }
                            if(Temp03OC1 != null){
                                Temp03OC1.close();
                            }
                        }
                    }catch (IOException ex){
                        Log.warning("File Error : Copy File Failed In Build Import '" + Temp03 + "' !");
                        ex.printStackTrace();
                        return false;
                    }

                }
            }
                if(Temp02 != null){
                for (int i2 = 0; i2 < Temp02.size(); i2++) {
                    String Temp04 = (String) Temp02.get(i2);
                    String Temp04InP1 = Temp04;
                    String Temp04OutP1 = this.D_OutPut + "/" + Temp04;
                    /*FileChannel Temp04IC1 = null;
                    FileChannel Temp04OC1 = null;*/
                    File Temp04TIF1 = new File(Temp04InP1);
                    if(!Temp04TIF1.exists()){
                        Log.warning("File Error : Input Dir is not exists In Build Import '" + Temp04 + "' !");
                        return false;
                    }
                    File Temp04TOF1 = new File(Temp04OutP1);
                    //TOF1.mkdirs();
                    //TOF1.delete();
                    //TOF1.createNewFile();
                    try {/*
                        try {
                            //IC1 = new FileInputStream(new File(Temp04InP1)).getChannel();
                            //OC1 = new FileOutputStream(TOF1).getChannel();
                            //OC1.transferFrom(IC1, 0, IC1.size());
                            CopyFolder(new File(Temp04InP1),Temp04TIF1);
                        } finally {
                            //IC1.close();
                            //OC1.close();
                            if(Temp04IC1 != null){
                                Temp04IC1.close();
                            }
                            if(Temp04OC1 != null){
                                Temp04OC1.close();
                            }
                        }*/
                        CopyFolder(Temp04TIF1,Temp04TOF1);
                    }catch (IOException ex){
                        Log.warning("File Error : Copy File Failed In Build Import '" + Temp04 + "' !");
                        ex.printStackTrace();
                        return false;
                    }

                }
            }
                Log.info("Import Build Resources Finish !");
            }

            if (this.D_SubData == null | M2 == null) {
                if (this.A_ImportFromOther) {
                    this.D_SubData = "true";
                } else {
                    this.D_SubData = "false";
                }

            }
            if (this.A_UseOtherOutPut) {
                this.D_OutPut = this.A_UseOtherOutPut_Name;
            } else if (this.D_OutPut == null) {
                this.D_OutPut = "output_dir";
            }
            //System.out.println(D_SubData);
            if(M1 != null){
                D_Name = (String) M1.get("Name");
                D_Icon = (String) M1.get("Icon");
                D_Version = (String) M1.get("Version");
                if(Objects.equals(D_SubData, "false")){
                    if(D_Name == null | D_Icon == null){
                        Log.warning("Package : Invalid File Structure.");
                        return false;
                    }
                }
            }else{
                if(Objects.equals(D_SubData, "false")){
                    Log.warning("Error : Package Part was Not Found !");
                    return false;
                }
            }
            if(Objects.equals(D_SubData, "false")) {
                Log.info("Reading Icon File.......");
                File IIF = new File(D_Icon);
                if (!IIF.exists()) {//如果文件不存在
                    Log.warning("Package Error : Icon File '" + IIF.getPath() + "' Nof Found !");
                    return false;
                }
                InputStream IIS = new FileInputStream(IIF);
                this.D_IconD = IIS.readAllBytes();
                Log.info("Read Icon File Finish !");
                //OUT输出头内容
            }
            if(Objects.equals(D_SubData, "false")){
                //为防止出错，检测是否是子文档
                Log.info("Checking Out Put Dir......");
                File DF = new File(D_OutPut);
                if(!DF.exists()){
                    Log.info("Out Put Dir Not Found ! Make it !");
                    DF.mkdirs();
                }
                Log.info("Check Out Put Dir Finish.");
                Log.info("Preparing Write Package Head Files......");
                String HeadFileData = "{\"pack\": {\"pack_format\": #Version#,\"description\": \"#Name#\"}}";
                String HeadFileBuild = HeadFileData.replaceAll("#Version#",D_Version);
                HeadFileBuild = HeadFileBuild.replaceAll("#Name#",D_Name.replaceAll("%", FGF));
                File Wph = new File(D_OutPut + "/" + "pack.mcmeta");
                OutputStream Wpho = new FileOutputStream(Wph);
                Wpho.write(HeadFileBuild.getBytes());
                Log.info("Write 'pack.mcmeta' Finish !");
                File Wpi = new File(D_OutPut + "/" + "pack.png");
                OutputStream Wpio = new FileOutputStream(Wpi);
                Wpio.write(D_IconD);
                Log.info("Write 'pack.png' Finish !");
                Log.info("Write Head Files Finish !");
            }
            //Log.info("Loading Resources Part !");
            M3 = (Map) MP.get("Resources");
            if(M3 == null){
                M3 = (Map) MP.get("Res");
            }
            if(M3 == null){
                Log.info("Resources Part was Not Found ! Skip !");
            }else{
                Log.info("Preparing Load Resources Part......");
                L1 = new ArrayList<>();
                String TsAt = (String) M3.get("AutoList");
                M5 = (Map) M3.get("Data");
                if(M5 != null) {
                    if (TsAt == null) {
                        //System.out.println(M5.keySet());
                        L1 = List.of(M5.keySet().toArray());

                    } else {
                        if (TsAt.equals("true")) {
                            List.of(M5.keySet().toArray());
                        }
                        L1 = (List) M3.get("DataList");
                    }
                }
                //L1 = (List) M3.get("DataList");
                //M5 = (Map) M3.get("Data");
                if(L1 != null){
                    Log.info("Loading Resources Part......");
                    for (int i = 0; i < L1.size(); i++) {
                        String TName = (String) L1.get(i);
                        Map TM;
                        TM = (Map) M5.get(TName);
                        if(TM == null){
                            Log.warning("Error : Part 'Data' Not Found !");
                            return false;
                        }
                        String TP = (String) TM.get("Type");
                        if(TP != null){
                            switch (TP){
                                case "File":
                                    String InP1 = (String) TM.get("In");
                                    String OutP1 = (String) TM.get("Out");
                                    FileChannel IC1 = null;
                                    FileChannel OC1 = null;
                                    File TIF1 = new File(InP1);
                                    if(!TIF1.exists()){
                                        Log.warning("File Error : Input File is not exists In Resources '" + TName + "' !");
                                        return false;
                                    }
                                    File TOF1 = new File(this.D_OutPut + "/" + OutP1);
                                    TOF1.mkdirs();
                                    TOF1.delete();
                                    TOF1.createNewFile();
                                    try {
                                        try {
                                            IC1 = new FileInputStream(new File(InP1)).getChannel();
                                            OC1 = new FileOutputStream(TOF1).getChannel();
                                            OC1.transferFrom(IC1, 0, IC1.size());
                                        } finally {
                                            //IC1.close();
                                            //OC1.close();
                                            if(IC1 != null){
                                                IC1.close();
                                            }
                                            if(OC1 != null){
                                                OC1.close();
                                            }
                                        }
                                    }catch (IOException ex){
                                        Log.warning("File Error : Copy File Failed In Resources '" + TName + "' !");
                                        ex.printStackTrace();
                                        return false;
                                    }
                                    break;
                                    //单个文件
                                case "Dir":
                                    String InP2 = (String) TM.get("In");
                                    String OutP2 = (String) TM.get("Out");
                                    //FileChannel IC2 = null;
                                    //FileChannel OC2 = null;
                                    File TIF2 = new File(InP2);
                                    if(!TIF2.exists()){
                                        Log.warning("File Error : Input File is not exists In Resources '" + TName + "' !");
                                        return false;
                                    }
                                    File TOF2 = new File(this.D_OutPut + "/" + OutP2);
                                    //TOF2.mkdirs();
                                    //TOF2.delete();
                                    //TOF2.createNewFile();
                                    try {
                                        /*try {*/
                                            //IC2 = new FileInputStream(new File(InP2)).getChannel();
                                            //OC2 = new FileOutputStream(TOF2).getChannel();
                                            //OC2.transferFrom(IC2, 0, IC2.size());
                                        CopyFolder(new File(InP2),TOF2);
                                        /*} finally {
                                            IC2.close();
                                            OC2.close();
                                        }*/
                                    }catch (IOException ex){
                                        Log.warning("File Error : Copy File Failed In Resources '" + TName + "' !");
                                        ex.printStackTrace();
                                        return false;
                                    }
                                    break;
                                case "FileList":
                                    List Tp1,Tp2;
                                    Tp1 = (List) TM.get("InList");
                                    Tp2 = (List) TM.get("OutList");
                                    if(Tp1 == null){
                                        Tp1 = (List) TM.get("In");
                                    }
                                    if(Tp2 == null){
                                        Tp2 = (List) TM.get("Out");
                                    }
                                    //String InP3 = (String) TM.get("InList");
                                    //String OutP3 = (String) TM.get("OutList");
                                    FileChannel IC3 = null;
                                    FileChannel OC3 = null;
                                    if(Tp1 == null | Tp2 == null){
                                        Log.warning("Error : InList or OutList Not Found In Resources '" + TName + "' !");
                                        return false;
                                    }
                                    if(Tp1.size() != Tp2.size()){
                                        Log.warning("Error : Parameter mismatch In Resources '" + TName + "' !");
                                        return false;
                                    }
                                    for (int j = 0; j < Tp1.size(); j++) {
                                        File TIF3 = new File((String) Tp1.get(j));
                                        if(!TIF3.exists()){
                                            Log.warning("File Error : Input File is not exists In Resources '" + TName + "' !");
                                            return false;
                                        }
                                        File TOF3 = new File(this.D_OutPut + "/" + (String) Tp2.get(j));
                                        TOF3.mkdirs();
                                        TOF3.delete();
                                        TOF3.createNewFile();
                                        try {
                                            try {
                                                IC1 = new FileInputStream(new File((String) Tp1.get(j))).getChannel();
                                                OC1 = new FileOutputStream(TOF3).getChannel();
                                                OC1.transferFrom(IC1, 0, IC1.size());
                                            } finally {
                                                if(IC3 != null){
                                                    IC3.close();
                                                }
                                                if(OC3 != null){
                                                    OC3.close();
                                                }

                                            }
                                        }catch (IOException ex){
                                            Log.warning("File Error : Copy File Failed In Resources '" + TName + "' !");
                                            ex.printStackTrace();
                                            return false;
                                        }
                                    }
                                    break;
                                    //多个文件
                                case "DirList":
                                    List Tp3,Tp4;
                                    Tp3 = (List) TM.get("InList");
                                    Tp4 = (List) TM.get("OutList");
                                    if(Tp3 == null){
                                        Tp3 = (List) TM.get("In");
                                    }
                                    if(Tp4 == null){
                                        Tp4 = (List) TM.get("Out");
                                    }
                                    //String InP3 = (String) TM.get("InList");
                                    //String OutP3 = (String) TM.get("OutList");
                                    FileChannel IC4 = null;
                                    FileChannel OC4 = null;
                                    if(Tp3 == null | Tp4 == null){
                                        Log.warning("Error : InList or OutList Not Found In Resources '" + TName + "' !");
                                        return false;
                                    }
                                    if(Tp3.size() != Tp4.size()){
                                        Log.warning("Error : Parameter mismatch In Resources '" + TName + "' !");
                                        return false;
                                    }
                                    for (int j = 0; j < Tp3.size(); j++) {
                                        File TIF3 = new File((String) Tp3.get(j));
                                        if(!TIF3.exists()){
                                            Log.warning("File Error : Input File is not exists In Resources '" + TName + "' !");
                                            return false;
                                        }
                                        File TOF4 = new File(this.D_OutPut + "/" + (String) Tp4.get(j));
                                        //TOF4.mkdirs();
                                        //TOF3.delete();
                                        //TOF3.createNewFile();
                                        try {
                                            /*try {
                                                IC1 = new FileInputStream(new File((String) Tp3.get(j))).getChannel();
                                                OC1 = new FileOutputStream(TOF3).getChannel();
                                                OC1.transferFrom(IC1, 0, IC1.size());
                                            } finally {
                                                IC4.close();
                                                OC4.close();
                                            }*/
                                            CopyFolder(new File((String) Tp3.get(j)),TOF4);
                                        }catch (IOException ex){
                                            Log.warning("File Error : Copy File Failed In Resources '" + TName + "' !");
                                            ex.printStackTrace();
                                            return false;
                                        }
                                    }
                                    break;
                                default:
                                    Log.warning("Error : Invalid Resources Type In '" + TName + "' !");
                                    return false;
                            }
                            /*if(TP != "FileList" | TP != "DirList"){
                                String InP = (String) TM.get("In");
                                String OutP = (String) TM.get("Out");
                                if(TP == "File"){
                                    FileChannel inputChannel = null;
                                    FileChannel outputChannel = null;
                                }
                                if(TP == "Dir"){

                                }
                            }else{
                                if(TP == "FileList"){

                                }
                                if(TP == "DirList"){

                                }
                            }*/
                        }else{
                            Log.warning("Error : Can't Find Resources Type In '" + TName + "' !");
                            return false;
                        }
                    }
                }else{
                    Log.warning("Error : Can't Find Resources List !");
                    return false;
                }
            }

            M6 = (Map) MP.get("Import");
            if(M6 == null){
                Log.info("Import Part was Not Found ! Skip !");
            }else{
                Log.info("Loading Import Part......");
                Temp IPT = new Temp();
                IPT.L0 = (List) M6.get("FileList");
                IPT.L1 = (List) M6.get("DirList");
                if(IPT.L0 == null){
                    IPT.L0 = (List) M6.get("ListFile");
                }
                if(IPT.L1 == null){
                    IPT.L1 = (List) M6.get("ListDir");
                }
                if(IPT.L0 == null){
                    IPT.L0 = (List) M6.get("File");
                }
                if(IPT.L1 == null){
                    IPT.L1 = (List) M6.get("Dir");
                }
                if(IPT.L0 != null){
                    for (int T2i = 0; T2i < IPT.L0.size(); T2i++) {
                        IPT.S0 = (String) IPT.L0.get(T2i);
                        Log.info("Load File : " + IPT.S0);
                        try{
                            Code CD = new Code();
                            CD.Init(this.RunDir, IPT.S0);
                            CD.A_LoggerName = IPT.S0;
                            CD.A_UseOtherOutPut = false;
                            CD.A_UseOtherOutPut_Name = this.A_UseOtherOutPut_Name;
                            CD.A_ImportFromOther = true;
                            boolean IsFinish = CD.Load();
                            if(IsFinish){
                                Log.info("Load File Finish !");
                            }else{
                                Log.info("Load File Failed !");
                            }
                        }catch (Exception ex){
                            Log.warning("System Import Error : At Load File '" + IPT.S0 + "' : ");
                            ex.printStackTrace();
                            return false;
                        }

                    }
                }
                if(IPT.L1 != null){
                    for (int T3i = 0; T3i < IPT.L1.size(); T3i++) {
                        IPT.S1 = (String) IPT.L1.get(T3i);
                        Log.info("Load File : " + IPT.S1);
                        try{
                            FileList FL = new FileList();
                            FL.A_EndsWith = this.DExpName;
                            /*File Ifd = new File(IPT.S1);*/
                            File Ifd;
                            /*if(!Ifd.isDirectory()){
                                Log.warning("Import Error : A Directory != A File : '" + IPT.S1 + "' !");
                                return false;
                            }*/
                            char[] head = IPT.S1.toCharArray();
                            if(head.length >= 1) {
                                if (head[0] == '*') {
                                    IPT.S30 = "";
                                    for (int i = 1; i < head.length; i++) {
                                        IPT.S30 = IPT.S30 + head[i];
                                    }
                                    File Ifd2 = new File(IPT.S30);
                                    File[] Fls = Ifd2.listFiles(FL);
                                    for (int i = 0; i < Fls.length; i++) {
                                        Code CD = new Code();
                                        CD.Init(this.RunDir, Fls[i].getPath());
                                        CD.A_LoggerName = Fls[i].getPath();
                                        CD.A_UseOtherOutPut = false;
                                        CD.A_UseOtherOutPut_Name = this.A_UseOtherOutPut_Name;
                                        CD.A_ImportFromOther = true;
                                        boolean IsFinish = CD.Load();
                                        if (IsFinish) {
                                            Log.info("Load File Finish !");
                                        } else {
                                            Log.info("Load File Failed !");
                                        }
                                    }


                                }else if (head[0] == '#') {
                                    IPT.S30 = "";
                                    for (int i = 1; i < head.length; i++) {
                                        IPT.S30 = IPT.S30 + head[i];
                                    }
                                    File Ifd3 = new File(IPT.S30);
                                    //File[] Fls = Ifd2.listFiles(FL);
                                    List N = new ArrayList<>();
                                    Tools.FindFileListWithEName(Ifd3, N, this.DExpName);
                                    for (int i = 0; i < N.size(); i++) {
                                        File NF = new File((String) N.get(i));
                                        Code CD = new Code();
                                        CD.Init(this.RunDir, NF.getPath());
                                        CD.A_LoggerName = NF.getPath();
                                        CD.A_UseOtherOutPut = false;
                                        CD.A_UseOtherOutPut_Name = this.A_UseOtherOutPut_Name;
                                        CD.A_ImportFromOther = true;
                                        boolean IsFinish = CD.Load();
                                        if (IsFinish) {
                                            Log.info("Load File Finish !");
                                        } else {
                                            Log.info("Load File Failed !");
                                        }
                                    }
                                } else if (head[0] == '$') {
                                    IPT.S30 = "";
                                    for (int i = 1; i < head.length; i++) {
                                        IPT.S30 = IPT.S30 + head[i];
                                    }
                                    File Ifd3 = new File(IPT.S30);
                                    //File[] Fls = Ifd2.listFiles(FL);
                                    List N = new ArrayList<>();
                                    Tools.FindFileListWithEName(Ifd3, N, this.DExpName);
                                    for (int i = 0; i < N.size(); i++) {
                                        File NF = new File((String) N.get(i));
                                        Code CD = new Code();
                                        CD.Init(this.RunDir, NF.getPath());
                                        CD.A_LoggerName = NF.getPath();
                                        CD.A_UseOtherOutPut = false;
                                        CD.A_UseOtherOutPut_Name = this.A_UseOtherOutPut_Name;
                                        CD.A_ImportFromOther = true;
                                        boolean IsFinish = CD.Load();
                                        if (IsFinish) {
                                            Log.info("Load File Finish !");
                                        } else {
                                            Log.info("Load File Failed !");
                                        }
                                    }
                                }else if (head[0] == '~') {
                                    IPT.S30 = "";
                                    for (int i = 1; i < head.length; i++) {
                                        IPT.S30 = IPT.S30 + head[i];
                                    }
                                    File Ifd2 = new File(IPT.S30);
                                    File[] Fls = Ifd2.listFiles(FL);
                                    for (int i = 0; i < Fls.length; i++) {
                                        Code CD = new Code();
                                        CD.Init(this.RunDir, Fls[i].getPath());
                                        CD.A_LoggerName = Fls[i].getPath();
                                        CD.A_UseOtherOutPut = false;
                                        CD.A_UseOtherOutPut_Name = this.A_UseOtherOutPut_Name;
                                        CD.A_ImportFromOther = true;
                                        boolean IsFinish = CD.Load();
                                        if (IsFinish) {
                                            Log.info("Load File Finish !");
                                        } else {
                                            Log.info("Load File Failed !");
                                        }
                                    }
                                }else if (head[0] == '@') {
                                    IPT.S30 = "";
                                    for (int i = 1; i < head.length; i++) {
                                        IPT.S30 = IPT.S30 + head[i];
                                    }
                                    File Ifd30 = new File(IPT.S30);
                                    //File[] Fls = Ifd2.listFiles(FL);
                                    List N = new ArrayList<>();
                                    Tools.FindFileListWithEName(Ifd30, N, "");
                                    for (int i = 0; i < N.size(); i++) {
                                        File NF = new File((String) N.get(i));
                                        Code CD = new Code();
                                        CD.Init(this.RunDir, NF.getPath());
                                        CD.A_LoggerName = NF.getPath();
                                        CD.A_UseOtherOutPut = false;
                                        CD.A_UseOtherOutPut_Name = this.A_UseOtherOutPut_Name;
                                        CD.A_ImportFromOther = true;
                                        boolean IsFinish = CD.Load();
                                        if (IsFinish) {
                                            Log.info("Load File Finish !");
                                        } else {
                                            Log.info("Load File Failed !");
                                        }
                                    }
                                }else if (head[0] == '!'){
                                    IPT.S30 = "";
                                    for (int i = 1; i < head.length; i++) {
                                        IPT.S30 = IPT.S30 + head[i];
                                    }

                                    File Ifd9 = new File(IPT.S30);
                                    File[] Fls = Ifd9.listFiles();
                                    for (int i = 0; i < Fls.length; i++) {
                                        Code CD = new Code();
                                        CD.Init(this.RunDir, Fls[i].getPath());
                                        CD.A_LoggerName = Fls[i].getPath();
                                        CD.A_UseOtherOutPut = false;
                                        CD.A_UseOtherOutPut_Name = this.A_UseOtherOutPut_Name;
                                        CD.A_ImportFromOther = true;
                                        boolean IsFinish = CD.Load();
                                        if (IsFinish) {
                                            Log.info("Load File Finish !");
                                        } else {
                                            Log.info("Load File Failed !");
                                        }
                                    }
                                }
                            /*else{
                                    File Ifd4 = new File(IPT.S1);
                                    File[] Fls = Ifd4.listFiles(FL);
                                    for (int i = 0; i < Fls.length; i++) {
                                        Code CD = new Code();
                                        CD.Init(this.RunDir, Fls[i].getPath());
                                        CD.A_LoggerName = Fls[i].getPath();
                                        CD.A_UseOtherOutPut = false;
                                        CD.A_UseOtherOutPut_Name = this.A_UseOtherOutPut_Name;
                                        CD.A_ImportFromOther = true;
                                        boolean IsFinish = CD.Load();
                                        if (IsFinish) {
                                            Log.info("Load File Finish !");
                                        } else {
                                            Log.info("Load File Failed !");
                                        }
                                    }
                                }*/


                            }
                        }catch (Exception ex){
                            Log.warning("System Import Error : At Load File '" + IPT.S1 + "' : ");
                            ex.printStackTrace();
                            return false;
                        }

                    }
                }
            }
            Temp iT2 = new Temp();
            Temp iT3 = new Temp();
            M7 = (Map) MP.get("Data");
            if(M7 == null){
                Log.info("Data Part was Not Found ! Skip !");
            }else {
                iT2.M0 = (Map) M7.get("Functions");
                if (iT2.M0 == null) {
                    iT2.M0 = (Map) M7.get("Function");
                }
                if (iT2.M0 == null) {
                    iT2.M0 = (Map) M7.get("Func");
                }
                if (iT2.M0 != null) {
                    iT2.L0 = new ArrayList();
                    iT2.S0 = (String) iT2.M0.getOrDefault("AutoList", "true");
                    //iT2.L1 = (List) iT2.M0.get("DataList");
                    iT2.M1 = (Map) iT2.M0.get("Data");
                    if (iT2.M1 != null) {
                        if (iT2.S0 == "true") {
                            iT2.L0 = List.of(iT2.M1.keySet().toArray());
                        } else {
                            iT2.L0 = (List) iT2.M0.get("DataList");
                        }
                        //L1 = List.of(M5.keySet().toArray());
                    }

                    if (iT2.L0 != null) {
                        if (iT2.M1 != null) {
                            for (int i600 = 0; i600 < iT2.L0.size(); i600++) {
                                String FName600 = (String) iT2.L0.get(i600);
                                iT2.M10 = (Map) iT2.M1.get(FName600);
                                if (iT2.M10 != null) {
                                    iT2.S11 = (String) iT2.M10.getOrDefault("Type", "String");
                                    //iT2.S12 = (List) iT2.M10.get("Data"); //文件模式，是路径
                                    //iT2.L11 = (List) iT2.M10.get("Data"); //文本模式，是数组
                                    iT2.S9 = (String) iT2.M10.get("Name");
                                    String OTP = FName600;
                                    String AOTP = "";
                                    if (iT2.S9 != null) {
                                        OTP = iT2.S9;
                                    }
                                    AOTP = DC.FunctionPath.replaceAll("#OD#", this.D_OutPut);
                                    AOTP = AOTP.replaceAll("#NS#", this.NameSpace);
                                    AOTP = AOTP.replaceAll("#FN#", OTP);//旧的不推荐用的方式：iT2.F1 = new File(this.D_OutPut + "/" + "data");
                                    if (iT2.S11.equals("String")) {
                                        iT2.L11 = (List) iT2.M10.get("Data");
                                        if (iT2.L11 != null) {
                                            String AA2 = "";
                                            iT2.F2 = new File(AOTP);
                                            for (int i = 0; i < iT2.L11.size(); i++) {
                                                AA2 = AA2 + iT2.L11.get(i) + "\n";
                                            }
                                            if (iT2.F2.exists()) {
                                                Log.warning("File : '" + iT2.F2.getPath() + "' is exists ! OverWrite !");
                                                iT2.F2.delete();
                                            }
                                            iT2.F2.mkdirs();
                                            iT2.F2.delete();
                                            iT2.F2.createNewFile();
                                            iT2.OS0 = new FileOutputStream(iT2.F2);
                                            iT2.OS0.write(AA2.getBytes());
                                        }
                                    } else if (iT2.S11.equals("File")) {
                                        iT2.S12 = (String) iT2.M10.get("Data");
                                        if (iT2.S12 != null) {
                                            iT2.F0 = new File(iT2.S12);
                                            iT2.F1 = new File(AOTP);
                                            if (!iT2.F0.exists()) {
                                                Log.warning("File Error : '" + iT2.F0.getPath() + "' was Not Found !");
                                                return false;
                                            }
                                            if (iT2.F1.exists()) {
                                                Log.warning("File : '" + iT2.F1.getPath() + "' is exists ! OverWrite !");
                                                iT2.F1.delete();
                                            }
                                            iT2.F1.mkdirs();
                                            iT2.F1.delete();
                                            iT2.F1.createNewFile();
                                            iT2.IS3 = new FileInputStream(iT2.F0);
                                            iT2.OS4 = new FileOutputStream(iT2.F1);
                                            byte[] BT01 = iT2.IS3.readAllBytes();
                                            iT2.OS4.write(BT01);
                                        }
                                    } else if (iT2.S11.equals("FileList")) {
                                        iT2.L13 = (List) iT2.M10.get("Data");
                                        if (iT2.L13 != null) {
                                            /*iT2.F20 = new File(iT2.S13);*/
                                            iT2.F21 = new File(AOTP);
                                        /*if(iT2.F20.exists()){
                                            Log.warning("File Error : '" + iT2.F20.getPath() + "' was Not Found !");
                                            return false;
                                        }*/
                                            byte[] ABT02 = {};
                                            for (int i = 0; i < iT2.L13.size(); i++) {
                                                iT2.F20 = new File((String) iT2.L13.get(i));
                                                if (!iT2.F20.exists()) {
                                                    Log.warning("File Error : '" + iT2.F20.getPath() + "' was Not Found !");
                                                    return false;
                                                }
                                                iT2.IS23 = new FileInputStream(iT2.F20);
                                                byte[] A2 = iT2.IS23.readAllBytes();
                                                ABT02 = Tools.CBytes(ABT02, "\n".getBytes());
                                                ABT02 = Tools.CBytes(ABT02, A2);

                                            }
                                            if (iT2.F21.exists()) {
                                                Log.warning("File : '" + iT2.F21.getPath() + "' is exists ! OverWrite !");
                                                iT2.F21.delete();
                                            }
                                            iT2.F21.mkdirs();
                                            iT2.F21.delete();
                                            iT2.F21.createNewFile();


                                            //iT2.IS23 = new FileInputStream(iT2.F20);
                                            iT2.OS24 = new FileOutputStream(iT2.F21);
                                            //byte[] BT01 = iT2.IS23.readAllBytes();
                                            iT2.OS24.write(ABT02);
                                        }
                                    }
                                }

                            }
                        }
                    } else {
                        Log.warning("Error : Can't Find Function List !");
                        return false;
                    }


                }


                /*
                iT3.M0 = (Map) M7.get("Advancements");
                if(iT3.M0 == null){
                    iT3.M0 = (Map) M7.get("Advancement");
                }
                if(iT3.M0 == null){
                    iT3.M0 = (Map) M7.get("Advan");
                }
                if(iT3.M0 != null){
                    iT3.L0 = new ArrayList();
                    iT3.S0 = (String) iT3.M0.getOrDefault("AutoList","true");
                    //iT2.L1 = (List) iT2.M0.get("DataList");
                    iT3.M1 = (Map) iT3.M0.get("Data");
                    if(iT3.M1 != null) {
                        if (iT3.S0 == "true") {
                            iT3.L0 = List.of(iT3.M1.keySet().toArray());
                        }else{
                            iT3.L0 = (List) iT3.M0.get("DataList");
                        }
                        //L1 = List.of(M5.keySet().toArray());
                    }

                    if(iT3.L0 != null){
                        if(iT3.M1 != null) {
                            for (int i600 = 0; i600 < iT3.L0.size(); i600++) {
                                String FName600 = (String) iT3.L0.get(i600);
                                iT3.M10 = (Map) iT3.M1.get(FName600);
                                if(iT3.M10 != null){
                                    iT2.S9 = (String) iT2.M10.get("Name");
                                    String OTP = FName600;
                                    String AOTP = "";
                                    if(iT2.S9 != null){
                                        OTP = iT2.S9;
                                    }
                                    AOTP = DC.AdvancementsPath.replaceAll("#OD#",this.D_OutPut);
                                    AOTP = AOTP.replaceAll("#NS#",this.NameSpace);
                                    AOTP = AOTP.replaceAll("#FN#",OTP);
                                    iT3.L11 = (List) iT3.M10.get("Data");
                                    if(iT3.L11 != null){
                                        iT3.F2 = new File(AOTP);
                                        if(iT3.F2.exists()){
                                            Log.warning("File : '" + iT3.F2.getPath() + "' is exists ! OverWrite !");
                                            iT3.F2.delete();
                                        }
                                        iT3.F2.mkdirs();
                                        iT3.F2.delete();
                                        iT3.F2.createNewFile();
                                        iT3.OS0 = new FileOutputStream(iT3.F2);
                                        iT3.OS0.write({});

                                    }
                                }

                            }
                        }
                    }else{
                        Log.warning("Error : Can't Find Function List !");
                        return false;
                    }
                }*/


            }

            return true;
        } catch (IOException e) {
            Log.warning("System Error : ");
            e.printStackTrace();
            return false;
        }
    }

    protected static void CopyFolder(File sourceFolder, File destinationFolder) throws IOException
    {
        //Check if sourceFolder is a directory or file
        //If sourceFolder is file; then copy the file directly to new location
        if (sourceFolder.isDirectory())
        {
            //Verify if destinationFolder is already present; If not then create it
            if (!destinationFolder.exists())
            {
                destinationFolder.mkdir();
                //System.out.println("Directory created :: " + destinationFolder);
            }

            //Get all files from source directory
            String files[] = sourceFolder.list();

            //Iterate over all files and copy them to destinationFolder one by one
            for (String file : files)
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);

                //递归调用复制子目录
                CopyFolder(srcFile, destFile);
            }
        }
        else
        {
            //使用文件复制工具进行复制
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            //System.out.println("File copied :: " + destinationFolder);
        }
    }

}
