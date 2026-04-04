package org.service;

import org.entity.notice;

import java.util.List;

public interface NoticeService {

    /*查询通知列表*/
    List<notice> queryNoticeList();

    /*查询通知具体内容*/
    notice queryNoticeInfo();

    /*增加通知*/
    boolean addNotice(notice notice);

    /*删除通知*/
    boolean deleteNotice(int id);

    /*修改通知*/
    boolean updateNotice();
}
