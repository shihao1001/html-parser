package com.sh.srv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sh.domain.WebRecord;
import com.sh.domain.WebSite;
import com.sh.http.HttpClientStream;
import com.sh.mapper.WebRecordMapper;
import com.sh.mapper.WebSiteMapper;
import com.sh.util.DateUtil;
import com.sh.util.SelectorUtil;
import com.sh.util.SessionFactoryUtil;

public class WebRecordDetailParser {
	
	private HttpClientStream httpClient = new HttpClientStream();
	private static final Logger logger = LoggerFactory
			.getLogger(NewWebSiteParser.class);
	private Long latestParsedWebRecordId = 0l;
	private Integer limit;
		
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Long getLatestParsedWebRecordId() {
		return latestParsedWebRecordId;
	}
	public void setLatestParsedWebRecordId(Long latestParsedWebRecordId) {
		this.latestParsedWebRecordId = latestParsedWebRecordId;
	}
	public WebRecordDetailParser(){}	

	public WebRecordDetailParser(Long latestParsedWebRecordId, Integer limit) {
		this.latestParsedWebRecordId = latestParsedWebRecordId;
		this.limit = limit;
	}
	public void parserWebRecordDetail(){
		SqlSession session = SessionFactoryUtil.getSessionFactory()
				.openSession();
		WebRecordMapper webRecordMapper = session
				.getMapper(WebRecordMapper.class);

		List<WebRecord> webRecordList = webRecordMapper.selectUnCrawledWebRecordById(latestParsedWebRecordId, limit);
		for(WebRecord webRecord : webRecordList){
			System.out.println(webRecord.getSource().getWebRecordContentNode());
		}
		List<Long> updatedWebRecordIds = new ArrayList<Long>();
		for(WebRecord webRecord: webRecordList){
		     try {
				if(parserHandlerWebRecord(webRecord)){
					updatedWebRecordIds.add(webRecord.getWebRecordId());
				}
			} catch (IOException e) {
				logger.error(String.format("id为：%d的webRecord存储失败！", webRecord.getWebRecordId()), e);
			}
		}	
		updateWebRecordListStatus(webRecordMapper,updatedWebRecordIds);
		session.commit();
		session.close();
	}
	
	private void updateWebRecordListStatus(WebRecordMapper webRecordMapper,
			List<Long> updatedWebRecordIds) {
		webRecordMapper.updateWebRecordList(updatedWebRecordIds);
		
	}
	private boolean parserHandlerWebRecord(WebRecord webRecord) throws IOException {
		httpClient.callHttpGet(webRecord.getHref());
		String htmlContent = httpClient.getResContent();
		Document doc = Jsoup.parse(htmlContent);
		String contentElement = webRecord.getSource().getWebRecordContentNode();
		String[] tagStrs = contentElement.split(";");
		StringBuffer content = new StringBuffer();
		
		String lineSeparator = System.getProperty("line.separator"); 
		
		for(String tagString : tagStrs){
			String tagQuery = SelectorUtil.tagString2SelectorQuery(tagString);
			Elements conEles = doc.select(tagQuery);
			content.append(conEles.html()+lineSeparator);
		}
		webRecord.setContent(content.toString());
		boolean isSussess = false;
	    isSussess = saveWebRecordContent(htmlContent,webRecord);
		return isSussess;
	}
	
	private boolean saveWebRecordContent(String htmlContent,WebRecord webRecord) throws IOException{
		String createDateStr = DateUtil.date2String(webRecord.getCreateDate());
		File htmlFolder = new File("./html/"+createDateStr);
		File dataFolder = new File("./data/"+createDateStr);
		
		if(!htmlFolder.exists()) htmlFolder.mkdir();
		if(!dataFolder.exists()) dataFolder.mkdir();
		
		File htmlFile = new File(htmlFolder.getAbsolutePath() + "/"+webRecord.getWebRecordId()+".html");
		File dataFile = new File(dataFolder.getAbsolutePath() + "/"+webRecord.getWebRecordId()+".txt");
		FileWriter htmlFileWriter =null;
		FileWriter dataFileWriter =null;
		try {
			if(!htmlFile.exists()){
				htmlFile.createNewFile();
			}
			if(!dataFile.exists()){
				dataFile.createNewFile();
			}
			
			 htmlFileWriter = new FileWriter(htmlFile,false);
			 dataFileWriter = new FileWriter(dataFile,false);
			
			htmlFileWriter.write(htmlContent);
			dataFileWriter.write(webRecord.getContent());
			
			htmlFileWriter.flush();
			dataFileWriter.flush();
			return true;
		} catch (IOException e) {
			throw e;
		}finally{
			if(htmlFileWriter != null){
				try {
					htmlFileWriter.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(dataFileWriter != null){
				try {
					dataFileWriter.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		WebRecordDetailParser parser = new WebRecordDetailParser(0l, 100);
		parser.parserWebRecordDetail();
	}

}
