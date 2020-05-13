package com.wt.boot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
    public String exportReportPDF(String jsonMenue, String proviceCode){//导出报表

        String filename = "c:/test" + new Date().getTime()+".pdf";

        this.createPDF(filename ,"集贤县","耕地");

        return "{" + "'" +"result" + "'" + ":" + filename + "}";

    }

    public void createPDF(String filename, String coutry, String DLCategory){//导出PDF报表
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle("example of PDF");
            document.open();

            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//设置中文样式（不设置，中文将不会显示）
            Font fontChinese_title = new Font(bfChinese, 20, Font.BOLD, BaseColor.BLACK);

            Paragraph paragraph_title = new Paragraph(coutry + DLCategory + "各地类面积报表（亩）", fontChinese_title);
            paragraph_title.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(paragraph_title);

            for(int i=0; i<2; i++){//换行
                document.add(new Paragraph(" "));
            }

            PdfPTable table = this.createTable(writer);
            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            document.close();
        }
    }

    public PdfPTable createTable(PdfWriter writer) throws Exception{
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//设置中文样式（不设置，中文将不会显示）
        Font fontChinese_title = new Font(bfChinese, 20, Font.BOLD, BaseColor.BLACK);
        Font cellFontsize = new Font(bfChinese, 12, Font.NORMAL, BaseColor.BLACK);

        PdfPTable table = new PdfPTable(2);//生成一个两列的表格

        //Image image1 = Image.getInstance("http://localhost:6080/arcgis/rest/directories/arcgisoutput/Utilities/PrintingTools_GPServer/_ags_7c2ddbb54a7241e19cb3065237d3f7b3.jpg");

        PdfPCell cell_1 = new PdfPCell(new Paragraph("地类名称",cellFontsize));
        PdfPCell cell_2 = new PdfPCell(new Paragraph("面积",cellFontsize));

        PdfPCell cell_3 = new PdfPCell(new Paragraph("水田",cellFontsize));
        PdfPCell cell_4 = new PdfPCell(new Paragraph("403361.94",cellFontsize));

        PdfPCell cell_5 = new PdfPCell(new Paragraph("水浇地",cellFontsize));
        PdfPCell cell_6 = new PdfPCell(new Paragraph("2145.34",cellFontsize));

        PdfPCell cell_7 = new PdfPCell(new Paragraph("旱田",cellFontsize));
        PdfPCell cell_8 = new PdfPCell(new Paragraph("1605692.6",cellFontsize));

        table.addCell(cell_1);
        table.addCell(cell_2);
        table.addCell(cell_3);
        table.addCell(cell_4);
        table.addCell(cell_5);
        table.addCell(cell_6);
        table.addCell(cell_7);
        table.addCell(cell_8);


        return table;
    }







}
