package tk.codecube.test.queue;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class TradeInfoCycleArray{
    
    private static CycleArray<Map<String,String>> datas;
    private static ReentrantLock lock = new ReentrantLock();
    
    private TradeInfoCycleArray() {};
    public static CycleArray<Map<String,String>> getInstance(){
        lock.lock();
        try{
            if(datas == null){
                datas = new CycleArray<Map<String,String>>(10);
            }
            
            return datas;
        }finally{
            lock.unlock();
        }
    }
}
