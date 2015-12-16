package com.sh.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.sh.util.SelectorUtil;

public class TestSelectorUtil {
	
	@Test
	public void test01(){
		//listRange   <div id=category@@class=clearfix>		
		//<p class=describe>;<div class=article-content fontSizeBig>
		String src = "div class=article-content fontSizeBig";
		String regex = "[a-zA-Z]+=.+ ";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(src);
		while(matcher.find()){
			System.out.println(matcher.group());
		}
		
	}
	
	@Test
	public void test02(){
		String str = "<div id=category@@class=clearfix>";
		System.out.println(SelectorUtil.tagString2SelectorQuery(str));
	}

}
