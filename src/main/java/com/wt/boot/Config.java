package com.wt.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config
{
    @Value("${file_dir}")
    private String file_dir;

    @Value("${server.port}")
    private String port;

    @Value("${report_url}")
    private String report_url;

    public String getReport_url()
    {
        return report_url;
    }

    public void setReport_url(String report_url)
    {
        this.report_url = report_url;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getFile_dir() {
        return file_dir;
    }

    public void setFile_dir(final String file_dir) {
        this.file_dir = file_dir;
    }

    






}