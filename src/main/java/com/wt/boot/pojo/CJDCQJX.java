package com.wt.boot.pojo;

import org.omg.CORBA.PRIVATE_MEMBER;

public class CJDCQJX
{
    private int objectid;
    private String bsm;
    private String ysdm;
    private String jxlx;
    private String jxxz;
    private String jxsm;
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

    public String getJxlx()
    {
        return jxlx;
    }

    public void setJxlx(String jxlx)
    {
        this.jxlx = jxlx;
    }

    public String getJxxz()
    {
        return jxxz;
    }

    public void setJxxz(String jxxz)
    {
        this.jxxz = jxxz;
    }

    public String getJxsm()
    {
        return jxsm;
    }

    public void setJxsm(String jxsm)
    {
        this.jxsm = jxsm;
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
