package com.sh.srv;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sh.domain.WebRecord;
import com.sh.domain.WebSite;
import com.sh.exception.ListRangeNotFound;
import com.sh.exception.WebRecordNotFound;
import com.sh.handler.HtmlParserHandler;
import com.sh.http.HttpClientStream;
import com.sh.mapper.WebRecordMapper;
import com.sh.mapper.WebSiteMapper;
import com.sh.util.DateUtil;
import com.sh.util.PageUtil;
import com.sh.util.SessionFactoryUtil;

public class NewWebSiteParser {
	private HttpClientStream httpClient = new HttpClientStream();
	/*
	 * private SqlSession session =
	 * SessionFactoryUtil.getSessionFactory().openSession(); private
	 * WebSiteMapper webSiteMapper = session.getMapper(WebSiteMapper.class);
	 * private WebRecordMapper webRecordMapper =
	 * session.getMapper(WebRecordMapper.class);
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(NewWebSiteParser.class);
	private HtmlParserHandler parserHandler = new HtmlParserHandler();

	public List<WebRecord> parserWebSite(WebSite webSite, Date beginDate) {
		List<WebRecord> webRecordList = new ArrayList<WebRecord>();
		String url = webSite.getSiteUrl();
		logger.info("url:"+url);
		String currentUrl = "";
		String htmlContent = "";
		for (int i = 1;; i++) {
			if (i == 1) {
				currentUrl = url;
				httpClient.callHttpGet(currentUrl);
				htmlContent = httpClient.getResContent();
			} else {
				currentUrl = PageUtil.getNextPage(htmlContent, webSite, i);
				httpClient.callHttpGet(currentUrl);
				htmlContent = httpClient.getResContent();
			}
			List<Element> listRangeElements = null;
			try {
				listRangeElements = parserHandler.parserListRangeElement(
						htmlContent, webSite);
			} catch (ListRangeNotFound e) {
				logger.error(
						String.format("站点:%s，第%d页没有找到listrange节点",
								webSite.getSiteUrl(), i), e);
			}
			
			boolean isBreak = false;

			for (int j=0;j<listRangeElements.size();j++) {
				Element listRangeElement =  listRangeElements.get(j);
				try {
					List<WebRecord> webRecords = new ArrayList<WebRecord>();
					 isBreak= parserHandler.parserWebRecordByListRange(listRangeElement, webSite,beginDate,webRecords);
					if(!(webRecords == null || webRecords.size() == 0)){
						webRecordList.addAll(webRecords);
					}
					if(isBreak) break;
				} catch (WebRecordNotFound e) {
					logger.error(String.format("第%d个listrange节点不包含webRecord",j), e);
				} catch (Exception e) {
					logger.error(e.toString());
				}
			}
			
			if(isBreak) break;
		}
		return webRecordList;	
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
			webSiteMapper.updateBeginDate(webRecordList.get(0).getCreateDate(), webSite.getSiteId());
			session.commit();		
		}
		session.close();
	}
	
	public static void main(String[] args) throws ParseException {
		NewWebSiteParser parser = new NewWebSiteParser();
		//WebSiteParser parser = new WebSiteParser();
		Date beginDate = DateUtil.string2Date("2015-12-03 00:00");
		parser.parser(beginDate);
		
	}

}
