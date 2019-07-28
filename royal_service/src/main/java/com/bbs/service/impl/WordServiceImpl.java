package com.bbs.service.impl;

import com.bbs.dao.WordDao;
import com.bbs.domain.Article;
import com.bbs.domain.Word;
import com.bbs.service.WordService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WordServiceImpl implements WordService {

    @Autowired
    private WordDao wordDao;

    @Override
    public List<Article> findAll() throws Exception {
        return null;
    }

    @Override
    public Article findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Word> findByPage(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return wordDao.findAll();
    }

    @Override
    public void updateStatus(Integer wordId, Integer status) throws Exception {
        wordDao.updateStatus(wordId,status);
    }

    @Override
    public List<Word> findByStatus(Integer status) throws Exception {
        return wordDao.findByStatus(status);
    }

    @Override
    public void save(Word word) throws Exception {
        wordDao.save(word);
    }
}
