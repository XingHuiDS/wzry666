package com.bbs.controller;

import com.bbs.domain.ZoneApply;
import com.bbs.service.ZoneApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/zoneapply")
public class ZoneApplyController {

    @Autowired
    private ZoneApplyService zoneApplyService;

    /**
     * 新加版块
     * @param zoneApply
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @ResponseBody
    public boolean save(ZoneApply zoneApply) throws Exception {
        zoneApplyService.save(zoneApply);
        return true;

    }
}
