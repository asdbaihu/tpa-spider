package com.hehemedia.spider.mapper;

import com.hehemedia.spider.pojo.NewsInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by GuoYi on 2017/1/12.
 */

public interface NewsInfoMapper {

    int saveNewsInfo(NewsInfo newsInfo);

}
