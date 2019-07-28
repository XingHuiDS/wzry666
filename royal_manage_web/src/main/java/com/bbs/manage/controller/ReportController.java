package com.bbs.manage.controller;

import com.bbs.domain.Report;
import com.bbs.service.ArticleService;
import com.bbs.service.ReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**处理举报贴
 *
 */
@Controller
@RequestMapping("/report")
public class ReportController {

        @Autowired
        private ReportService reportService;

        @Autowired
        private ArticleService articleService;

    /**
     * 举报帖查询
     * @param model
     * @param pageNum
     * @param report
     * @return
     * @throws Exception
     */
        @RequestMapping("/findByPage.do")
        public String findByPage(Model model,@RequestParam(required = false,defaultValue = "1",value = "pn") Integer pageNum, Report report) throws Exception {
            PageHelper.startPage(pageNum,6);
            List<Report> reportList = reportService.findByPages(report);
            PageInfo pageInfo = new PageInfo(reportList,6);
            model.addAttribute("pageInfo",pageInfo);
            return "/ReportPage";
        }

    /**
     * 处理举报帖
     * @param pageNum
     * @param flag
     * @param articleId
     * @param reportId
     * @return
     * @throws Exception
     */
        @RequestMapping("/auditReport.do")
        public String auditReport(@RequestParam(required = false, defaultValue = "1", value = "pn") Integer pageNum,Integer flag,Integer articleId,Integer reportId) throws Exception{
            if (flag==0){
                //驳回
                reportService.changeReportStatus(articleId);
                articleService.changeArticleStatus(articleId);
            }else if (flag==1){
                //屏蔽
                reportService.changeReportStatus(articleId);
                articleService.deleteByArticleId(articleId);
            }
            return "redirect:/report/findByPage.do?pn="+pageNum;
        }


}
