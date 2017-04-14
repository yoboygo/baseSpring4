/**
 * TestOthers.java
 * 
 * Aimy
 * 下午2:14:24
 */
package tk.codecube.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.xml.sax.SAXException;
import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tk.codecube.test.aop.springcore.entry.IWaiter;
import tk.codecube.test.aop.springcore.entry.impl.NaiveWaiter;
import tk.codecube.test.aop.springcore.entry.impl.SimpleWaiter;
import tk.codecube.test.elasticsearch.CycleArrayList;
import tk.codecube.test.elasticsearch.Elasticsearch;

/**
 * 除Spring之外的各种小的Case
 * @author Aimy
 * 2015年2月27日 下午2:14:24
 */
public class TestOthers {

	
	/**
	 *  测试读取Data文件
	 * @author Aimy
	 * 2015年2月27日 下午2:15:14
	 */
	@Test
	public void testReadData()
	{
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(new File("./src/test/resources/testData.data")));
			System.out.println(dis.read());
			
//			File file = new File("");
//			System.out.println(file.getCanonicalPath());
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public <E> List<E> getInit()
	{
		return new ArrayList<E>();
	}
	
	@Test
	public void testList()
	{
		ArrayList<Integer> al  = new ArrayList<Integer>();
		for(int i=0;i<10;++i)
		{
			al.add(i);
		}
		printList(al);
	}
	//使用递归遍历List
	public void printList(ArrayList<Integer> list)
	{
		System.out.println(list.remove(0));
		if(list.size() > 0)
		{
			printList(list);
		}
	}
	
	/**
	 * ThreadStack：线程调用堆栈测试
	 */
	@Test
	public void testThreadStack()
	{
		System.out.println("================开始===============");

		printThreadStack();
		
		System.out.println("================结束===============");
		
	}
	public void printThreadStack()
	{
		StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
		for(StackTraceElement e:stackTraceElement)
		{
			System.out.println(e.getClassName()+"	--->	"+e.getMethodName());
		}
	}
	
