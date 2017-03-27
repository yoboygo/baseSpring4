package tk.codecube.test.queue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import org.junit.Test;

/**
 * @dec 测试队列
 * @author songjl 2017年3月17日 上午9:17:00
 *
 */
public class TestQueue {
    
    @Test
    public void testBlockQueue() throws InterruptedException{
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        //每秒写一个
        Thread addTreadInfo = new Thread(() -> {
            
            Queue<Map<String,String>> queue = TreadInfoQueue.instance();
            while(true){
                Map<String,String> data = new HashMap<String,String>();
//                data.put(Thread.currentThread().getName(),dtf.format(LocalDateTime.now()));
                data.put(Thread.currentThread().getName(),LocalDateTime.now() + "");
//                boolean success = queue.offer(data,Integer.MAX_VALUE,TimeUnit.SECONDS);
                //超出Queue容量，弹出顶部丢弃
                while(!queue.offer(data)){
                    System.out.println("-丢弃数据" + queue.remove());
                }
                System.out.println("写入数据..." + data.toString());
                
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
           
        });
        
        // Map<String,String> data = queue.
        //没2秒读一次 只读最新的
        Thread getTreadInfo = new Thread(() -> {
            
            Queue<Map<String,String>> queue = TreadInfoQueue.instance();
            while(true){
                
                try {
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                Map<String,String> data = new HashMap<String,String>();
                System.out.print("读取数据...");
                if(queue.isEmpty()){
                    System.out.println("-数据为空");
                    continue;
                }
                if(queue.size() == 1){
                    data = queue.peek();
                }else{
                    System.out.print("-Queue 中数据大于 1 size = " + queue.size());
                    while(queue.size() > 1){
                        //丢弃过时数据，只保留最新的一条
                        queue.remove();
                    }
                    data = queue.peek();
                }
                System.out.println("-Queue:" + data.toString());
            }
            
        });
        
        //启动多线程
        addTreadInfo.start();
        getTreadInfo.start();
        
        Thread.currentThread().sleep(100000);
        addTreadInfo.stop();
        getTreadInfo.stop();
    }
    
}
