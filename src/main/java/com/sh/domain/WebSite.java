package com.sh.domain;

import java.util.Date;

public class WebSite {
	private Integer siteId;
	private String siteName;
	private String siteUrl;
	private String listRange;
	private String titleNode;
	private String dateNode;
	private String picNode;
	
	private String siteCategory;
	private Integer siteCategoryId;
	private String webRecordContentNode;
	private Date createDate;
	private Date updateDate;
	
	private Integer type;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitleNode() {
		return titleNode;
	}
	public void setTitleNode(String titleNode) {
		this.titleNode = titleNode;
	}
	public String getDateNode() {
		return dateNode;
	}
	public void setDateNode(String dateNode) {
		this.dateNode = dateNode;
	}
	public String getPicNode() {
		return picNode;
	}
	public void setPicNode(String picNode) {
		this.picNode = picNode;
	}
	
	
	public String getWebRecordContentNode() {
		return webRecordContentNode;
	}
	public void setWebRecordContentNode(String webRecordContentNode) {
		this.webRecordContentNode = webRecordContentNode;
	}
	public Integer getSiteCategoryId() {
		return siteCategoryId;
	}
	public void setSiteCategoryId(Integer siteCategoryId) {
		this.siteCategoryId = siteCategoryId;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public String getListRange() {
		return listRange;
	}
	public void setListRange(String listRange) {
		this.listRange = listRange;
	}
	public String getSiteCategory() {
		return siteCategory;
	}
	public void setSiteCategory(String siteCategory) {
		this.siteCategory = siteCategory;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
}
