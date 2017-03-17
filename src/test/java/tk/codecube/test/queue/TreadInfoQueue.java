package tk.codecube.test.queue;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TreadInfoQueue {
    
    private static Queue<Map<String,String>> queue;
    
    private TreadInfoQueue(){
        
    }
    
    public static Queue<Map<String,String>> instance(){
        if(queue == null){
            
            synchronized (TreadInfoQueue.class) {
                if(queue == null){
//                    queue = new LinkedBlockingQueue<Map<String,String>>();
                    queue = new ArrayBlockingQueue<Map<String,String>>(10);
                }
            }
        }
        return queue;
    }
    
}