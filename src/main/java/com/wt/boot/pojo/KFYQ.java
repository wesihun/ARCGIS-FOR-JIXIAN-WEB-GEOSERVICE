package com.wt.boot.pojo;

public class KFYQ
{
    private int objectid;
    private String bsm;
    private String ysdm;
    private String kfyqmc;
    private String kfyqlx;
    private String kfyqxz;
    private String kfyqtz;
    private float kfyqmj;
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

    public String getKfyqmc()
    {
        return kfyqmc;
    }

    public void setKfyqmc(String kfyqmc)
    {
        this.kfyqmc = kfyqmc;
        this.setName(kfyqmc);
    }

    public String getKfyqlx()
    {
        return kfyqlx;
    }

    public void setKfyqlx(String kfyqlx)
    {
        this.kfyqlx = kfyqlx;
    }

    public String getKfyqxz()
    {
        return kfyqxz;
    }

    public void setKfyqxz(String kfyqxz)
    {
        this.kfyqxz = kfyqxz;
    }

    public String getKfyqtz()
    {
        return kfyqtz;
    }

    public void setKfyqtz(String kfyqtz)
    {
        this.kfyqtz = kfyqtz;
    }

    public float getKfyqmj()
    {
        return kfyqmj;
    }

    public void setKfyqmj(float kfyqmj)
    {
        this.kfyqmj = kfyqmj;
        this.setArea(kfyqmj);
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
