package tk.codecube.test.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestNio {
	@Test
	public void sumValues() throws Exception
	{
		String path = "http://118.178.246.100:8080/simpleResultAction.action?busiType=THTradeDetailBySystemPerMonthApplication";
		URL url = new URL(path);
		
		URLConnection uc = url.openConnection();
		uc.connect();
		InputStream is = uc.getInputStream();
		String str = convertStreamToString(is); 
		JSONArray datas = JSONArray.fromObject(str);
		
		
		System.out.println(sumer(datas).toString());
		System.out.println(deliver(datas).toString());
		/*
		HttpClientBuilder dhc = HttpClientBuilder.create();
		CloseableHttpClient chc = dhc.build();
		HttpGet hg = new HttpGet(path);
		HttpResponse hr = dhc.execute(hg);
		
		HttpEntity he = hr.getEntity();
		if(he != null)
		{
			InputStream is =  he.getContent();
			String str = convertStreamToString(is);  
            System.out.println("Do something");   
            System.out.println(str);  
		}
		*/
		
	}
	
	public static String convertStreamToString(InputStream is) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }  
	
	/**
	 * 累加器
	 * @param datas
	 * @return
	 */
	public JSONObject sumer(JSONArray datas)
	{
		JSONObject result = new JSONObject();
		for(Object obj : datas)
		{
			JSONObject json = JSONObject.fromObject(obj);
			String key = json.getString("s");
			String value = json.getString("y");
			
			Object preValue = result.get(key);
			if(preValue == null)
			{
				preValue = "0";
			}
			
			BigDecimal b1 = new BigDecimal(value);
			BigDecimal b2 = new BigDecimal(String.valueOf(preValue));
			
			result.put(key, b1.add(b2).toPlainString());
		}
		return result;
	}
	
	/**
	 * 区分不同的S轴
	 * @param datas
	 * @return
	 */
	public JSONObject deliver(JSONArray datas)
	{
		JSONObject result = new JSONObject();
		for(Object obj : datas)
		{
			JSONObject json = JSONObject.fromObject(obj);
			String key = json.getString("s");
			JSONArray item = JSONArray.fromObject(result.get(key) == null ? "[]":result.get(key));
			item.add(json);
			result.put(key, item);
		}
		return result;
	}
}
