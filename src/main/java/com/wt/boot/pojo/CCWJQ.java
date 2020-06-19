package com.wt.boot.pojo;

public class CCWJQ
{
    private int objectid;
    private String bsm;
    private String ysdm;
    private float zdmj;
    private String bz;
    private String shape;
    private float area;//统一转换面积字段名
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

    public float getZdmj()
    {
        return zdmj;
    }

    public void setZdmj(float zdmj)
    {
        this.zdmj = zdmj;
        this.setArea(zdmj);
    }

    public String getBz()
    {
        if(null==this.bz){this.bz="";}
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
