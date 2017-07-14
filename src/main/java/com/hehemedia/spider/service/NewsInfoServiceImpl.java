package com.hehemedia.spider.service;

import com.hehemedia.spider.mapper.NewsInfoMapper;
import com.hehemedia.spider.pojo.NewsInfo;
import com.hehemedia.spider.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lenovo on 2017/7/11.
 */
@Service("newsInfoService")
public class NewsInfoServiceImpl implements NewsInfoService {

    @Autowired
    private NewsInfoMapper newsInfoMapper;

    public int save(NewsInfo newsInfo) {
        return newsInfoMapper.saveNewsInfo(newsInfo);
    }
}
