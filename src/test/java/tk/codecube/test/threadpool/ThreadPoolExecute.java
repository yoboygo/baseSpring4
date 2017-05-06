package tk.codecube.test.threadpool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * 测试Java线程池
 * @author songjl
 *
 */
public class ThreadPoolExecute {
    
    ExecutorService es = Executors.newFixedThreadPool(2);
    
    /**
     * @Dec 成功!!!
     * @throws InterruptedException
     * songjl 2017年4月27日 下午5:00:05
     */
    @Test
    public void testIteratorList() throws InterruptedException{
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < 10; ++i){
            list.add(i + "");
        }
        for(int i = 0; i < 2 ; ++i){
            es.execute(new PrintMsg(list));
        }
        es.awaitTermination(Integer.MAX_VALUE, TimeUnit.MINUTES);
        
    }
    
    /**
     * @Dec 成功！！
     * @throws InterruptedException
     * songjl 2017年4月27日 下午5:14:16
     */
    @Test
    public void testIteratorMap() throws InterruptedException{
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for(int i = 0; i < 10; ++i){
            Map<String,String> item = new HashMap<String,String>();
            item.put(i+"", i+"");
            list.add(item);
        }
        for(int i = 0; i < 2 ; ++i){
            es.execute(new PrintMsgMap(list));
        }
        es.awaitTermination(Integer.MAX_VALUE, TimeUnit.MINUTES);
        
    }
}

class PrintMsg implements Runnable{

    private List<String> params;
    
    public PrintMsg(List<String> params) {
        this.params = params;
    }
    
    @Override
    public void run() {
        for(String value : params){
            System.out.println(Thread.currentThread().getName() + "--->" + value);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}

class PrintMsgMap implements Runnable{
    
    private List<Map<String,String>> params;
    
    public PrintMsgMap(List<Map<String,String>> params) {
        this.params = params;
    }
    @Override
    public void run() {
        for(Map<String,String> value : params){
            System.out.println(Thread.currentThread().getName() + "--->" + value);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}