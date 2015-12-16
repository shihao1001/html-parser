package com.sh.util;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SelectorUtil {
	
	
	public static Element htmlPath2Element(Document doc,String htmlPath){
		
		
		return null;
	}
	
	public static String tagString2SelectorQuery2(String str){
		String selectorQuery = "";	
		//listRange   <div id=category@@class=clearfix>		
		//<p class=describe>;<div class=article-content fontSizeBig>
		str = str.substring(1, str.length()-1).trim();
		String[] strs = str.split(" ");
		String tag = strs[0];
		selectorQuery = selectorQuery+tag;
		for(int i=1;i<strs.length;i++){
			selectorQuery = selectorQuery +"[" +strs[i] + "]";
		}
		return selectorQuery;	
	}
	
	
	public static String tagString2SelectorQuery(String str){
		String selectorQuery = "";	
		//listRange   <div id=category@@class=clearfix>		
		//<p class=describe>;<div class=article-content fontSizeBig>
		str = str.substring(1, str.length()-1).trim();
		int firstBlankIndex = str.indexOf(" ");
		String tag = str.substring(0, firstBlankIndex);
		String attrs = str.substring(firstBlankIndex+1);
		String[] attrArray = attrs.split("@@");
		selectorQuery = selectorQuery+tag;
		for(String attr : attrArray){
			selectorQuery = selectorQuery +"[" +attr + "]";
		}
		
		return selectorQuery;	
	}
	
	
	
	public static Element pathString2Element(Elements webRecords,String path){
		
		//String path = "div[NO]/h3/a";
		
		//               div[NO]/div/div[2]/div/div/div/span
		
		String[] tags = path.split("/");
	    Element ele = null;
	    
		
		for(int i=0;i<tags.length;i++){
			String tagWithIndex = tags[i];
			String tag = "";
			Integer index = 0;
			if(tagWithIndex.endsWith("]")){	
				index = Integer.parseInt(tagWithIndex.substring(0, tagWithIndex.length()-1).replaceAll("\\[", ",").split(",")[1]);
				tag = tagWithIndex.substring(0, tagWithIndex.length()-1).replaceAll("\\[", ",").split(",")[0];		
			}else{
				index = 0;
				tag = tagWithIndex;
			}
			if(index != 0){
				if(i == 0){
					//ele = listRangeElements.select(tag).get(index);
					ele = webRecords.get(index);
				}else{
					ele = ele.children().select(tag).get(index-1);
				}	
			}else{
				if(i == 0){
					ele = webRecords.select(tag).first();
				}else{
					ele = ele.children().select(tag).first();
				}		
			}
			if(ele == null) break;
			System.out.println("#############################");
			System.out.println(ele);
			System.out.println("#############################");
			
		}
		return ele;
	}

}
