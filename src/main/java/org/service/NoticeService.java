package org.service;

import org.entity.notice;

import java.util.List;

public interface NoticeService {

    /*查询通知列表*/
    public List<notice> queryNoticeList();

    /*查询通知具体内容*/
    public notice queryNoticeInfo();

    /*增加通知*/
    public boolean addNotice(notice notice);

    /*删除通知*/
    public boolean deleteNotice(int id);

    /*修改通知*/
    public boolean updateNotice();
}
