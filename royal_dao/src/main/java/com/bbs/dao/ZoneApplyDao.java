package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.ZoneApply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 板块申请dao
 * @return
 * @throws
 */
public interface ZoneApplyDao {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @Select("select * from bbs_zoneapply_table")
    List<ZoneApply> findAll() throws Exception;

    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    Article findById(Integer id) throws Exception;

    /**
     * 开辟新板块申请
     * @param zoneApply
     */
    @Insert("insert into bbs_zoneapply_table(zoneName,userName,reason) values(#{zoneName},#{userName},#{reason})")
    void save(ZoneApply zoneApply);

    /**
     * 处理开辟新板块
     * @param applyZoneId
     * @param status
     * @throws Exception
     */
    @Update("update bbs_zoneapply_table set status = #{status} where applyZoneId = #{applyZoneId}")
    void updateStatus(@Param("applyZoneId") Integer applyZoneId, @Param("status") Integer status) throws Exception;
}
