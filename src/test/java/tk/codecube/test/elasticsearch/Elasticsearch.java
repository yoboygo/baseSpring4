package tk.codecube.test.elasticsearch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 日志查询
 * @author songjl
 *
 */
public class Elasticsearch {

    public static void main(String[] args) throws Exception {
        int length = args.length;
        if(length % 2 != 0 || length == 0){
            System.out.println("参数个数不正确~ use: Elasticsearch configpath configpath  paramterName paramterValue");
            System.exit(0);
        } 
       
        Map<String,String> params = new HashMap<String,String>();
        for(int i = 0; i < args.length; ++i){
            params.put(args[i], args[++i]);
        }
        
        String configPath = params.remove("configpath");
        //读取参数模板
        String configModel = readConfig(configPath);
        //拼接查询参数
        String requestParamters = assembelRequestParamters(configModel,params);
        //发起请求
        String httpResult = doPost(requestParamters);
        //将从返回的结果中提取原始的日志
        List<String> logData = parseElasticaserchLogNoJson(httpResult);
        //解析原始日志，生成标准化的数据
        JSONArray datas = parseLogStr(logData);
        
        System.out.println(JSONObject.fromObject(datas).toString());
    }

    /**
     * 将原始的日志解析成标准格式
     * @param logData
     * @return
     */
    public static JSONArray parseLogStr(List<String> logData) {

        Pattern contentPattern = Pattern.compile("\\{.*\\}");
        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        JSONArray result = new JSONArray();
        for(String log : logData){
            JSONObject data = new JSONObject();
            Matcher contentMatcher = contentPattern.matcher(log);
            Matcher dateMatcher = datePattern.matcher(log);
            
            if(contentMatcher.find()){
                data = JSONObject.fromObject(contentMatcher.group());
            }
            if(dateMatcher.find()){
                data.put("logtime", dateMatcher.group());
            }
            result.add(data);
        }
        System.out.println("标准化的数据：");
        System.out.println(result.toString());
        return result;
    }

    /**
     * 发起Post请求
     * @param requestPathaters
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public static String doPost(String requestPathaters) throws ClientProtocolException, IOException {
        String url = "http://10.139.113.29:10592/_search";
        HttpClientBuilder hb = HttpClientBuilder.create();
        CloseableHttpClient hc = hb.build();
        
        HttpPost hp = new HttpPost(url);
        HttpEntity entity = new StringEntity(requestPathaters,"utf-8");
        hp.setEntity(entity);
        
        CloseableHttpResponse response = hc.execute(hp);
        HttpEntity he = response.getEntity();
//        return parseElasticaserchLog(EntityUtils.toString(he));
        String result = EntityUtils.toString(he);
        System.out.println("HTTP返回数据：");
        System.out.println(result);
        return result;
    }

    /**
     * 解析ElasticaserchLog日志
     * 因为JSON Lib 解析的问题，此方法并不能正确执行，留待研究
     * @param string
     * @return
     */
    @Deprecated
    public static List<String> parseElasticaserchLog(String httpResult) {
        List<String> resultLogs = new ArrayList<String>();
        JsonConfig jc = new JsonConfig();
//        jc.addIgnoreFieldAnnotation("message");
        MessageProcesser mp = new MessageProcesser();
        jc.registerJsonValueProcessor("message", mp);
        jc.registerPropertyExclusions(String.class, new String[]{"message"});
        JSONObject datas = JSONObject.fromObject(httpResult,jc);
        JSONArray arrayLogs = datas.getJSONObject("hits").getJSONArray("hits");
        if(arrayLogs.isEmpty())
        {
            return resultLogs;
        }
        @SuppressWarnings("unchecked")
        Iterator<Object> logs = arrayLogs.iterator();
        while(logs.hasNext()){
            resultLogs.add(JSONObject.fromObject(logs.next()).getJSONObject("_source").getString("message"));
        }
        System.out.println("原始的日志数据：");
        System.out.println(JSONObject.fromObject(resultLogs).toString());
        return resultLogs;
    }
    /**
     * 解析ElasticaserchLog日志
     * @param string
     * @return
     */
    public static List<String> parseElasticaserchLogNoJson(String httpResult) {
        List<String> resultLogs = new ArrayList<String>();
        
        Pattern messagePattern = Pattern.compile("\"message\":(.*\\})\",");
        Matcher messageMatcher = messagePattern.matcher(httpResult);
        while(messageMatcher.find()){
            
            String data = messageMatcher.group(1);
            resultLogs.add(data);
        }
        
        System.out.println("原始的日志数据：");
        System.out.println(resultLogs.toString());
        return resultLogs;
    }

    /**
     * 拼接查询参数
     * @param configModel
     * @param params
     * @return
     * @throws Exception 
     */
    public static String assembelRequestParamters(String configModel, Map<String, String> params) throws Exception {
        for(Map.Entry<String, String> e : params.entrySet()){
            configModel = configModel.replace("#"+e.getKey()+"#", e.getValue());
        }
        System.out.println(configModel);
        
        if(configModel.indexOf("#") != -1){
            throw new Exception("参数不完整~");
        }
        
        return configModel;
    }

    /**
     * 读取参数模板
     * @param string
     * @return
     * @throws IOException 
     */
    public static String readConfig(String configpath) throws IOException {
        
        StringBuilder result = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(configpath)))){
            while(br.ready()){
                result.append(br.readLine());
            }
        }
       
        return result.toString();
    }
}

class MessageProcesser implements JsonValueProcessor{

    @Override
    public Object processArrayValue(Object arg0, JsonConfig arg1) {
        return arg0;
    }

    @Override
    public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
        return arg1;
    }
    
}
