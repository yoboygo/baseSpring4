package tk.codecube.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

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
    
    /**
     * @Dec 读文件
     * @param path
     * @return
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     * @throws IOException
     * songjl 2017年5月6日 上午8:50:02
     */
    public static String readFile(String path) throws UnsupportedEncodingException, FileNotFoundException, IOException{
        StringBuilder ret = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"))){
            while(br.ready()){
                ret.append(br.readLine()).append("/n");
            }
        }
        return ret.toString();
    }
    
    /**
     * @Dec 写文件
     * @param content
     * @param path
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     * @throws IOException
     * songjl 2017年5月6日 上午9:20:35
     */
    public static void writeFile(String content,String path,boolean append) throws UnsupportedEncodingException, FileNotFoundException, IOException{
        try(FileWriterWithEncoding fw = new FileWriterWithEncoding(new File(path),"UTF-8",append)){
            fw.write(content);
            fw.flush();
        }
    }
    
    
    /**
     * @Dec get方法
     * @param _url
     * @param params
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     * 2017年5月11日 下午1:28:15 songjl
     */
    public static String doGet(String _url,Map<String,String> params) throws URISyntaxException, ClientProtocolException, IOException{
        HttpClient hc = HttpClientBuilder.create().build();
        HttpGet hg = new HttpGet();
        String url = _url + "?";
        for(Map.Entry<String, String> entry : params.entrySet()){
            url += entry.getKey() + "=" + entry.getValue() + "&";
        }
        hg.setURI(new URI(url));
        
        HttpResponse hp = hc.execute(hg);
        return EntityUtils.toString(hp.getEntity());
    }
    
    public static String doGet(String _url) throws URISyntaxException, ClientProtocolException, IOException{
        return doGet(_url,new HashMap<String,String>());
    }
    
}
