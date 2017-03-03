package tk.codecube.test.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.apache.log4j.Logger;


public class LoggingInvocationHandler implements InvocationHandler {

    private static final Logger LOGGER = Logger.getLogger(LoggingInvocationHandler.class);
    
    private Object receiverObject;
    
    
    public LoggingInvocationHandler(Object object) {
        this.receiverObject = object;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.info("调用方法 " + method.getName() + ";参数为 " + Arrays.deepToString(args));
        System.out.println("调用方法 " + method.getName() + ";参数为 " + Arrays.deepToString(args));
        return method.invoke(receiverObject, args);
    }
}
