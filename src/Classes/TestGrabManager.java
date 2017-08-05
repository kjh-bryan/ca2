/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import org.junit.Test;
 
import java.io.IOException;
import java.net.URL;
import javafx.collections.ObservableList;
 
public class TestGrabManager {
	@Test
	public ObservableList<String> happy(int threadCount, String query, int typeOfSearch) throws IOException, InterruptedException {
		GrabManager grabManager = new GrabManager(threadCount,2, 10);
		 switch(typeOfSearch)
                {
                    case 1:
                        query = "https://www.google.com/search?q=" + query;
                        break;
                    case 2:
                        query = "https://www.bing.com/search?q=" + query;
                        break;
                    case 3:
                        query = query;
                        break;
                }
		grabManager.go(new URL(query),typeOfSearch);
                ObservableList<String> returning = grabManager.getList();
		grabManager.write("urllist.txt");
                return returning;
	}
 
}