package com.wt.boot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.wt.boot.Config;
import com.wt.boot.mapper.ArcgisMapper;
import com.wt.boot.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials="true")
public class MyController {

    @Autowired
    ArcgisMapper arcgisMapper;

    @Autowired
    Config config;

    @RequestMapping(value = "getSecondCategoryCode", produces = "application/json;charset=utf-8")
    public List<DltbArea> getSecondCategoryCode(String jsonMenue, String proviceCode) {//各地类面积
        ArrayList<Menue> menues = JSON.parseObject(jsonMenue, new TypeReference<ArrayList<Menue>>(){});
        String sql = "";

        for(int i=0; i<menues.size(); i++){
            Menue menue = menues.get(i);

            if(i == menues.size()-1){
                sql += "'" + menue.getSecondcategoryCode() + "'" ;
            }else{
                sql += "'" + menue.getSecondcategoryCode() + "'" + ", ";
            }
        }

        List<DltbArea> dltbAreas;

        if(proviceCode.equals("000000")){//集贤县
            dltbAreas = arcgisMapper.getDltbArea(sql);
        }else {
            dltbAreas = arcgisMapper.getDltbAreaByprovenceCode(sql, proviceCode);
        }

        return dltbAreas;

    }

    @RequestMapping(value = "getDLTB", produces = "application/json;charset=utf-8")
    public List<DLTB> getDLTB(String jsonMenue, String proviceCode) {//所有地类图斑
        ArrayList<Menue> menues = JSON.parseObject(jsonMenue, new TypeReference<ArrayList<Menue>>(){});
        String sql = "";

        for(int i=0; i<menues.size(); i++){
            Menue menue = menues.get(i);

            if(i == menues.size()-1){
                sql += "'" + menue.getSecondcategoryCode() + "'" ;
            }else{
                sql += "'" + menue.getSecondcategoryCode() + "'" + ", ";
            }
        }

        List<DLTB> list;

        if(proviceCode.equals("000000")){//集贤县
            list = arcgisMapper.getDLTB(sql);
        }else {
            list = arcgisMapper.getDLTBWidthCounctry(sql, proviceCode);
        }

        return list;

    }

    @RequestMapping(value = "exportReportPDF", produces = "application/json;charset=utf-8")
    public String exportReportPDF(String jsonMenue, String proviceCode, String rightMenueName, String menuename){//导出报表
        String firstFilename = config.getFile_dir();
        String lastFilename = new Date().getTime()+".pdf";
        String province = "集贤县";
        String coutry="";
        String DLCategory=menuename;

        ArrayList<Menue> menues = JSON.parseObject(jsonMenue, new TypeReference<ArrayList<Menue>>(){});
        List<DltbArea> dltbAreas;
        String sql = "";

        for(int i=0; i<menues.size(); i++){//解析二级分类
            Menue menue = menues.get(i);

            if(i == menues.size()-1){
                sql += "'" + menue.getSecondcategoryCode() + "'" ;
            }else{
                sql += "'" + menue.getSecondcategoryCode() + "'" + ", ";
            }
        }

        if(proviceCode.equals("000000")){//集贤县
            dltbAreas = arcgisMapper.getDltbArea(sql); //分组统计面积
        }else {
            dltbAreas = arcgisMapper.getDltbAreaByprovenceCode(sql, proviceCode);
            coutry = rightMenueName;
        }


        this.createPDF(firstFilename+lastFilename ,province,coutry,DLCategory, dltbAreas);

        String resutl = config.getReport_url()+":" + config.getPort() + "/" + lastFilename;

        JSONObject jt = new JSONObject();
        jt.put("result", resutl);

        return jt.toJSONString();

    }

