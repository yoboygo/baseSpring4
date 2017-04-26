package tk.codecube.test.queue;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class TradeInfoCycleArray<E> {
    
    private static CycleArray<Map<String,String>> datas;
    private TradeInfoCycleArray() {};
    private static ReentrantLock lock = new ReentrantLock();
    
    public static CycleArray<Map<String,String>> getInstance(int size){
        lock.lock();
        try{
            if(datas == null){
                datas = new CycleArray<Map<String,String>>(size);
            }
            
            return datas;
        }finally{
            lock.unlock();
        }
    }
}
