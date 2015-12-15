package com.sh.test;

import java.text.ParseException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.sh.http.HttpClientStream;
import com.sh.srv.WebSiteParser;
import com.sh.util.DateUtil;
import com.sh.util.SelectorUtil;

public class TestCrawl {
	@Test
	public void test01(){
		HttpClientStream httpClient = new HttpClientStream();
		String url = "http://www.ahwang.cn/house/";
		httpClient.callHttpGet(url);
		String content = httpClient.getResContent();
		Document doc = Jsoup.parse(content);
		//listRange   <div id='category' class='clearfix'>
		
		//Elements eles = doc.select("div#category.clearfix");
		Elements eles = doc.select("div[id=category][class=clearfix]");
		System.out.println(eles.first().children());
		
		eles = eles.first().children();
		
		String titlePath = "div[NO]/h3/a";
		String[] strs = titlePath.split("/");
		String tag = strs[0].replaceAll("\\[NO\\]", "");
		System.out.println("tag:"+tag);
		Element title = eles.select(tag).first().select("h3>a").first();
		
		System.out.println("############################################title:"+title.attr("href"));
		System.out.println("############################################title:"+title.text());
		
		
		//title div[NO]/h3/a
		/*for(int i = 0;;i++){
			
		}*/
		
	}
	
	@Test
	public void test02(){
		System.out.println(SelectorUtil.tagString2SelectorQuery("<div id=category class=clearfix>"));
	}
	
	
	@Test
	public void test03() throws ParseException{
		WebSiteParser parser = new WebSiteParser();
		Date beginDate = DateUtil.string2Date("2015-12-11 00:00");
		parser.parser(beginDate);
	}

}
