package com.wt.boot.pojo;

import java.util.Date;

public class GJGY
{
    private int objectid;
    private String bsm;
    private String ysdm;
    private String bhqmc;
    private String bhqjb;
    private String pzjg;
    private Date pzsj;
    private float bhqmj;
    private String bz;
    private String shape;
    private float area;//统一转换字段名
    private String name;//统一转换名称字段名

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public float getArea()
    {
        return area;
    }

    public void setArea(float area)
    {
        this.area = area;
    }

    public int getObjectid()
    {
        return objectid;
    }

    public void setObjectid(int objectid)
    {
        this.objectid = objectid;
    }

    public String getBsm()
    {
        return bsm;
    }

    public void setBsm(String bsm)
    {
        this.bsm = bsm;
    }

    public String getYsdm()
    {
        return ysdm;
    }

    public void setYsdm(String ysdm)
    {
        this.ysdm = ysdm;
    }

    public String getBhqmc()
    {
        return bhqmc;
    }

    public void setBhqmc(String bhqmc)
    {
        this.bhqmc = bhqmc;
        this.setName(bhqmc);
    }

    public String getBhqjb()
    {
        return bhqjb;
    }

    public void setBhqjb(String bhqjb)
    {
        this.bhqjb = bhqjb;
    }

    public String getPzjg()
    {
        return pzjg;
    }

    public void setPzjg(String pzjg)
    {
        this.pzjg = pzjg;
    }

    public Date getPzsj()
    {
        return pzsj;
    }

    public void setPzsj(Date pzsj)
    {
        this.pzsj = pzsj;
    }

    public float getBhqmj()
    {
        return bhqmj;
    }

    public void setBhqmj(float bhqmj)
    {
        this.bhqmj = bhqmj;
        this.setArea(bhqmj);
    }

    public String getBz()
    {
        return bz;
    }

    public void setBz(String bz)
    {
        this.bz = bz;
    }

    public String getShape()
    {
        return shape;
    }

    public void setShape(String shape)
    {
        this.shape = shape;
    }
}
