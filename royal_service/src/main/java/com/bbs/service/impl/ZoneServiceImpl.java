package com.bbs.service.impl;

import com.bbs.dao.ZoneDao;
import com.bbs.domain.Article;
import com.bbs.domain.Zone;
import com.bbs.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneDao zoneDao;

    @Override
    public List<Zone> findAll() throws Exception {
        return zoneDao.findAll();
    }

    @Override
    public Article findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void save(Zone zone) throws Exception {
        zoneDao.save(zone);
    }
}
