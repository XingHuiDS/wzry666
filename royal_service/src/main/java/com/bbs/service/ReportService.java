package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.Report;

import java.util.List;

public interface ReportService {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Article> findAll() throws Exception;

    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    Article findById(Integer id) throws Exception;


    /**
     * 保存举报信息
     * @param report
     */
    void sendPeprot(Report report);


    /**
     *
     * @param page
     * @param size
     * @return
     */
    List<Report> findByPage(int page, int size) throws Exception;

    /**
     *
     * @param id
     * @param reportStatus
     */
    void processing(Integer id, Integer reportStatus);

    /**
     * 改变举报帖处理状态
     * @param articleId
     * @throws Exception
     */
    void changeReportStatus(Integer articleId) throws Exception;

    /**
     * 举报帖查询
     * @param report
     * @return
     * @throws Exception
     */
    List<Report> findByPages(Report report) throws Exception;

}
