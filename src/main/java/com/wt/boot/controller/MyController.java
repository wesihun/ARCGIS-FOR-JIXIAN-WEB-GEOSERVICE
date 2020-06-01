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
import com.wt.boot.pojo.DLTB;
import com.wt.boot.pojo.DltbArea;
import com.wt.boot.pojo.Menue;
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


}
