package com.wt.boot.pojo;

public class CZCDYD
{
    private int objectid;
    private String bsm;
    private String ysdm;
    private String czclx;
    private String czcdm;
    private String czcmc;
    private float czcmj;
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

    public String getCzclx()
    {
        return czclx;
    }

    public void setCzclx(String czclx)
    {
        this.czclx = czclx;
    }

    public String getCzcdm()
    {
        return czcdm;
    }

    public void setCzcdm(String czcdm)
    {
        this.czcdm = czcdm;
    }

    public String getCzcmc()
    {
        return czcmc;
    }

    public void setCzcmc(String czcmc)
    {
        this.czcmc = czcmc;
        this.setName(czcmc);
    }

    public float getCzcmj()
    {
        return czcmj;
    }

    public void setCzcmj(float czcmj)
    {
        this.czcmj = czcmj;
        this.setArea(czcmj);
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
