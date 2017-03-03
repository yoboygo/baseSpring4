package tk.codecube.test;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.Test;
import tk.codecube.test.invocation.LoggingInvocationHandler;

public class TestJava7 {
    
    /**
     * 动态代理方法调用
     */
    @Test
    public void testInvocation(){
        String str = "Hello Word";
        LoggingInvocationHandler hanler = new LoggingInvocationHandler(str);
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Comparable<String> obj = (Comparable<String>) Proxy.newProxyInstance(cl, new Class[] {Comparable.class}, hanler);
        obj.compareTo("Good");
        obj.equals("Good");
    }
    
    /**
     * fileChannel的文件复制
     * @throws IOException
     */
    @Test
    public void testFileChanel() throws IOException{
        String sorce = "src/test/resources/data/datav.txt";
        String dest = "src/test/resources/data/datav.bak";
        
        try(FileChannel sfc = FileChannel.open(Paths.get(sorce), StandardOpenOption.READ);
                FileChannel dfc = FileChannel.open(Paths.get(dest), StandardOpenOption.WRITE,StandardOpenOption.CREATE)){
            sfc.transferTo(sfc.position(), sfc.size(), dfc);
        }
    }
}
