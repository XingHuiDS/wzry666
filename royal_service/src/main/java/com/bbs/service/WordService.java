package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.Word;

import java.util.List;

public interface WordService {
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
     * 分页查询敏感词
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<Word> findByPage(int page, int size) throws Exception;

    /**
     * 启停敏感词
     * @param wordId
     * @param status
     * @throws Exception
     */
    void updateStatus(Integer wordId, Integer status) throws Exception;

    /**
     * 查询启用敏感词
     * @param status
     * @return
     * @throws Exception
     */
    List<Word> findByStatus(Integer status) throws Exception;

    /**
     * 新增敏感词
     * @param word
     * @throws Exception
     */
    void save(Word word) throws Exception;
}
