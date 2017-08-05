/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author kangj
 */
public class GrabManager {
	public static int THREAD_COUNT = 0;
	private static final long PAUSE_TIME = 1000;

	private Set<URL> masterList = new HashSet<>();
	private List<Future<GrabPage>> futures = new ArrayList<>();
	private ExecutorService executorService;
    
    private String urlBase;
    private final int maxDepth;
    private final int maxUrls;

    public GrabManager(int threadCount,int maxDepth, int maxUrls)
    {
        this.THREAD_COUNT = threadCount;
        this.maxDepth = maxDepth;
	this.maxUrls  = maxUrls;
        executorService =  Executors.newFixedThreadPool(THREAD_COUNT);
    }
    
    public void go(URL start,int googleOrBingOrNone) throws IOException, InterruptedException {
        switch(googleOrBingOrNone)
        {
            case 1:
                urlBase = "";
                break;
            case 2:
                urlBase = "";
                break;
            case 3:
                urlBase = start.toString().replaceAll("(.*//.*/).*", "$1");
                break;
            default:
                break;
        }
    
        StopWatch stopWatch = new StopWatch();
        
        stopWatch.start();
        submitNewURL(start,0,googleOrBingOrNone);
        
	while (checkPageGrabs(googleOrBingOrNone));
        stopWatch.stop();
        
        System.out.println("Found : " + masterList.size() + " urls");
        System.out.println("in " + stopWatch.getTime() / 1000 + " seconds");
        
    }
    
    private boolean checkPageGrabs(int googleOrBingOrNone) throws InterruptedException {
        Thread.sleep(PAUSE_TIME);
        Set<GrabPage> pageSet = new HashSet<>();
        Iterator<Future<GrabPage>> iterator = futures.iterator();
        
        while(iterator.hasNext())
        {
            Future<GrabPage> future = iterator.next();
            if(future.isDone())
            {
                iterator.remove();
                try{
                    pageSet.add(future.get());
                }   catch (InterruptedException e) {  // skip pages that load too slow
				} catch (ExecutionException e) {
				}
            }
        }
        for (GrabPage grabPage : pageSet){
            addNewURLs(grabPage,googleOrBingOrNone);
            
        }
        return (futures.size() > 0);
    }
    
    private void addNewURLs(GrabPage grabPage,int googleOrBingOrNone) {
        for(URL url : grabPage.getUrlList()){
            if(url.toString().contains("#")){
                try{
                    url = new URL(StringUtils.substringBefore(url.toString(), "#"));
                }catch(MalformedURLException  e){
                    
                }
            }
            
            submitNewURL(url , grabPage.getDepth() + 1,googleOrBingOrNone);
        }
    }
    
    private void submitNewURL(URL url, int depth, int googleOrBingOrNone){
        if(shouldVisit(url, depth,googleOrBingOrNone)){
            masterList.add(url);
            GrabPage grabPage = new GrabPage(url,depth,googleOrBingOrNone);
            Future<GrabPage> future = executorService.submit(grabPage);
            futures.add(future);
        }
    }
    
    private boolean shouldVisit(URL url, int depth,int googleOrBingOrNone) {
        if (masterList.contains(url)) {
            return false;
        }
        if (!url.toString().startsWith(urlBase) && googleOrBingOrNone == 3) {
            return false;
        }
        if (url.toString().endsWith(".pdf")) {
            return false;
        }
        if (depth > maxDepth) {
            return false;
        }
        if (masterList.size() >= maxUrls) {
            return false;
        }
        return true;
    }
    public void write(String path) throws IOException {
		FileUtils.writeLines(new File(path), masterList);
	}
    
    public ObservableList<String> getList(){
            ObservableList<String> listToReturn =  FXCollections.observableArrayList();
            for(URL m : masterList)
            {
                listToReturn.add(m.toString());
            }
            
            executorService.shutdown();
            while(true){
                try{
                    System.out.println("Waiting for the service to terminate..");
                    if(executorService.awaitTermination(5, TimeUnit.SECONDS))
                    {
                        break;
                    }
                }
                catch(InterruptedException ex)
                {
                    
                }
            }
            System.out.println("Done shut down");
            return listToReturn;
            
            
    }
}
