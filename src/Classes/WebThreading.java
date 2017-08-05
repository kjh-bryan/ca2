/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kangj
 */
public class WebThreading implements Runnable{
    public int noOfThread;
    public String query;
    public int typeOfSearch;
    
    public WebThreading(int noOfThread,String query, int typeOfSearch)
    {
        this.noOfThread = noOfThread;
        this.query = query;
        this.typeOfSearch= typeOfSearch;
    }
    
    @Override
    public void run()
    {
        TestGrabManager t = new TestGrabManager();
        try {
            t.happy(noOfThread, query, typeOfSearch);
        } catch (IOException ex) {
        } catch (InterruptedException ex) {
        }
    }
}
