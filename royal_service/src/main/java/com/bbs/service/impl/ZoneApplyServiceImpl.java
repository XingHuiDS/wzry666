package com.bbs.service.impl;

import com.bbs.dao.ZoneApplyDao;
import com.bbs.domain.Article;
import com.bbs.domain.ZoneApply;
import com.bbs.service.ZoneApplyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ZoneApplyServiceImpl implements ZoneApplyService {

    @Autowired
    private ZoneApplyDao zoneApplyDao;

    @Override
    public List<Article> findAll() throws Exception {
        return null;
    }

    @Override
    public Article findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void save(ZoneApply zoneApply) throws Exception {
        zoneApplyDao.save(zoneApply);
    }

    @Override
    public List<ZoneApply> findByPage(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return zoneApplyDao.findAll();
    }

    @Override
    public void updateStatus(Integer applyZoneId, Integer status) throws Exception {
        zoneApplyDao.updateStatus(applyZoneId,status);
    }
}
