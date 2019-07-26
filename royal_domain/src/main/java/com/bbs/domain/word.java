package com.bbs.domain;

import java.io.Serializable;

/**
 * 敏感词
 */
public class word implements Serializable {
    private Integer wordId;//
    private String word;//敏感词
    private Integer status;//是否启用

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "word{" +
                "wordId=" + wordId +
                ", word='" + word + '\'' +
                ", status=" + status +
                '}';
    }
}
