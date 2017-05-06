package tk.codecube.test.queue;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
            
            Queue<Map<String,String>> queue = TradeInfoQueue.instance();
            while(true){
                Map<String,String> data = new HashMap<String,String>();
//                data.put(Thread.currentThread().getName(),dtf.format(LocalDateTime.now()));
                data.put(Thread.currentThread().getName(),LocalDateTime.now() + "");
//                boolean success = queue.offer(data,Integer.MAX_VALUE,TimeUnit.SECONDS);
                //超出Queue容量，弹出顶部丢弃
//                while(!queue.offer(data)){
//                    System.out.println("-丢弃数据" + queue.remove());
//                }
                queue.add(data);
                System.out.println("写入数据..." + data.toString());
                
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
           
        });
        
        // Map<String,String> data = queue.
        //每2秒读一次 只读最新的
        Thread getTreadInfo = new Thread(() -> {
            
            Queue<Map<String,String>> queue = TradeInfoQueue.instance();
            while(true){
                
                try {
                    Thread.currentThread().sleep(20000);
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
    
    
    /**
     * 测试循环列表
     */
    @Test
    public void testCycleArray(){
        CycleArray<Integer> cl = new CycleArray<Integer>(10);
        int i; 
        for(i = 0 ; i < 8 ; ++i){
            cl.add(i);
        }
        
        for(i = 0; i < 10 ; ++i){
            System.out.println(i + ":" + cl.get(i));
        }
    }
    
    /**
     * @Dec 测试阻塞的循环数组
     * @throws InterruptedException
     * songjl 2017年4月26日 下午3:47:27
     */
    @Test
    public void testBlockCycleArray() throws InterruptedException{
        
        //写线程
        Thread writeThread = new Thread(() -> {

            while(true){
                
                Map<String,String> data = new HashMap<String,String>();
                String value = LocalTime.now().toString();
                data.put("write", value);
                TradeInfoCycleArray.getInstance().add(data);
                System.out.println("写入数据：" + value);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        });
        
        //读线程
        Thread readThread = new Thread(() -> {
            
            while(true){
                
                System.out.println("=========开始读取数据===========");
                CycleArray<Map<String,String>> datas = TradeInfoCycleArray.getInstance();
                int size = datas.capacity();
                for(int i = 0; i < size ; ++i){
                    System.out.println( i + ":" + datas.get(i) );
                }
                System.out.println("=========结束读取数据===========");
                
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        writeThread.start();
        readThread.start();
        
        Thread.sleep(100000);
        writeThread.stop();
        readThread.stop();
    }
}
