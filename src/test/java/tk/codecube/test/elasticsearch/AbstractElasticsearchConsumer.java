package tk.codecube.test.elasticsearch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Elasticsearch日志查询消费者
 * @dec 
 * @author songjl 2017年3月20日 上午10:03:44
 *
 */
public abstract class AbstractElasticsearchConsumer {
    
    private AbstractElasticsearchProvider abstractElasticsearchProvider;
    
    /**
     *  配置模板的路径
     */
    private String configpath;
    
    /**
     * 配置模板
     */
    private String configModel;
    
    /**
     * 可配置参数，用于替换配置模板中的占位符
     */
    private Map<String,String> params = new HashMap<String,String>();
    
    /**
     * elasticsearch 配置
     */
    private Map<String,String> elasticsearchConfig = new HashMap<String,String>();
    
    //以下参数全部配置到elasticsearchParams中,全是必要参数
    /**
     * Elasticsearch 服务器
     */
//    private String serverUrl = "http://10.139.113.29:10592/_search";
    
    /**
     * 从Elasticsearch日志中取出原始日志的正则，务必将原始日志匹配到group(1)中，即用（）括起来
     */
//    private String logRegx = "\"message\":(.*\\})\",";
    
    /**
     * 从原始日志中取出所需的日志内容的正则，匹配group(1)
     */
    private String contentRegx = "(\\{.*\\})";
    
    /**
     * 从原始日志中提取日志的生成时间，匹配group(1)
     */
    private String logDateRegx = "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})";
    
    
    /**
     * @throws Exception 
     * @Dec 初始化方法
     */
    public void init() throws Exception{
        configModel = readConfig();
        String requestEntity = assembelRequestParamters(configModel, elasticsearchConfig);
        params.put("requestEntity", requestEntity);
    }
    
    /**
     * @Dec 定时调度
     * @throws Exception
     * 2017年3月20日 上午11:18:28 songjl
     */
    public void execute() throws Exception{
//       search 请求体requestEntity
        List<String> logDatas = abstractElasticsearchProvider.search(params);
        JSONArray datas = parseLogStr(logDatas, contentRegx, logDateRegx);
        //将logDateRegx推送到ONS
        
    }
    
    /**
     * 读取参数模板
     * @param string
     * @return
     * @throws IOException 
     */
    public String readConfig() throws IOException {
        
        StringBuilder result = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(configpath)))){
            while(br.ready()){
                result.append(br.readLine());
            }
        }
       
        return result.toString();
    }
    
    /**
     * 拼接查询参数
     * @param configModel
     * @param params
     * @return
     * @throws Exception 
     */
    public String assembelRequestParamters(String configModel, Map<String, String> params) throws Exception {
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
     * 具体的解析逻辑应该放到实现类中，Provider只负责返回原始的日志数据
     * @Dec 将原始的日志解析成标准格式
     * @param logData
     * @param contentRegx
     * @param dateRegx
     * @return
     * 2017年3月20日 上午10:34:32 songjl
     */
    public JSONArray parseLogStr(List<String> logDatas,String contentRegx,String dateRegx) {

       Pattern contentPattern = Pattern.compile(contentRegx);
       Pattern datePattern = Pattern.compile(dateRegx);
       
       JSONArray result = new JSONArray();
       for(String log : logDatas){
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
    
    public String getConfigpath() {
        return configpath;
    }

    
    public void setConfigpath(String configpath) {
        this.configpath = configpath;
    }

    
    public Map<String, String> getParams() {
        return params;
    }

    
    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    
    public String getConfigModel() {
        return configModel;
    }

    
    public void setConfigModel(String configModel) {
        this.configModel = configModel;
    }

    
    public AbstractElasticsearchProvider getAbstractElasticsearchProvider() {
        return abstractElasticsearchProvider;
    }

    
    public void setAbstractElasticsearchProvider(
            AbstractElasticsearchProvider abstractElasticsearchProvider) {
        this.abstractElasticsearchProvider = abstractElasticsearchProvider;
    }

    
    public Map<String, String> getElasticsearchConfig() {
        return elasticsearchConfig;
    }

    
    public void setElasticsearchConfig(Map<String, String> elasticsearchConfig) {
        this.elasticsearchConfig = elasticsearchConfig;
    }

    
}
