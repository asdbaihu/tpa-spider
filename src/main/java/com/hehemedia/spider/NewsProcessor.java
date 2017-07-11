package com.hehemedia.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * Created by Lenovo on 2017/7/10.
 */
public class NewsProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3);

    public void process(Page page) {
        if (page.getUrl().regex("http://news.baidu.com/").match()) {
            //列表页
            page.addTargetRequests(page.getHtml().xpath("//ul[@class='ulist focuslistnews']/").links().regex("http://china.huanqiu.com/article/2017-07/\\d\\d\\d\\d\\d\\d\\d\\d.html").all());
        } else {
            //详情页//
            page.putField("title", page.getHtml().xpath("//div[@class='conText']/h1/text()|//*[@id=\"artical_topic\"]/text()|//div[@class='bg-content']/h1/text()|//div[@class='J-title_detail title_detail']/h1/span/text()|*[@id=\"chan_newsTitle\"]/text()|//div[@class='newscontent']/h1/text()"));
//            page.putField("content", page.getHtml().xpath("//div[@id='text']/p/text()"));
            page.putField("date", page.getHtml().xpath("//strong[@id='pubtime_baidu']/text()"));
            page.putField("source", page.getHtml().xpath("//strong[@id='source_baidu']/a/text()"));
            page.putField("url", page.getUrl());
        }

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new NewsProcessor()).addUrl("http://news.baidu.com/").thread(5).run();
    }
}
