package com.bbs.domain;

/**
 * 敏感词板块
 * @date 2019/6/24 16:58
 */
public class Word {
    private int wordId; //敏感词ID
    private String word; //敏感词
    private int status; //是否启用

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
