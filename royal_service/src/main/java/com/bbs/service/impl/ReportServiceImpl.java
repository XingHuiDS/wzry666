package com.bbs.service.impl;

import com.bbs.dao.ReplyDao;
import com.bbs.dao.ReportDao;
import com.bbs.domain.Article;
import com.bbs.domain.Report;
import com.bbs.service.ReportService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;
    @Autowired
    private ReplyDao replyDao;

    @Override
    public List<Article> findAll() throws Exception {
        return null;
    }

    @Override
    public Article findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void sendPeprot(Report report) {
        if(report.getReportStatus()==0){
            report.setReportStatus(1);
        }else {
            report.setReportStatus(0);
        }
        reportDao.sendReport(report);
    }

    @Override
    public List<Report> findByPage(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return reportDao.findAll();
    }

    @Override
    public void processing(Integer id, Integer reportStatus) {
        reportDao.processing(id,reportStatus);
    }

    /**
     * 改变举报帖处理状态
     * @param articleId
     * @throws Exception
     */
    @Override
    public void changeReportStatus(Integer articleId)  throws Exception{
        reportDao.changeReportStatus(articleId);
    }

    /**
     * 举报帖查询
     * @param report
     * @return
     * @throws Exception
     */
    @Override
    public List<Report> findByPages(Report report) throws Exception {

        return reportDao.findByPages(report);
    }


}
