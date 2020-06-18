package com.wt.boot.pojo;

public class DLTB
{
    private int objectid;
    private String bsm;
    private String ysdm;
    private String dlbm;
    private String qsdwdm;
    private String qsdwmc;
    private float tbmj;
    private float area;

    public float getArea()
    {
        return area;
    }

    public void setArea(float area)
    {
        this.area = area;
    }

    public float getTbmj()
    {
        return tbmj;
    }

    public void setTbmj(float tbmj)
    {
        this.tbmj = tbmj;
        this.setArea(tbmj);
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

    public String getDlbm()
    {
        return dlbm;
    }

    public void setDlbm(String dlbm)
    {
        this.dlbm = dlbm;
    }

    public String getQsdwdm()
    {
        return qsdwdm;
    }

    public void setQsdwdm(String qsdwdm)
    {
        this.qsdwdm = qsdwdm;
    }

    public String getQsdwmc()
    {
        return qsdwmc;
    }

    public void setQsdwmc(String qsdwmc)
    {
        this.qsdwmc = qsdwmc;
    }
}
