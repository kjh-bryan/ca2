/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author kangj
 */
public class GrabPage implements Callable<GrabPage>{
    static final int TIMEOUT = 5000;
    private URL url;
    private int depth;
    private Set<URL> urlList = new HashSet<>();
    private int googleOrBingOrNone;
    
    public GrabPage(URL url, int depth, int typeOfSearch){
        this.url = url;
        this.depth = depth;
        this.googleOrBingOrNone = typeOfSearch;
    }
    
    @Override
    public GrabPage call() throws Exception {
        Document document = null;
        System.out.println("doc Select : yahoo/bing/none : " +googleOrBingOrNone);
         String docSelect = "";
          switch(googleOrBingOrNone)
                {
                    case 1:
                        docSelect = "div.srg div.g div.rc h3.r a";
                        break;
                    case 2:
                        docSelect = "li.b_algo h2 a";
                        break;
                    case 3:
                        docSelect = "a[href]";
                        break;
                }
        System.out.println("Visiting (" + depth + "): " + url.toString());
        document = Jsoup.parse(url,TIMEOUT);
        System.out.println("GrabPage call selecting docSelect : " + docSelect);
        processLinks(document.select(docSelect));
        
        return this;
    }
    
    private void processLinks(Elements links)
    {
        for(Element link : links)
        {
            String href = link.attr("href");
            if(StringUtils.isBlank(href) || href.startsWith("#")){
                continue;
            } 
            
            try{
                URL nextUrl = new URL(url,href);
                urlList.add(nextUrl);
            } catch (MalformedURLException ex) {
            }
        }
    }
    public Set<URL> getUrlList(){
        return urlList;
    }
    public int getDepth(){
        return depth;
    }
}
