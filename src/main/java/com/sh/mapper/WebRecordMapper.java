package com.sh.mapper;

import java.util.List;

import com.sh.domain.WebRecord;

public interface WebRecordMapper {
	
	public void insertWebRecordList(List<WebRecord> webRecordList);
	
	public Long insertWebRecord(WebRecord webRecordList);
	
	public void updateWebRecord(WebRecord webRecord);
	
	public List<WebRecord> selectUnCrawledWebRecordById(long beginId,Integer limitNo);

}
