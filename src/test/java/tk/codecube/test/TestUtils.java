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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
    
    /**
     * @Dec 将JSONArray转换成List
     * @param datas
     * @return
     * 2017年5月12日 上午11:21:51 songjl
     */
    public static List<Map<String,Object>> convertJSONArrayToList(JSONArray datas){
        List<Map<String,Object>> retVal = new ArrayList<Map<String,Object>>();
        for(Object data : datas){
            Map<String,Object> item = new HashMap<String,Object>();
            JSONObject jo = JSONObject.fromObject(data);
            Iterator<?> iterator = jo.keys();
            while(iterator.hasNext()){
                String key = iterator.next().toString();
                item.put(key, jo.get(key));
            }
            retVal.add(item);
        }
        return retVal;
    }
    
    /**
     * @Dec 除一个除数
     * @param datas
     * @param divide
     * @return
     * 2017年5月12日 下午1:59:44 songjl
     */
    public static List<Map<String,Object>> convertJSONArrayToList(JSONArray datas,BigDecimal divide,String field){
        List<Map<String,Object>> retVal = new ArrayList<Map<String,Object>>();
        for(Object data : datas){
            Map<String,Object> item = new HashMap<String,Object>();
            JSONObject jo = JSONObject.fromObject(data);
            Iterator<?> iterator = jo.keys();
            while(iterator.hasNext()){
                String key = iterator.next().toString();
                String val = jo.getString(key);
                if(key.equals(field)){
                    
                    val = new BigDecimal(val).divide(divide).toPlainString();
                }
                item.put(key, val);
            }
            retVal.add(item);
        }
        return retVal;
    }
    
    /**
     * @Dec 测试解析字符串
     * @param purchaseCount
     * @return
     * @throws ParseException
     * 2017年5月13日 上午9:35:22 songjl
     */
    public static String buildPurchaseOffLineCount(List<Map<String,Object>> purchaseCount) throws ParseException {
        List<Map<String,Object>> ret = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> temp = new ArrayList<Map<String,Object>>();
        Set<String> keyValidata = new HashSet<String>();
        
        String preDate = null;
        for(Map<String,Object> data : purchaseCount){
            Map<String,Object> item = new HashMap<String, Object>();
            
            //计算两个日期间的间隔天数，用0补全节假日的数据
            if(preDate != null){
                List<String> missDays = calculateIntervalDays(preDate,data.get("date").toString());
                for(String d : missDays){
                    Map<String,Object> e = new HashMap<String, Object>();
                    String ix = StringUtils.substring(d,6,8);
                    if(keyValidata.contains(ix)){
                        ix = ix + "'";
                    }
                    keyValidata.add(ix);
                    String[] val = {"0"}; 
                    e.put("x", ix);
                    e.put("y", val);
                    temp.add(e);
                }
            }
            preDate = data.get("date").toString();

            
            String x = StringUtils.substring(data.get("date").toString(),6,8);
            if(keyValidata.contains(x)){
                x = x + "'";
            }
            keyValidata.add(x);
            
            String[] val = {data.get("successCount").toString()};
            item.put("x", x);
            item.put("y", val);
            temp.add(item);
        }
        //过滤出30个
        int start = temp.size() - 31;
        start = start > 0 ? start : 0;
        for(int i = start ; i < temp.size() ; ++i){
            ret.add(temp.get(i));
        }
        return JSONArray.fromObject(ret).toString();
    }
    
    /**
     * @Dec 将一个对象装换成JSONObject
     * @param o
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * 2017年5月13日 下午6:13:23 songjl
     */
    public static JSONObject objectToJson(Object o) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        
        JSONObject ret = new JSONObject();
        BeanInfo bi = Introspector.getBeanInfo(o.getClass());
        PropertyDescriptor[] bpds = bi.getPropertyDescriptors();
        for(PropertyDescriptor bpd : bpds){
            Method read = bpd.getReadMethod();
            Object val = read.invoke(o);
            ret.put(bpd.getName(),val);
        }
        return ret;
    }
    
    public static JSONObject objectToJsonUseFields(Object o) throws IllegalArgumentException, IllegalAccessException {
        JSONObject ret = new JSONObject();
        Field[] fields = o.getClass().getDeclaredFields();
        for(Field field : fields){
           Object val = field.get(o);
           ret.put(field.getName(), val);
        }
        return ret;
    }
}