	/**
	 * 测试Dom解析XML
	 * @throws DocumentException
	 * @throws SAXException 
	 * @throws IOException 
	 */
	@Test
	public void testXmlSchema() throws DocumentException, SAXException, IOException
	{
		String fileInPath = "config/taskConfig.xml";
		String schemaPath = "config/taskConfig.xsd";
		
		URL fileBasePath = ClassLoader.getSystemResource("");
		
		InputStream isIn =  ClassLoader.getSystemResourceAsStream(fileInPath);
		
		SAXReader sr = new SAXReader();
		Document doc = sr.read(isIn);
		
		Element root = doc.getRootElement();
		System.out.println(root.asXML());
		
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File(fileBasePath+schemaPath));
		Validator validator = schema.newValidator();
		try{
			validator.validate(new StreamSource(new File(fileBasePath+fileInPath)));
		}catch(SAXException e){
			System.out.println("校验失败！");
		}
		
	}
	
	/**
	 * 测试浮点型精度
	 */
	@Test
	public void testDecimalScale()
	{
		DecimalFormat df = new DecimalFormat("#0.00");
		double num = 1602.5f;
		System.out.println(df.format(num));
	}
	
	@Test
	public void testInstance()
	{
		NaiveWaiter nw = new NaiveWaiter();
		SimpleWaiter sw = new SimpleWaiter();
		
		System.out.println(nw instanceof IWaiter);
		System.out.println(sw instanceof IWaiter);
		
	}
	
	  /**
     * 按小时分组合计
     * @param jsonStr
     * @return
     */
    @SuppressWarnings("unused")
	private String sumCountByHour(String jsonStr)
    {
    	Assert.assertNotNull(jsonStr);
    	JSONArray datas = JSONArray.fromObject(jsonStr);
    	JSONArray results = new JSONArray();
    	Map<String,String[]> tmpData = new HashMap<String, String[]>();
    	for(Object item : datas)
    	{
    		JSONObject data = JSONObject.fromObject(item);
    		//时间格式
    		String time = data.getString("x");
    		String[] count = (String[])data.get("y");
    		
    		String hh = time.split(":")[0];
    		
    		String[] preCount = tmpData.get(hh);
    		if(preCount == null)
    		{
    			preCount = new String[]{"0"};
    		}

			long preCountValue = Long.parseLong(preCount[0]);
			long countValue = Long.parseLong(count[0]);
			long totalCount = preCountValue+countValue;
			count[0] = String.valueOf(totalCount);
			tmpData.put(hh, count);
    	}
    	
    	for(Map.Entry<String, String[]> entry : tmpData.entrySet())
    	{
    		results.add(JSONObject.fromObject(entry));
    	}
    	return results.toString();
    }
    
    /**
	 * 生成数据大屏需要的JSON对象
	 * @param itemCount
	 * @param s
	 * @param cf
	 * @return
	 * @throws Exception
	 */
	public String createJsonData(int itemCount, int s, ChronoField cf,String path) throws Exception{
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
		LocalDateTime ldt = LocalDateTime.now();
		Random rd = new Random();
		
		JSONArray ret = new JSONArray();
		
		for(int i = 1; i <= itemCount; ++i){
			String x = ldt.format(dtf);
			for(int j = 1; j <= s; ++j){
				JSONObject itemData = new JSONObject();
				itemData.put("x", String.valueOf(x));
				itemData.put("y", String.valueOf(rd.nextInt(999)));
				itemData.put("s", String.valueOf(j));
				
				//为动态添加图例做准备
				if(StringUtils.isNotBlank(path)){
					List<String> itemName = readFileToList(path);
					int index = j - 1;
					itemData.put("name", itemName.get(index));
//					itemData.put("value", j);//跟s字段相同
				}
				
				ret.add(itemData);
			}
			switch(cf){
				case YEAR:
					//增加1年
					ldt = ldt.plusYears(1);break;
				case MONTH_OF_YEAR:
					//增加1月
					ldt = ldt.plusMonths(1);break;
				case DAY_OF_YEAR:
					//增加1天
					ldt = ldt.plusDays(1);break;
				default:
					System.err.println("不持的参数~");
					throw new Exception("仅支持年月日~");
					
			}
		}
		return ret.toString();
	}
	
	/**
	 * 从文件中读取数据行，返回list
	 * @throws FileNotFoundException 
	 * @throws IOException
	 */
	public List<String> readFileToList(String path) throws FileNotFoundException, IOException{
//		String path = "src/test/resources/data/datav.txt";
		List<String> ret =  new ArrayList<String>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(new File(path)))){
		    while(br.ready()){
	            ret.add(br.readLine());
	        }
		}
		return ret;
	}
	
	
	/**
	 * 获取每年的数据
	 * @param itemCount
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public String getYearData(int itemCount,int s) throws Exception{
		return createJsonData(itemCount,s,ChronoField.YEAR,null);
	}
	/**
	 * 获取每月的数据
	 * @param itemCount
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public String getMonthData(int itemCount,int s) throws Exception{
		return createJsonData(itemCount,s,ChronoField.MONTH_OF_YEAR,null);
	}
	/**
	 * 获取每天的数据
	 * @param itemCount
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public String getDayData(int itemCount,int s) throws Exception{
		String path = "src/test/resources/data/datav.txt";
		return createJsonData(itemCount,s,ChronoField.DAY_OF_YEAR,path);
	}
	
	/**
	 * 测试生成的JSON
	 * @throws Exception
	 */
	@Test
	public void testCreateJsonData() throws Exception{
		
		String yearData = getYearData(5, 3);
		String monthData = getMonthData(6, 3);
		String dayData =  getDayData(15, 3);
		
		System.out.println(yearData);
		System.out.println(monthData);
		System.out.println(dayData);
	}
	
	/**
	 * 测试继承关系中的getClass()
	 * 在父类中调用getClass()方法,返回的是子类
	 */
	@Test
	public void testExtends(){
		SubClass sc = new SubClass();
		
		sc.invokeGetClass();
		sc.invokGetClassInSubClass();
	}
	
	/**
	 * 测试Java中发起HTTP请求
	 * @throws IOException 
	 */
	public String doHttpPost(String _url,String param) throws IOException{
	    //TODO 测试失败
		URL url = new URL(_url);
//		String action = "_search";
		
		URLConnection uc = url.openConnection();
		uc.setRequestProperty("Content-Type",
                  "application/x-www-form-urlencoded");
		uc.setRequestProperty("accept", "*/*");
		uc.setRequestProperty("connection", "Keep-Alive");
		
		uc.setDoOutput(true);
		uc.setDoInput(true);
		String result = "";
		try(PrintWriter out = new PrintWriter(uc.getOutputStream());
		        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()))){
		  
//		    uc.setDefaultRequestProperty(key, value);
		    out.println(param);
		    out.flush();
		    String line;
		    while((line = in.readLine()) != null){
		        result += line;
		    }
		    
		}
		return result;
	}
	
	/** 
	 * 成功
	 * HTTP GET 请求
	 * @return
	 * @throws IOException 
	 */
	public String doHttpGet(String _url) throws IOException{
	    
	    URL url = new URL(_url);
	    String result = "";
	    URLConnection uc = url.openConnection();
	    try(BufferedReader in = new BufferedReader( new InputStreamReader(uc.getInputStream())) ){
	        
	        String line = "";
	        while((line = in.readLine()) != null){
	            result += line;
	        }
	    }
	    
	    return result;
	}
	
	
	/**
	 * 测试成功
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String httpClientPost(String url,JSONObject params) throws ClientProtocolException, IOException{
	    
	    HttpClientBuilder hb = HttpClientBuilder.create();
	    CloseableHttpClient hc = hb.build();
	    
	    HttpPost hp = new HttpPost(url);
	    HttpEntity entity = new StringEntity(params.toString(),"utf-8");
	    hp.setEntity(entity);
	    
	    CloseableHttpResponse response = hc.execute(hp);
	    HttpEntity he = response.getEntity();
	    return EntityUtils.toString(he);
	}
	
	
	@Test
	public void testDoPost() throws IOException{
	    String url = "http://10.139.113.29:10592/_search";
	    JSONObject p = new JSONObject();
	    p.put("title", "交易");
	    JSONObject p2 = new JSONObject();
	    p2.put("match", p);
	    JSONObject p3 = new JSONObject();
	    p3.put("query", p2);
	    
	    //请求体并不能放到get中
//	    System.out.println(doHttpGet(url + "?" + p3.toString()));
	    //失败
//	    System.out.println(doHttpPost(url,p3.toString()));
	    
	    JSONObject result = JSONObject.fromObject(httpClientPost(url,p3));
	   
	    System.out.println(result);
	    JSONArray datas = result.getJSONObject("hits").getJSONArray("hits");
	    
	    System.out.println(datas);
	}
	
	/**
	 * 测试Elasticsearch
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testElasticsearch() throws FileNotFoundException, IOException{
	    String logDatasPaht = "C:\\Users\\songjl\\Desktop\\config\\logdatas.txt";
	    String logDatasStr = "";
	    try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(logDatasPaht)))){
	        while(br.ready()){
	            logDatasStr += br.readLine();
	        }
	    }
	    List<String> logDatas = Elasticsearch.parseElasticaserchLogNoJson(logDatasStr);
//	    List<String> logDatas = Elasticsearch.parseElasticaserchLog(logDatasStr);
	    
	    Elasticsearch.parseLogStr(logDatas);
	   
	}
	
	/**
	 * Java8 可以直接使用Instant
	 * @throws ParseException 
	 * @Dec 测试ISO8601日期格式
	 * 2017年3月27日 下午5:12:44 songjl
	 */
	@Test
	public void testISO8601Format() throws ParseException{
	    
	    SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    
        Date d = iso8601Format.parse("2017-03-07T09:55:53.789Z");
        
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+00:00"));
        System.out.println(sdf.format(d));
        System.out.println(TimeZone.getDefault());
        System.out.println(iso8601Format.format(cal.getTime()));
        cal.add(Calendar.MINUTE, -2);   //往后倒2分钟
        System.out.println(sdf.format(cal.getTime()));
	    
	    
	    /* java8 实现
	    Instant it = Instant.now();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
	    System.out.println(it.toString());
	    System.out.println(it.parse("2017-03-07T09:55:53.789Z").toString());
	     */
	}
	
	/**
	 * @Dec测试循环列表
	 * 2017年4月7日 下午1:18:29 songjl
	 */
	@Test
	public void testCycleList(){
	    CycleArrayList<Integer> cal =  new CycleArrayList<Integer>(3);
	    int i = 0;
	    while( i < 3 ){
	        cal.add(i++);
	    }
	    i = 0;
	    while( i < 3)
	    {
	        int index = i++;
	        System.out.print(index + "-" +cal.get(index) + ",");
	    }
	    cal.add(50);
	    System.out.println("");
	    i = 0;
        while( i < 3)
        {
            int index = i++;
            System.out.print(index + "-" +cal.get(index) + ",");
        }
	}
	
	/**
	 * 失败
	 * @Dec
	 * 2017年4月11日 上午10:25:41 songjl
	 */
	@Test
	public void testJSONArray(){
	    String data = "[thuserid=20140101000000007400000804,businessCode=022,applicationAmount=1000.00,applicationVol=<null>,source=001,operDate=20170308,operTime=081359]";
	    
	    JSONArray dataJSON = JSONArray.fromObject(data);
	    System.out.println(dataJSON.toString());
	    
	}
}
