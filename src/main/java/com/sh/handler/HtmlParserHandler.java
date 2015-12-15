package com.sh.handler;


import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sh.domain.WebRecord;
import com.sh.domain.WebSite;
import com.sh.exception.ListRangeNotFound;
import com.sh.exception.WebRecordNotFound;
import com.sh.srv.NewWebSiteParser;
import com.sh.util.DateUtil;
import com.sh.util.SelectorUtil;

public class HtmlParserHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(HtmlParserHandler.class);

	public List<Element> parserListRangeElement(String htmlContent,
			WebSite webSite) throws ListRangeNotFound {
		String listRangeNodeAttris = webSite.getListRange();
		Document doc = Jsoup.parse(htmlContent);
		String listRangeQuery = SelectorUtil
				.tagString2SelectorQuery(listRangeNodeAttris);
		Elements listRangeElements = doc.select(listRangeQuery);
		if(listRangeElements == null || listRangeElements.size() == 0){
			throw new ListRangeNotFound("ListRangeNotFound");
		}
		return listRangeElements.subList(0, listRangeElements.size());
	}

	public boolean parserWebRecordByListRange(Element listRangeElement,WebSite webSite,Date beginDate,List<WebRecord> webRecordList)
			throws WebRecordNotFound, Exception {
		boolean isBreak = false;
		
		String titleNode = webSite.getTitleNode();
		String dateNode = webSite.getDateNode();
		String picNode = webSite.getPicNode();

		Elements webRecords = listRangeElement.children();
		if(webRecords == null || webRecords.size() == 0){
			throw new WebRecordNotFound();
		}

		for (int i = 0; i < webRecords.size(); i++) {
			WebRecord webRecord = new WebRecord();
			webRecord.setSource(webSite);
			String titleNode2 = titleNode.replaceAll("NO", i + "");
			String dateNode2 = dateNode.replaceAll("NO", i + "");
			String picNode2 = picNode.replaceAll("NO", i + "");

			try {
				parserWebRecord(webRecords, webRecord, titleNode2, dateNode2,
						picNode2);
			} catch (WebRecordNotFound e) {
				logger.error(String.format("第%d个webRecords节点不包含webRecord",i), e);
			} catch (Exception e) {
				logger.error(e.toString());
			}
			
			if(!(webRecord.getCreateDate() == null || webRecord.getCreateDate().equals(""))){
				if (webRecord.getCreateDate().after(beginDate)) {
					if (webRecord != null) {
						webRecord.setCrawlDate(new Date());
						webRecordList.add(webRecord);
					}
				}else{
					isBreak = true;
				}
				
			}
			if(isBreak) break;
			webRecord = null;
		}
		return isBreak;
	}

	private void parserWebRecord(Elements webRecords, WebRecord webRecord,
			String titleNode, String dateNode, String picNode)
			throws WebRecordNotFound, Exception {
		Element titleElement = SelectorUtil.pathString2Element(webRecords,
				titleNode);
		if (titleElement == null) {
			throw new WebRecordNotFound("未找到webRecord记录");
		}
		Element dateElement = null;
		if (!(dateNode == null || dateNode.equals(""))) {
			dateElement = SelectorUtil.pathString2Element(webRecords, dateNode);
		}
		Element picElement = null;
		if (!(picNode == null || picNode.equals(""))) {
			picElement = SelectorUtil.pathString2Element(webRecords, picNode);
		}

		webRecord.setTitle(titleElement.text());
		webRecord.setHref(titleElement.attr("href"));

		if (dateElement != null) {
			webRecord.setCreateDate(DateUtil.string2Date(dateElement.text()));
		}

		if (picElement != null) {
			webRecord.setImg1(picElement.attr("src"));
		}
	}

}