    public void createPDF(String filename, String province, String coutry, String DLCategory, List<DltbArea> dltbAreas){//导出PDF报表
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle("PDF file");
            document.open();

            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//设置中文样式（不设置，中文将不会显示）
            Font fontChinese_title = new Font(bfChinese, 20, Font.BOLD, BaseColor.BLACK);

            Paragraph paragraph_title = new Paragraph(province + coutry + DLCategory + "各地类面积报表（亩）", fontChinese_title);
            paragraph_title.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(paragraph_title);

            for(int i=0; i<2; i++){//换行
                document.add(new Paragraph(" "));
            }

            PdfPTable table = this.createTable(writer, dltbAreas);
            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            document.close();
        }
    }

    public PdfPTable createTable(PdfWriter writer, List<DltbArea> dltbAreas) throws Exception{
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//设置中文样式（不设置，中文将不会显示）
        Font fontChinese_title = new Font(bfChinese, 20, Font.BOLD, BaseColor.BLACK);
        Font cellFontsize = new Font(bfChinese, 12, Font.NORMAL, BaseColor.BLACK);

        PdfPTable table = new PdfPTable(2);//生成一个两列的表格

        //Image image1 = Image.getInstance("http://localhost:6080/arcgis/rest/directories/arcgisoutput/Utilities/PrintingTools_GPServer/_ags_7c2ddbb54a7241e19cb3065237d3f7b3.jpg");

        PdfPCell cell_1 = new PdfPCell(new Paragraph("地类名称",cellFontsize));
        PdfPCell cell_2 = new PdfPCell(new Paragraph("面积",cellFontsize));

        for(int i=0; i<dltbAreas.size(); i++){
            DltbArea dltbArea = dltbAreas.get(i);

            PdfPCell cell_3 = new PdfPCell(new Paragraph(dltbArea.getDlmc(),cellFontsize));
            PdfPCell cell_4 = new PdfPCell(new Paragraph(dltbArea.getArea()+"",cellFontsize));

            table.addCell(cell_3);
            table.addCell(cell_4);
        }

        return table;
    }


    @RequestMapping(value = "getAllDltbAreaByProvinceCode", produces = "application/json;charset=utf-8")
    public List<DltbArea> getAllDltbAreaByProvinceCode(String proviceCode) {//某行政区所有地类面积

        List<DltbArea> dltbAreas;

        if(proviceCode.equals("000000")) {//集贤县
            proviceCode = "230521";
        }

        dltbAreas = arcgisMapper.getAllDltbAreaByprovenceCode(proviceCode);

        return dltbAreas;

    }

    @RequestMapping(value = "printMap", produces = "application/json;charset=utf-8")
    public String printMap(String title, String paper, String format, String url){//打印地图
        String firstFilename = config.getFile_dir();
        String lastFilename = new Date().getTime()+".pdf";

        String result = config.getReport_url()+":" + config.getPort() + "/" + lastFilename;

        this.createPDFFileForPringMap(firstFilename+lastFilename, title, url);//构建PDF文件

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);

        return jsonObject.toJSONString();
    }

    public void createPDFFileForPringMap(String fileName, String title, String imageUrl) {//构建打印地图PDF文件
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.addTitle("PDF file");
            document.open();

            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//设置中文样式（不设置，中文将不会显示）
            Font fontChinese_title = new Font(bfChinese, 20, Font.BOLD, BaseColor.BLACK);

            Paragraph paragraph_title = new Paragraph(title, fontChinese_title);
            paragraph_title.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(paragraph_title);

            /*for(int i=0; i<2; i++){//换行
                document.add(new Paragraph(" "));
            }*/

            PdfPTable table = new PdfPTable(1);//生成一个两列的表格
            table.getDefaultCell().setBorder(0);

            Image image = Image.getInstance(imageUrl);
            table.addCell(image);

            document.add(table);
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            document.close();
        }
    }


    @RequestMapping(value = "getLayerData", produces = "application/json;charset=utf-8")
    public String getLayerData(String jsonTree)//获取图层数据
    {
        SpecialMenue specialMenue = JSON.parseObject(jsonTree, new TypeReference<SpecialMenue>(){});

        if(null == specialMenue.getTablename() || specialMenue.getTablename().equals("")) return null;

        List list = null;

        if(specialMenue.getTablename().contains("ccwjq")){list = arcgisMapper.getCCWJQ(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("czcdyd")){list = arcgisMapper.getCZCDYD(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("cjdcqjx")){list = arcgisMapper.getCJDCQJX(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("xzq")){list = arcgisMapper.getXZQ(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("gjgy")){list = arcgisMapper.getGJGY(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("kfyq")){list = arcgisMapper.getKFYQ(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("lsyd")){list = arcgisMapper.getLSYD(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("pdt")){list = arcgisMapper.getPDT(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("sdgy")){list = arcgisMapper.getSDGY(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("slgy")){list = arcgisMapper.getSLGY(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("stbhhx")){list = arcgisMapper.getSTBHHX(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("ttq")){list = arcgisMapper.getTTQ(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("xzqjx")){list = arcgisMapper.getXZQJX(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("yjjbnttb")){list = arcgisMapper.getYJJBNTTB(specialMenue.getTablename());}
        if(specialMenue.getTablename().contains("zrbhq")){list = arcgisMapper.getZRBHQ(specialMenue.getTablename());}



        JSONObject jt = new JSONObject();
        jt.put("result", list);


        return jt.toJSONString();
    }


    @RequestMapping(value = "getAnalysisData", produces = "application/json;charset=utf-8")
    public String getAnalysisData(String jsonTree, String currentPage, String pageSize, String serchFileName, String serchFileValue)//获取统计分析图层数据，分页方案
    {
        AnalysisMenue analysisMenue = JSON.parseObject(jsonTree, new TypeReference<AnalysisMenue>(){});

        if(null==analysisMenue.getTablename() || analysisMenue.getTablename().equals("")){return null;}

        String whereSQL = "";
        String limitSQL = "";
        List list = null;

        if((null!=currentPage && !"".equals(currentPage)) && (null!=pageSize && !"".equals(pageSize))){limitSQL = constructLimitSQL(Integer.parseInt(currentPage), Integer.parseInt(pageSize));}
        whereSQL = this.constructWhereSQL(serchFileName, serchFileValue);//构造whereSQL

        if(analysisMenue.getTablename().contains("ccwjq")){list = arcgisMapper.getCCWJQ_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("czcdyd")){list = arcgisMapper.getCZCDYD_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("cjdcqjx")){list = arcgisMapper.getCJDCQJX_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("xzq")){list = arcgisMapper.getXZQ_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("gjgy")){list = arcgisMapper.getGJGY_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("kfyq")){list = arcgisMapper.getKFYQ_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("lsyd")){list = arcgisMapper.getLSYD_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("pdt")){list = arcgisMapper.getPDT_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("sdgy")){list = arcgisMapper.getSDGY_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("slgy")){list = arcgisMapper.getSLGY_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("stbhhx")){list = arcgisMapper.getSTBHHX_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("ttq")){list = arcgisMapper.getTTQ_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("xzqjx")){list = arcgisMapper.getXZQJX_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("yjjbnttb")){list = arcgisMapper.getYJJBNTTB_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("zrbhq")){list = arcgisMapper.getZRBHQ_1(analysisMenue.getTablename(),whereSQL,limitSQL);}
        if(analysisMenue.getTablename().contains("dltb")){list = arcgisMapper.getDltb_1(analysisMenue.getTablename(),whereSQL,limitSQL);}

        JSONObject jt = new JSONObject();
        jt.put("result", list);

        return jt.toJSONString();
    }



    @RequestMapping(value = "getAnalysisTotalRecord", produces = "application/json;charset=utf-8")
    public String getAnalysisTotalRecord(String jsonTree, String serchFileName, String serchFileValue)//获取统计分析图层数据总条数
    {
        AnalysisMenue analysisMenue = JSON.parseObject(jsonTree, new TypeReference<AnalysisMenue>(){});

        if(null==analysisMenue.getTablename() || analysisMenue.getTablename().equals("")){return null;}

        String whereSQL = "";
        float totalRecord = 0;

        whereSQL = this.constructWhereSQL(serchFileName, serchFileValue);//构造whereSQL


        if(analysisMenue.getTablename().contains("ccwjq")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("czcdyd")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("cjdcqjx")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("xzq")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("gjgy")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("kfyq")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("lsyd")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("pdt")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("sdgy")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("slgy")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("stbhhx")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("ttq")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("xzqjx")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("yjjbnttb")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("zrbhq")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}
        if(analysisMenue.getTablename().contains("dltb")){totalRecord = arcgisMapper.getLayerTotalRecord(analysisMenue.getTablename(), whereSQL);}

        JSONObject jt = new JSONObject();
        jt.put("result", totalRecord);

        return jt.toJSONString();
    }

    public String constructWhereSQL(String serchFileName, String serchFileValue)//构造whereSQL语句
    {
        String whereSQL = "";

        if((null!=serchFileName && !serchFileName.equals("")) && (null!=serchFileValue && !serchFileValue.equals("")))//有限制条件
        {
            if(serchFileName.equals("标识码"))
            {
                whereSQL +="where bsm like '%" + serchFileValue + "%'";
            }
            if(serchFileName.equals("要素代码"))
            {
                whereSQL +="where ysdm like '%" + serchFileValue + "%'";
            }
        }

        return whereSQL;
    }

    public String constructLimitSQL(int currentPage, int pageSize)//构造whereSQL语句
    {
        String limitSQL = "";

        limitSQL += "limit " + pageSize  + " OFFSET " + (currentPage-1)*pageSize;

        return limitSQL;
    }

    @RequestMapping(value = "getAnalysisTotalArea", produces = "application/json;charset=utf-8")
    public String getAnalysisTotalArea(String jsonTree)//获取统计分析图层数据总面积
    {
        AnalysisMenue analysisMenue = JSON.parseObject(jsonTree, new TypeReference<AnalysisMenue>(){});

        if(null==analysisMenue.getTablename() || analysisMenue.getTablename().equals("")){return null;}

        float totalArea = 0;
        List list = null;

        if(analysisMenue.getTablename().contains("ccwjq")) { list = arcgisMapper.getCCWJQ(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { CCWJQ ccwjq = (CCWJQ) list.get(i);totalArea += ccwjq.getArea(); } }
        if(analysisMenue.getTablename().contains("czcdyd")) { list = arcgisMapper.getCZCDYD(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { CZCDYD czcdyd = (CZCDYD) list.get(i);totalArea += czcdyd.getArea(); } }
        if(analysisMenue.getTablename().contains("cjdcqjx")) { list = arcgisMapper.getCJDCQJX(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { CJDCQJX cjdcqjx = (CJDCQJX) list.get(i);totalArea += cjdcqjx.getArea(); } }
        if(analysisMenue.getTablename().contains("xzq")) { list = arcgisMapper.getXZQ(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { XZQ xzq = (XZQ) list.get(i);totalArea += xzq.getArea(); } }
        if(analysisMenue.getTablename().contains("gjgy")) { list = arcgisMapper.getGJGY(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { GJGY gjgy = (GJGY) list.get(i);totalArea += gjgy.getArea(); } }
        if(analysisMenue.getTablename().contains("kfyq")){list = arcgisMapper.getKFYQ(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { KFYQ kfyq = (KFYQ) list.get(i);totalArea += kfyq.getArea(); } }
        if(analysisMenue.getTablename().contains("lsyd")){list = arcgisMapper.getLSYD(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { LSYD lsyd = (LSYD) list.get(i);totalArea += lsyd.getArea(); } }
        if(analysisMenue.getTablename().contains("pdt")){list = arcgisMapper.getPDT(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { PDT pdt = (PDT) list.get(i);totalArea += pdt.getArea(); } }
        if(analysisMenue.getTablename().contains("sdgy")){list = arcgisMapper.getSDGY(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { SDGY sdgy = (SDGY) list.get(i);totalArea += sdgy.getArea(); } }
        if(analysisMenue.getTablename().contains("slgy")){list = arcgisMapper.getSLGY(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { SLGY slgy = (SLGY) list.get(i);totalArea += slgy.getArea(); } }
        if(analysisMenue.getTablename().contains("stbhhx")){list = arcgisMapper.getSTBHHX(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { STBHHX stbhhx = (STBHHX) list.get(i);totalArea += stbhhx.getArea(); } }
        if(analysisMenue.getTablename().contains("ttq")){list = arcgisMapper.getTTQ(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { TTQ ttq = (TTQ) list.get(i);totalArea += ttq.getArea(); } }
        if(analysisMenue.getTablename().contains("xzqjx")){list = arcgisMapper.getXZQJX(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { XZQJX xzqjx = (XZQJX) list.get(i);totalArea += xzqjx.getArea(); } }
        if(analysisMenue.getTablename().contains("yjjbnttb")){list = arcgisMapper.getYJJBNTTB(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { YJJBNTTB yjjbnttb = (YJJBNTTB) list.get(i);totalArea += yjjbnttb.getArea(); } }
        if(analysisMenue.getTablename().contains("zrbhq")){list = arcgisMapper.getZRBHQ(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { ZRBHQ zrbhq = (ZRBHQ) list.get(i);totalArea += zrbhq.getArea(); } }
        if(analysisMenue.getTablename().contains("dltb")){list = arcgisMapper.getDltb(analysisMenue.getTablename());for(int i=0; i<list.size(); i++) { DLTB dltb = (DLTB) list.get(i);totalArea += dltb.getArea(); } }

        JSONObject jt = new JSONObject();
        jt.put("result", totalArea);

        return jt.toJSONString();
    }






}
