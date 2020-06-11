package com.wt.boot.pojo;

import java.util.Date;

public class LSYD
{
    private int objectid;
    private String bsm;
    private String ysdm;
    private String gltbbsm;
    private String pzwjmc;
    private String pzwh;
    private float tbmj;
    private float pzmj;
    private String ytfl;
    private String jtxmyt;
    private Date pzrq;
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

    public String getGltbbsm()
    {
        return gltbbsm;
    }

    public void setGltbbsm(String gltbbsm)
    {
        this.gltbbsm = gltbbsm;
    }

    public String getPzwjmc()
    {
        return pzwjmc;
    }

    public void setPzwjmc(String pzwjmc)
    {
        this.pzwjmc = pzwjmc;
        this.setName(pzwjmc);
    }

    public String getPzwh()
    {
        return pzwh;
    }

    public void setPzwh(String pzwh)
    {
        this.pzwh = pzwh;
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

    public float getPzmj()
    {
        return pzmj;
    }

    public void setPzmj(float pzmj)
    {
        this.pzmj = pzmj;
    }

    public String getYtfl()
    {
        return ytfl;
    }

    public void setYtfl(String ytfl)
    {
        this.ytfl = ytfl;
    }

    public String getJtxmyt()
    {
        return jtxmyt;
    }

    public void setJtxmyt(String jtxmyt)
    {
        this.jtxmyt = jtxmyt;
    }

    public Date getPzrq()
    {
        return pzrq;
    }

    public void setPzrq(Date pzrq)
    {
        this.pzrq = pzrq;
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
