package com.wt.boot.pojo;

public class XZQ
{
    private int objectid;
    private String bsm;
    private String ysdm;
    private String xzqdm;
    private String xzqmc;
    private float dcmj;
    private float jsmj;
    private String mssm;
    private String hdmc;
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

    public String getXzqdm()
    {
        return xzqdm;
    }

    public void setXzqdm(String xzqdm)
    {
        this.xzqdm = xzqdm;
    }

    public String getXzqmc()
    {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc)
    {
        this.xzqmc = xzqmc;
        this.setName(xzqmc);
    }

    public float getDcmj()
    {
        return dcmj;
    }

    public void setDcmj(float dcmj)
    {
        this.dcmj = dcmj;
        this.setArea(dcmj);
    }

    public float getJsmj()
    {
        return jsmj;
    }

    public void setJsmj(float jsmj)
    {
        this.jsmj = jsmj;
    }

    public String getMssm()
    {
        return mssm;
    }

    public void setMssm(String mssm)
    {
        this.mssm = mssm;
    }

    public String getHdmc()
    {
        return hdmc;
    }

    public void setHdmc(String hdmc)
    {
        this.hdmc = hdmc;
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
