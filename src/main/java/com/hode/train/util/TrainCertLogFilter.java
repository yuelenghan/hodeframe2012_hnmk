package com.hode.train.util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hode.rbac.model.UserSessionModel;
import com.hode.train.model.TrainCertLogModel;

public class TrainCertLogFilter implements Filter {
    private static final Logger logger = Logger.getLogger("certLog");

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        //得到访问ip
        String ip = getIpAddr((HttpServletRequest) request);

        //得到访问用户名和id
        HttpSession session = ((HttpServletRequest) request).getSession();
        UserSessionModel user = (UserSessionModel)session.getAttribute("user");
        String userName = user.getStrName();
        int userID = user.getIntUserID();

        //得到访问时间
        Date date = new Date();
        String curDate = MyDateUtil.DateToString(date , "yyyyMMdd HH:mm:ss");

        // 得到访问的uri
        String uri = ((HttpServletRequest) request).getRequestURI();

        // 向数据库中插入日志记录
        TrainCertLogModel trainCertLogModel = new TrainCertLogModel();
        trainCertLogModel.setStrUserName(userName);
        trainCertLogModel.setStrIp(ip);
        trainCertLogModel.setStrUri(uri);
        trainCertLogModel.setStrDate(curDate);
        trainCertLogModel.setIntUserID(userID);

        try {
            TrainUtil.insertTrainCertLog(trainCertLogModel);

            //在硬盘上生成日志文件
            //日志格式   用户名|ip|uri|时间|用户id
            logger.info(userName + "|" + ip + "|" + uri + "|" + curDate + "|" + userID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 根据请求得到ip地址
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
