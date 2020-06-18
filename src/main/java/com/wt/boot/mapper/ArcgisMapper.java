package com.wt.boot.mapper;

import com.wt.boot.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ArcgisMapper
{
    @Select("select dlbm, substring(dlbm from 1 for 2) as firstcategory,sum(tbmj)/666.67 as area, dlmc from dltb group by firstcategory, dlbm, dlmc having dlbm in (${sqlIn}) ")
    public List<DltbArea> getDltbArea(@Param("sqlIn") String sqlIn);//根类统计二级分类面积

    @Select("select dlbm, substring(dlbm from 1 for 2) as firstcategory,sum(tbmj)/666.67 as area, dlmc from dltb where qsdwdm like CONCAT(#{provenceCode},'%') group by firstcategory, dlbm, dlmc having dlbm in (${sqlIn})")
    public List<DltbArea> getDltbAreaByprovenceCode(@Param("sqlIn") String sqlIn, @Param("provenceCode") String provenceCode);//根类统计二级分类面积,根据行政区划代码

    @Select("select objectid,bsm,ysdm,dlbm,qsdwdm,qsdwmc from dltb where dlbm in(${sqlIn}) ")
    public List<DLTB> getDLTB(@Param("sqlIn") String sqlIn);//根类二级分类获取所有图斑

    @Select("select objectid,bsm,ysdm,dlbm,qsdwdm,qsdwmc from dltb where dlbm in(${sqlIn}) and qsdwdm like CONCAT(#{provenceCode},'%')")
    public List<DLTB> getDLTBWidthCounctry(@Param("sqlIn") String sqlIn, @Param("provenceCode") String provenceCode);//根类二级分类获取所有图斑，行政区划

    @Select("select dlbm, substring(dlbm from 1 for 2) as firstcategory,sum(tbmj)/666.67 as area, dlmc from dltb where qsdwdm like CONCAT(#{provenceCode},'%') group by firstcategory, dlbm, dlmc ")
    public List<DltbArea> getAllDltbAreaByprovenceCode(@Param("provenceCode") String provenceCode);//根类统计二级分类面积,根据行政区划代码



    @Select("select * from ${table} ")
    public List<CCWJQ> getCCWJQ(@Param("table") String table);//拆除未尽区

    @Select("select * from ${table} ")
    public List<CZCDYD> getCZCDYD(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<CJDCQJX> getCJDCQJX(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<XZQ> getXZQ(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<GJGY> getGJGY(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<KFYQ> getKFYQ(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<LSYD> getLSYD(@Param("table") String table);

    @Select("select * from ${table} limit 1000 OFFSET 0")
    public List<PDT> getPDT(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<SDGY> getSDGY(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<SLGY> getSLGY(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<STBHHX> getSTBHHX(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<TTQ> getTTQ(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<XZQJX> getXZQJX(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<YJJBNTTB> getYJJBNTTB(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<ZRBHQ> getZRBHQ(@Param("table") String table);

    @Select("select * from ${table} ")
    public List<DLTB> getDltb(@Param("table") String table);



    @Select("select count(*) from ${table} ${whereSQL}")//根据条件查询总记录数，没条件传""
    public int getLayerTotalRecord(@Param("table") String table, @Param("whereSQL") String whereSQL);//拆除未尽区





}
