package com.weiweb.crawler.util;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

@Component("pictureCrawler")
public class PictureCrawler extends BreadthCrawler {
	private static CloseableHttpClient httpClient = HttpClients.createDefault();// 默認生成的HttpClient;
	

	public PictureCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
//		 addRegex("http://www.meitulu.com/t/*");
//	        addRegex("http://www.meitulu.com/*/*");
//		 this.addRegex("http://www.meitulu.com/.*/.*");
		
	}
	
	public void meituPicCrawler() {
		
	}

	@Override
	public void visit(Page page, CrawlDatums crawlDatums) {
		Links nextLink=new Links();
        System.out.println("正在抽取"+page.url()); 
        String title=page.doc().title(); 
        Elements links = page.doc().getElementsByTag("a");
        for (Element link : links) {
        	
            String linkHref = link.attr("href");
            String linkText = link.text();
            String linkTitle=link.attr("title");
            if(linkHref.contains("down")){
          	  //System.out.println("無用頁面"+linkHref);
          	  continue;
            }
            //System.out.println(linkHref);
           if((linkHref.indexOf("meitulu.com"))>-1){
	             nextLink.add(linkHref);
	            System.out.println(linkHref+"...."+linkTitle+"......"+linkText);
	            	 
           }
           //System.out.println(linkText);
          }
        Elements images=page.doc().getElementsByTag("img");
        for(Element image:images){
        	String imageUrl=image.attr("src");
        	String css=image.className();
        	if(css.equals("content_img")) {
        		System.out.println(imageUrl);
        	}
        	if(imageUrl.contains("small")||imageUrl.contains("Thumbs/files")||imageUrl.contains("setup")){
        		continue;
        	}else {
        		
//        		HttpGet httpGet=new HttpGet(imageUrl);
//       		 	try {
//					HttpResponse response=httpClient.execute(httpGet);
//					
//					InputStream in=response.getEntity().getContent();
//					
//					BufferedImage srcImage = null;
//					srcImage = ImageIO.read(in);
//					int srcImageHeight = srcImage.getHeight();
//					int srcImageWidth = srcImage.getWidth();
//					System.out.println(srcImageHeight+">>"+srcImageWidth);
//					System.out.println(imageUrl+"...."+in.available());
//					if(srcImageHeight>600&&srcImageWidth>800){
//						in.close();
//						System.out.println(imageUrl);
//						continue;
//					}
//					in.close();
//				} catch (ClientProtocolException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//       		 
        		
        	}
        	
        }
       crawlDatums.add(nextLink);
        
        
	}
	
	public static void main(String args[]) throws Exception {
		PictureCrawler picCrawler=new PictureCrawler("crawl", false);
		picCrawler.addSeed("https://www.meitulu.com/item/8878.html");
		picCrawler.start(5);
	}
	
}
