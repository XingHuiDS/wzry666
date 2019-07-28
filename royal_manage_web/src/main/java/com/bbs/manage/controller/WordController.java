package com.bbs.manage.controller;

import com.bbs.domain.Word;
import com.bbs.service.WordService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/mgr_word")
public class WordController {

    @Autowired
    private WordService wordService;

    @RequestMapping("/findByPage")
    public ModelAndView findByPage(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Word> wordList = wordService.findByPage(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(wordList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("word-page-list");
        return mv;
    }

    @RequestMapping("/uptStatus")
    public void uptStatus(Integer wordId, Integer status) throws Exception {
        wordService.updateStatus(wordId, status);
    }

    @RequestMapping("/save")
    @ResponseBody
    public boolean save(Word word) throws Exception {
        wordService.save(word);
        return true;
    }

}
