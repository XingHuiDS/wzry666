package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.Zone;

import java.util.List;

public interface ZoneService {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Zone> findAll() throws Exception;

    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    Article findById(Integer id) throws Exception;

    /**
     * 审核通过增加新板块
     * @param zone
     * @throws Exception
     */
    void save(Zone zone) throws Exception;
}
