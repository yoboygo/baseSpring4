package tk.codecube.test.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
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

/**
 * Elasticsearch日志查询提供者
 * 主要发起请求并处理返回结果
 * @dec 
 * @author songjl 2017年3月20日 上午10:13:46
 *
 */
public abstract class AbstractElasticsearchProvider {
   
    /**
     * @Dec 主方法
     * @param params
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * 2017年3月20日 上午10:34:54 songjl
     */
    public List<String> search(Map<String,String> params) throws ClientProtocolException, IOException{
        
        String resultStr = doPost(params.get("serverUrl"),params.get("requestEntity"));
        List<String> logDatas = parseElasticaserchLogWithRegx(resultStr,params.get("logRegx"));
        return logDatas;
    }
    
    /**
     * @Dec 发起Post请求
     * @param url
     * @param requestEntity
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * 2017年3月20日 上午10:33:56 songjl
     */
    public String doPost(String url,String requestEntity) throws ClientProtocolException, IOException {
        
        HttpClientBuilder hb = HttpClientBuilder.create();
        CloseableHttpClient hc = hb.build();
        
        HttpPost hp = new HttpPost(url);
        HttpEntity entity = new StringEntity(requestEntity,"utf-8");
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
     * @param string
     * @return
     */
    public static List<String> parseElasticaserchLogWithRegx(String httpResult,String elasticsearchLogRegx) {
        List<String> resultLogs = new ArrayList<String>();
        
        Pattern messagePattern = Pattern.compile(elasticsearchLogRegx);
        Matcher messageMatcher = messagePattern.matcher(httpResult);
        while(messageMatcher.find()){
            
            String data = messageMatcher.group(1);
            resultLogs.add(data);
        }
        
        System.out.println("原始的日志数据：");
        System.out.println(resultLogs.toString());
        return resultLogs;
    }
   
}
