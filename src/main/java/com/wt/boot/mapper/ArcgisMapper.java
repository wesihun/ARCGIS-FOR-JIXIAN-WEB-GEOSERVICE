package com.wt.boot.mapper;

import com.wt.boot.pojo.DltbArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ArcgisMapper {
    @Select("select dlbm, substring(dlbm from 1 for 2) as firstcategory,sum(tbmj)/666.67 as area, dlmc from dltb group by firstcategory, dlbm, dlmc having dlbm in (${sqlIn}) ")
    public List<DltbArea> getDltbArea(@Param("sqlIn") String sqlIn);//根类统计二级分类面积

}
