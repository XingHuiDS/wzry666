package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.Zone;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 板块dao
 * @return
 * @throws
 */
public interface ZoneDao {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @Select("select * from bbs_zone_table")
    List<Zone> findAll() throws Exception;

    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    Article findById(Integer id) throws Exception;

    @Insert("insert into bbs_zone_table(zoneName,isDef) values(#{zoneName},#{isDef})")
    void save(Zone zone);
}
