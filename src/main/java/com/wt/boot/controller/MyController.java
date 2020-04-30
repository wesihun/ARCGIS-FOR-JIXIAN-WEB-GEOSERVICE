package com.wt.boot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wt.boot.mapper.ArcgisMapper;
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
    public List<DltbArea> getSecondCategoryCode(String jsonMenue) {
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

        List<DltbArea> dltbAreas = arcgisMapper.getDltbArea(sql);

        return dltbAreas;

    }







}
