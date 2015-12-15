package com.sh.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sh.domain.WebSite;

public class PageUtil {
	public static String getNextPage(String htmlContent,WebSite webSite,Integer currentPage){
		String nextPage = "";
		Document doc = Jsoup.parse(htmlContent);
		String nextPageQuery = SelectorUtil.tagString2SelectorQuery(webSite.getNextPage());
		Elements nextPageElements = doc.select(nextPageQuery);
		
		Element nextPageElement = nextPageElements.first();
		
		return nextPageElement.attr("href");
	}

}
