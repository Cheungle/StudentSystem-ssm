package org.controller.admin;


import org.entity.notice;
import org.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminNoticeController {
    @Autowired
    public NoticeService noticeService;

    @RequestMapping("/getNoticeList")
    public List<notice> getNoticeList(){
    	return noticeService.queryNoticeList();
    }
}
