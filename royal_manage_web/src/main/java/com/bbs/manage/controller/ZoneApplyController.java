package com.bbs.manage.controller;

import com.bbs.domain.Zone;
import com.bbs.domain.ZoneApply;
import com.bbs.service.ZoneApplyService;
import com.bbs.service.ZoneService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/mgr_zoneapply")
public class ZoneApplyController {

    @Autowired
    private ZoneApplyService zoneApplyService;

    @Autowired
    private ZoneService zoneService;

    @RequestMapping("/findByPage")
    public ModelAndView findByPage(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<ZoneApply> wordList = zoneApplyService.findByPage(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(wordList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("zoneapply-page-list");
        return mv;
    }

    @RequestMapping("/deal")
    public void deal(Integer applyZoneId, String zoneName,Integer flag) throws Exception {
        zoneApplyService.updateStatus(applyZoneId,1);
        if(flag == 1){
            Zone zone = new Zone();
            zone.setZoneName(zoneName);
            zone.setIsDef(2);
            zoneService.save(zone);
        }
    }


}
