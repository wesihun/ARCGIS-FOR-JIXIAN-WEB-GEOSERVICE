package com.wt.boot.pojo;

import java.util.Date;

public class STBHHX
{
    private int objectid;
    private String bsm;
    private String ysdm;
    private String xjxzqhdm;
    private String lxdm;
    private String sldm;
    private String mc;
    private int rksl;
    private String stgnybhmb;
    private String dlwz;
    private float qymj;
    private String stxtyzblx;
    private String rwhdlx;
    private String sthjwt;
    private String gkcs;
    private Date slsj;
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

    public String getXjxzqhdm()
    {
        return xjxzqhdm;
    }

    public void setXjxzqhdm(String xjxzqhdm)
    {
        this.xjxzqhdm = xjxzqhdm;
    }

    public String getLxdm()
    {
        return lxdm;
    }

    public void setLxdm(String lxdm)
    {
        this.lxdm = lxdm;
    }

    public String getSldm()
    {
        return sldm;
    }

    public void setSldm(String sldm)
    {
        this.sldm = sldm;
    }

    public String getMc()
    {
        return mc;
    }

    public void setMc(String mc)
    {
        this.mc = mc;
        this.setName(mc);
    }

    public int getRksl()
    {
        return rksl;
    }

    public void setRksl(int rksl)
    {
        this.rksl = rksl;
    }

    public String getStgnybhmb()
    {
        return stgnybhmb;
    }

    public void setStgnybhmb(String stgnybhmb)
    {
        this.stgnybhmb = stgnybhmb;
    }

    public String getDlwz()
    {
        return dlwz;
    }

    public void setDlwz(String dlwz)
    {
        this.dlwz = dlwz;
    }

    public float getQymj()
    {
        return qymj;
    }

    public void setQymj(float qymj)
    {
        this.qymj = qymj;
        this.setArea(qymj);
    }

    public String getStxtyzblx()
    {
        return stxtyzblx;
    }

    public void setStxtyzblx(String stxtyzblx)
    {
        this.stxtyzblx = stxtyzblx;
    }

    public String getRwhdlx()
    {
        return rwhdlx;
    }

    public void setRwhdlx(String rwhdlx)
    {
        this.rwhdlx = rwhdlx;
    }

    public String getSthjwt()
    {
        return sthjwt;
    }

    public void setSthjwt(String sthjwt)
    {
        this.sthjwt = sthjwt;
    }

    public String getGkcs()
    {
        return gkcs;
    }

    public void setGkcs(String gkcs)
    {
        this.gkcs = gkcs;
    }

    public Date getSlsj()
    {
        return slsj;
    }

    public void setSlsj(Date slsj)
    {
        this.slsj = slsj;
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
