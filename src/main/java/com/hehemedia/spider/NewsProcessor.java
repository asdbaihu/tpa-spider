package com.hehemedia.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Lenovo on 2017/7/10.
 */
public class NewsProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3);
    private Page page;

    public void process(Page page) {
        if (page.getUrl().regex("http://news.baidu.com/").match()) {
            //列表页
          page.addTargetRequests(page.getHtml().xpath("//ul[@class='ulist focuslistnews']/").links().regex("http://china.huanqiu.com/article/2017-07/\\d\\d\\d\\d\\d\\d\\d\\d.html").all());
//            page.addTargetRequests(page.getHtml().xpath("//ul[@class='ulist focuslistnews']/").links().all());
        } else {
            //详情页//
            page.putField("title", page.getHtml().xpath("//div[@class='conText']/h1/text()|//*[@id=\"artical_topic\"]/text()|//div[@class='bg-content']/h1/text()|//div[@class='J-title_detail title_detail']/h1/span/text()|*[@id=\"chan_newsTitle\"]/text()|//div[@class='newscontent']/h1/text()"));
//            page.putField("content", page.getHtml().xpath("//div[@id='text']/p/text()"));
            page.putField("date", page.getHtml().xpath("//strong[@id='pubtime_baidu']/text()"));
            page.putField("source", page.getHtml().xpath("//strong[@id='source_baidu']/a/text()"));
            page.putField("url", page.getUrl());


            NewsInfo newsInfo=new NewsInfo();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                newsInfo.setDate(dateFormat.parse(page.getHtml().xpath("//strong[@id='pubtime_baidu']/text()").toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
                newsInfo.setTitle(page.getHtml().xpath("//div[@class='conText']/h1/text()|//*[@id=\"artical_topic\"]/text()|//div[@class='bg-content']/h1/text()|//div[@class='J-title_detail title_detail']/h1/span/text()|*[@id=\"chan_newsTitle\"]/text()|//div[@class='newscontent']/h1/text()").toString());
                newsInfo.setSource(page.getHtml().xpath("//strong[@id='source_baidu']/a/text()").toString());
                newsInfo.setUrl(page.getUrl().toString());
            new CsdnBlogDao().addNews(newsInfo);
        }

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new NewsProcessor())
                .addUrl("http://news.baidu.com/")
//                .addPipeline(new JsonFilePipeline("E:\\webmagic\\"))//保存抓取结果
                .thread(5)
                .run();
    }
}
