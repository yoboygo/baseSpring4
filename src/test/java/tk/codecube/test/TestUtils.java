package tk.codecube.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    
    /**
     * 计算两个日期的间隔
     * 如果没有间隔，返回空列表
     * @param preDate
     * @param date
     * @return
     * @throws ParseException 
     */
    public static List<String> calculateIntervalDays(String preDate, String date) throws ParseException {
        String  format = "yyyyMMdd";
        return calculateIntervalDays(preDate, date,format);
    }

    /**
     * 根据format 返回两个日期的间隔时间
     * @param preDate
     * @param date
     * @param format
     * @return
     * @throws ParseException 
     */
    private static List<String> calculateIntervalDays(String startDate,
            String endDate, String format) throws ParseException {
        
        List<String> ret = new ArrayList<String>();
        
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        long days = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24) - 1;
        for(long i = 0; i< days ; ++i){
            cal.add(Calendar.DAY_OF_MONTH, 1);
            ret.add(sdf.format(cal.getTime()));
        }
        return ret;
    }
}
