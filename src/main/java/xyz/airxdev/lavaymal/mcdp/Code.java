package xyz.airxdev.lavaymal.mcdp;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class Code {
    public static String FGF = "\u00a7";
    public Yaml YL = new Yaml();
    public String DExpName = "lymd";
    public String RunDir = "";
    public String MainFP = "";
    public Logger Log = Logger.getLogger("MCMP");
    public String D_SubData = "false";
    public String D_OutPut = "output_dir";
    public String D_Icon = "";
    public byte[] D_IconD ;
    public String D_Name = "";
    public String D_Version = "0";
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
            String NameSpace = (String) MP.get("Namespace");
            Log.info("NameSpace : " + NameSpace);
            M1 = (Map) MP.get("Package");
            M2 = (Map) MP.get("Build");
            this.D_SubData = (String) M2.get("SubData");
            if(this.D_SubData == null){
                this.D_SubData = "false";
            }
            this.D_OutPut = (String) M2.get("OutPut");
            if(this.D_OutPut == null){
                this.D_OutPut = "output_dir";
            }
            D_Name = (String) M1.get("Name");
            D_Icon = (String) M1.get("Icon");
            D_Version = (String) M1.get("Version");
            if(Objects.equals(D_SubData, "false")){
                if(D_Name == null | D_Icon == null){
                    Log.warning("Package : Invalid File Structure.");
                    return false;
                }
            }
            Log.info("Reading Icon File.......");
            File IIF = new File(D_Icon);
            if(!IIF.exists()){//如果文件不存在
                Log.warning("Package Error : Icon File '" + IIF.getPath() + "' Nof Found !");
                return false;
            }
            InputStream IIS = new FileInputStream(IIF);
            this.D_IconD = IIS.readAllBytes();
            Log.info("Read Icon File Finish !");
            //OUT输出头内容
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
            Log.info("Loading Resources Part !");
            M3 = (Map) MP.get("Resources");
            if(M3 == null){
                Log.info("Resources Part Not Found ! Skip !");
            }else{
                Log.info("Loading Resources Part......");
                L1 = new ArrayList<>();
                L1 = (List) M3.get("DataList");
                M5 = (Map) M3.get("Data");
                if(L1 != null){
                    for (int i = 0; i < L1.size(); i++) {
                        String TName = (String) L1.get(i);
                        Map TM;
                        TM = (Map) M5.get(TName);

                    }
                }
            }



            return true;
        } catch (IOException e) {
            Log.warning("System Error : ");
            e.printStackTrace();
            return false;
        }
    }

}
