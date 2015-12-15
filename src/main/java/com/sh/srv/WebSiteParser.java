package com.sh.srv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sh.domain.WebRecord;
import com.sh.domain.WebSite;
import com.sh.http.HttpClientStream;
import com.sh.mapper.WebRecordMapper;
import com.sh.mapper.WebSiteMapper;
import com.sh.util.DateUtil;
import com.sh.util.SelectorUtil;
import com.sh.util.SessionFactoryUtil;

public class WebSiteParser {

	private HttpClientStream httpClient = new HttpClientStream();

	private List<WebRecord> parserWebSite(WebSite webSite, Date beginDate) {
		List<WebRecord> webRecordList = new ArrayList<WebRecord>();

		String url = webSite.getSiteUrl();
		System.out.println("source url:"+url);
		String listRange = webSite.getListRange();
		String titleNode = webSite.getTitleNode();
		String dateNode = webSite.getDateNode();
		String picNode = webSite.getPicNode();

		// http://www.ahwang.cn/house/@@@page@@@.shtml
		for (int i = 1;; i++) {
			System.out.println("遍历第一页开始");
			// 设置页码
			boolean isBreak = false;
			String url2 = url.replaceAll("@@@page@@@", i + "");
			System.out.println("url:"+url2);
			httpClient.callHttpGet(url2);
			String content = httpClient.getResContent();

			Document doc = Jsoup.parse(content);
			
			
			String listRangeQuery = SelectorUtil
					.tagString2SelectorQuery(listRange);
			Elements listRangeElements = doc.select(listRangeQuery).first()
					.children();

			for (int j = 0; j < listRangeElements.size(); j++) {
				WebRecord webRecord = new WebRecord();
				webRecord.setSource(webSite);
				String titleNode2 = titleNode.replaceAll("NO", j + "");
				String dateNode2 = dateNode.replaceAll("NO", j + "");
				String picNode2 = picNode.replaceAll("NO", j + "");

				try {
					parserWebRecord(listRangeElements, webRecord, titleNode2,
							dateNode2, picNode2);
					System.out.println(webRecord.getTitle());
					
				} catch (ParseException e) {
					e.printStackTrace();
				}

				if (webRecord.getCreateDate().after(beginDate)) {
					if (webRecord != null) {
						webRecord.setCrawlDate(new Date());
						webRecordList.add(webRecord);
					}
				} else {
					System.out.println("跳出循环："+webRecord.getTitle());
					isBreak = true;
					break;
				}
				
				webRecord = null;
			}
			if (isBreak) break;
			
			System.out.println("遍历第一页结束");
		}

		return webRecordList;
	}

	private boolean parserWebRecord(Elements listRangeElements,
			WebRecord webRecord, String titleNode, String dateNode,
			String picNode) throws ParseException {
		Element titleElement = SelectorUtil.pathString2Element(
				listRangeElements, titleNode);
		Element dateElement = null;
		if (!(dateNode == null || dateNode.equals(""))) {
			dateElement = SelectorUtil.pathString2Element(listRangeElements,
					dateNode);
		}
		Element picElement = null;
		if (!(picNode == null || picNode.equals(""))) {
			picElement = SelectorUtil.pathString2Element(listRangeElements,
					picNode);
		}

		webRecord.setTitle(titleElement.text());
		webRecord.setHref(titleElement.attr("href"));
		

		if (dateElement != null) {
			webRecord.setCreateDate(DateUtil.string2Date(dateElement.text()));
		}

		if (picElement != null) {
			webRecord.setImg1(picElement.attr("src"));
			System.out.println("img1:"+webRecord.getImg1());
		}
		return false;
	}

	public void parser(Date beginDate) {
		SqlSession session = SessionFactoryUtil.getSessionFactory()
				.openSession();
		if(session == null){
			System.out.println("session wei null");
		}
		WebSiteMapper webSiteMapper = session.getMapper(WebSiteMapper.class);
		WebRecordMapper webRecordMapper = session
				.getMapper(WebRecordMapper.class);

		List<WebSite> webSiteList = webSiteMapper.getAllWebSites();
		for (WebSite webSite : webSiteList) {
			List<WebRecord> webRecordList = parserWebSite(webSite, beginDate);
			System.out.println("打印list");
			for(WebRecord webRecord: webRecordList){
				System.out.println(webRecord.getTitle()+" "+webRecord.getHref()+"   "+webRecord.getCreateDate());
				//webRecordMapper
			}
			System.out.println("插入数据库");
			if(webRecordMapper == null){
				System.out.println("为空");
			}
			webRecordMapper.insertWebRecordList(webRecordList);
			session.commit();		
		}
		session.close();
	}
	
	public static void main(String[] args) throws ParseException {
		WebSiteParser parser = new WebSiteParser();
		Date beginDate = DateUtil.string2Date("2015-12-11 00:00");
		parser.parser(beginDate);
		
	}

}
