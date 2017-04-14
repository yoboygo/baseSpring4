package tk.codecube.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class TestUtils {
    
    /**
     * @Dec 将MAP转换成Bean
     * @param data
     * @param clazz
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * 2017年4月14日 下午2:24:10 songjl
     */
    public static Object MapToBean(Map<String,Object> data,Class<?> clazz) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
        
        Object obj = clazz.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for(PropertyDescriptor pd : pds)
        {
            String key = pd.getName();
            if(data.containsKey(key)){
                Method method = pd.getWriteMethod();
                method.invoke(obj, data.get(key));
            }
        }
        return obj;
    }
}
