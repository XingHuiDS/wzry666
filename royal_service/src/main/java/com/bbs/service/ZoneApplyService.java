package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.ZoneApply;

import java.util.List;

public interface ZoneApplyService {
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
     * 开辟新板块申请
     * @param zoneApply
     * @throws Exception
     */
    void save(ZoneApply zoneApply) throws Exception;

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<ZoneApply> findByPage(int page, int size) throws Exception;

    /**
     * 处理板块申请
     * @param applyZoneId
     * @param status
     * @throws Exception
     */
    void updateStatus(Integer applyZoneId, Integer status) throws Exception;
}
