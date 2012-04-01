package ui;

import java.util.ArrayList;

public class StatusPublisher {

    private ArrayList<StatusListener> listeners=new ArrayList<StatusListener>();
    
    private static StatusPublisher instance=null;
    private StatusPublisher() { 
        
    }
    
    
    public static StatusPublisher getInstance() {
        if (null==instance) {
            instance=new StatusPublisher();
        }
        return instance;
    }
    
    public void addListener(StatusListener l) {
        listeners.add(l);
    }
    
    public void statusUpdate(String msg) {
        
        for (StatusListener l:listeners) {
            l.statusUpdate(msg);
            
        }
    }
}
