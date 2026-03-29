package org.service.impl;

import org.entity.notice;
import org.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Override
    public List<notice> queryNoticeList() {
        return null;
    }

    @Override
    public boolean addNotice(notice notice) {
        return false;
    }

    @Override
    public boolean deleteNotice(int id) {
        return false;
    }

    @Override
    public boolean updateNotice() {
        return false;
    }

    @Override
    public notice queryNoticeInfo() {
        return null;
    }
}
