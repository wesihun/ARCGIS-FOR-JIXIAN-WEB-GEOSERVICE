package com.wt.boot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wt.boot.mapper.ArcgisMapper;
import com.wt.boot.pojo.DLTB;
import com.wt.boot.pojo.DltbArea;
import com.wt.boot.pojo.Menue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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







}
