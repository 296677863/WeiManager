package com.weiweb.crawler.util;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class MovieCrawler extends BreadthCrawler {

	public MovieCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		
	}
	
	public static void main(String args[]) throws Exception {
		MovieCrawler crawler=new MovieCrawler("movieCrawler", false);
		crawler.addSeed("http://www.dytt8.net/index.htm");
		crawler.start(6);
	}

}
