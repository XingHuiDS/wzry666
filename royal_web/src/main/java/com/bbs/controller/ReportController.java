package com.bbs.controller;

import com.bbs.domain.Report;
import com.bbs.domain.UserInfo;
import com.bbs.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RequestMapping("/report")
@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;


    @RequestMapping("/sendReport")
    public String sendReport(Report report, HttpServletRequest request, HttpServletResponse response){
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        Date date = new Date();
        report.setReportUserName(user.getUserName());
        report.setReportTime(date);
        //1为已举报
        report.setReportStatus(1);
        reportService.sendPeprot(report);
        return "redirect:/index/show";
    }
}
