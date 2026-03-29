package org.controller.student;

import org.entity.notice;
import org.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentNoticeController {
    @Autowired
    public NoticeService noticeService;

    @RequestMapping("/ShowNotice")
    public List<notice> ShowNotice(){
    	return noticeService.queryNoticeList();
    }

    @RequestMapping("/ShowNoticeInfo")
    public notice ShowNoticeInfo(){
    	return noticeService.queryNoticeInfo();
    }

}
