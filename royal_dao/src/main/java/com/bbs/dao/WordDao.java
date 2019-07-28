package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.Word;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 禁言dao
 * @return
 * @throws
 */
public interface WordDao {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @Select("select * from bbs_word_table")
    List<Word> findAll() throws Exception;

    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    Article findById(Integer id) throws Exception;

    /**
     * 启停敏感词
     * @param wordId
     * @param status
     */
    @Update("update bbs_word_table set status = #{status} where wordId = #{wordId}")
    void updateStatus(@Param("wordId") Integer wordId, @Param("status") Integer status) throws Exception;

    /**
     * 查询启用敏感词
     * @param status
     * @return
     */
    @Select("select * from bbs_word_table where status = #{status}")
    List<Word> findByStatus(Integer status) throws Exception;

    /**
     * 新增敏感词
     * @param word
     */
    @Insert("insert into bbs_word_table(word) values(#{word})")
    void save(Word word);
}
