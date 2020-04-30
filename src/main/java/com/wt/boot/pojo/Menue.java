package com.wt.boot.pojo;


public class Menue
{
    private int menueid;
    private String menuename;
    private String firstcategoryCode;
    private String secondcategoryCode;
    private String secondcategoryName;

    public int getMenueid()
    {
        return menueid;
    }

    public void setMenueid(int menueid)
    {
        this.menueid = menueid;
    }

    public String getMenuename()
    {
        return menuename;
    }

    public void setMenuename(String menuename)
    {
        this.menuename = menuename;
    }

    public String getFirstcategoryCode()
    {
        return firstcategoryCode;
    }

    public void setFirstcategoryCode(String firstcategoryCode)
    {
        this.firstcategoryCode = firstcategoryCode;
    }

    public String getSecondcategoryCode()
    {
        return secondcategoryCode;
    }

    public void setSecondcategoryCode(String secondcategoryCode)
    {
        this.secondcategoryCode = secondcategoryCode;
    }

    public String getSecondcategoryName()
    {
        return secondcategoryName;
    }

    public void setSecondcategoryName(String secondcategoryName)
    {
        this.secondcategoryName = secondcategoryName;
    }
}
