package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 举报dao
 * @return
 * @throws
 */
public interface ReportDao {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @Select("SELECT * FROM bbs_report_table")
    List<Report> findAll() throws Exception;

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
    @Insert("insert into bbs_report_table (reportContent,reportTime,reportUserName,reportStatus,articleId) values (#{reportContent},#{reportTime},#{reportUserName},#{reportStatus},#{articleId})")
    void sendReport(Report report);



    /**
     * 处理举报信息
     * @param id
     * @param reportStatus
     */
    @Update("update bbs_report_table set reportStatus=#{reportStatus} where reportId = #{id}")
    void processing(@Param("id") Integer id, @Param("reportStatus") Integer reportStatus);;


    List<Report> findByPage(int page, int size);

    /**
     * 改变举报帖处理状态
     * @param articleId
     * @throws Exception
     */
    @Update("update bbs_report_table set reportStatus=1 where articleId=#{articleId}")
    void changeReportStatus(Integer articleId) throws Exception;

    /**
     * 举报帖查询
     * @param report
     * @return
     * @throws Exception
     */
    @Select("select * from bbs_report_table where reportStatus=0")
    List<Report> findByPages(Report report) throws Exception;

}
