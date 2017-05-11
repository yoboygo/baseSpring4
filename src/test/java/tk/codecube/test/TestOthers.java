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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import com.mysql.fabric.xmlrpc.base.Array;
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
	@Test
	public void testSubstr(){
	    String timeStamp = "20170502 14:01:02";
	    System.out.println(StringUtils.substring(timeStamp, 6,8));
	}
	
	@Test
	public void testDaysCalc() throws ParseException{
	    String startDate = "20170428";
	    String endDate = "20170502";
	    List<String> ret = TestUtils.calculateIntervalDays(startDate, endDate);
	    for(String date : ret){
	        System.out.println(date);
	    }
	}
	
	@Test
	public void testParseWorkDay() throws ParseException{
	    String jsonData = "[{\"date\":\"20170403\",\"successCount\":\"32135959\"},{\"date\":\"20170404\",\"successCount\":\"33117704\"},{\"date\":\"20170405\",\"successCount\":\"38163247\"},{\"date\":\"20170406\",\"successCount\":\"36173222\"},{\"date\":\"20170407\",\"successCount\":\"35360696\"},{\"date\":\"20170408\",\"successCount\":\"34825173\"},{\"date\":\"20170409\",\"successCount\":\"38617955\"},{\"date\":\"20170410\",\"successCount\":\"40019772\"},{\"date\":\"20170411\",\"successCount\":\"35492723\"},{\"date\":\"20170412\",\"successCount\":\"35000275\"},{\"date\":\"20170413\",\"successCount\":\"35602742\"},{\"date\":\"20170414\",\"successCount\":\"35866579\"},{\"date\":\"20170415\",\"successCount\":\"35849194\"},{\"date\":\"20170416\",\"successCount\":\"36476050\"},{\"date\":\"20170417\",\"successCount\":\"39339569\"},{\"date\":\"20170418\",\"successCount\":\"38510119\"},{\"date\":\"20170419\",\"successCount\":\"37592598\"},{\"date\":\"20170420\",\"successCount\":\"37831565\"},{\"date\":\"20170421\",\"successCount\":\"36505391\"},{\"date\":\"20170422\",\"successCount\":\"36572436\"},{\"date\":\"20170423\",\"successCount\":\"37571243\"},{\"date\":\"20170424\",\"successCount\":\"40028973\"},{\"date\":\"20170425\",\"successCount\":\"37704311\"},{\"date\":\"20170426\",\"successCount\":\"37529163\"},{\"date\":\"20170427\",\"successCount\":\"38289008\"},{\"date\":\"20170428\",\"successCount\":\"39109666\"},{\"date\":\"20170429\",\"successCount\":\"35488918\"},{\"date\":\"20170430\",\"successCount\":\"35256194\"},{\"date\":\"20170501\",\"successCount\":\"40769870\"},{\"date\":\"20170502\",\"successCount\":\"43783970\"}]";
	    JSONArray datas = JSONArray.fromObject(jsonData);
	    List<Map<String,String>> allDatas = new ArrayList<Map<String,String>>();
	    for(Object obj : datas){
	        Map<String,String> item = new HashMap<String,String>();
	        JSONObject data = JSONObject.fromObject(obj);
	        item.put("date", data.getString("date"));
	        item.put("", data.getString("successCount"));
	        allDatas.add(item);
	    }
	    String ret = buildAppRetNewJsonStr(allDatas);
	    System.out.println(ret);
	}
	
	/**
     * 处理数据不分天数
     * @param purchaseAmount
     * @return
     * @throws ParseException
     */
    protected String buildAppRetNewJsonStr(List<Map<String,String>> purchaseAmount) throws ParseException {
        List<Map<String,Object>> ret = new ArrayList<Map<String,Object>>();
        Set<String> keyValidata = new HashSet<String>();
        
        String preDate = null;
        for(Map<String,String> data : purchaseAmount){
            Map<String,Object> item = new HashMap<String, Object>();
            
            //计算两个日期间的间隔天数，用0补全节假日的数据
            if(preDate != null){
               
                List<String> missDays = TestUtils.calculateIntervalDays(preDate,data.get("date"));
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
                    ret.add(e);
                }
            }
            preDate = data.get("date");
            String x = StringUtils.substring(data.get("date"),6,8);
            if(keyValidata.contains(x)){
                x = x + "'";
            }
            keyValidata.add(x);
            
            String[] val = {data.get("successCount")};
            item.put("x", x);
            item.put("y", val);
            ret.add(item);
        }
        
        return ret.toString();
    }
    
    
    public void testCalendarDayCalc(){
        String calendar = "{20171201=20171130, 20161128=20161125, 20161129=20161128, 20171207=20171206, 20161122=20161121, 20171208=207, 20161121=20161118, 20171205=20171204, 20161124=20161123, 20171206=20171205, 20161123=20161122, 20171204==20161124, 20171211=20171208, 20171212=20171211, 20171213=20171212, 20171218=20171215, 20171219=20171218, 220171214=20171213, 20171215=20171214, 20170207=20170206, 20170206=20170203, 20161109=20161108, 20170209=201170207, 20170203=20170126, 20161108=20161107, 20161107=20161104, 20161102=20161101, 20161101=20161031, 201661103=20161102, 20170330=20170329, 20170331=20170330, 20161118=20161117, 20161117=20161116, 20161014=null,  20161115=20161114, 20161114=20161111, 20161019=20161018, 20161018=20161017, 20161017=20161014, 20161111=200161109, 20170320=20170317, 20170322=20170321, 20170425=20170424, 20170321=20170320, 20170424=20170421, 201170426=20170425, 20170428=20170427, 20170328=20170327, 20170327=20170324, 20170809=20170808, 20170531=201700807, 20170329=20170328, 20170807=20170804, 20170324=20170323, 20170323=20170322, 20170421=20170420, 201708420=20170419, 20170802=20170801, 20170803=20170802, 20170801=20170731, 20170220=20170217, 20170125=201701240, 20170126=20170125, 20170222=20170221, 20170223=20170222, 20170224=20170223, 20170123=20170120, 20170227==20170123, 20170228=20170227, 20170120=20170119, 20170414=20170413, 20170310=20170309, 20170413=20170412, 220170411=20170410, 20170418=20170417, 20170417=20170414, 20170317=20170316, 20170316=20170315, 20170315=201170407, 20170314=20170313, 20170313=20170310, 20170118=20170117, 20170119=20170118, 20170116=20170113, 201770210=20170209, 20170215=20170214, 20170112=20170111, 20170216=20170215, 20170113=20170112, 20170213=201702109, 20170419=20170418, 20170214=20170213, 20170111=20170110, 20170217=20170216, 20170302=20170301, 201703003=20170302, 20170306=20170303, 20170308=20170307, 20170307=20170306, 20170103=20161230, 20170309=20170308,, 20170105=20170104, 20170106=20170105, 20170109=20170106, 20170508=20170505, 20170509=20170508, 20170704=220170630, 20171009=20170929, 20171101=20171031, 20171103=20171102, 20171102=20171101, 20170705=20170704, 200171107=20171106, 20170707=20170706, 20171106=20171103, 20170502=20170428, 20171109=20171108, 20170503=201771107, 20170504=20170503, 20170505=20170504, 20170901=20170831, 20170831=20170830, 20170830=20170829, 201710905=20170904, 20170906=20170905, 20170904=20170901, 20171114=20171113, 20171019=20171018, 20171113=201711117, 20171017=20171016, 20170907=20170906, 20171016=20171013, 20170908=20170907, 20171117=20171116, 201706306=20171115, 20171013=20171012, 20171115=20171114, 20171012=20171011, 20171011=20171010, 20171010=20171009,  20161216=20161215, 20170726=20170725, 20161215=20161214, 20170725=20170724, 20170720=20170719, 20161219=200170720, 20170911=20170908, 20170629=20170628, 20170628=20170627, 20170913=20170912, 20170821=20170818, 201170823=20170822, 20161214=20161213, 20170822=20170821, 20161213=20161212, 20170825=20170824, 20161212=201610823, 20171123=20171122, 20170918=20170915, 20170620=20170619, 20171122=20171121, 20170919=20170918, 201706828=20170825, 20170622=20170621, 20171124=20171123, 20170829=20170828, 20170623=20170622, 20170914=201709134, 20171121=20171120, 20170626=20170623, 20171120=20171117, 20170627=20170626, 20170525=20170524, 20170526==20171124, 20170727=20170726, 20170728=20170727, 20170522=20170519, 20171129=20171128, 20170523=20170522, 220170524=20170523, 20161205=20161202, 20170714=20170713, 20161207=20161206, 20170713=20170712, 20161206=201170711, 20161209=20161208, 20170711=20170710, 20161208=20161207, 20170710=20170707, 20170519=20170518, 201770810=20170809, 20170619=20170616, 20170922=20170921, 20170921=20170920, 20170920=20170919, 20170814=201708130, 20170811=20170810, 20161202=20161201, 20170817=20170816, 20170818=20170817, 20170612=20170609, 201709215=20170814, 20170816=20170815, 20170927=20170926, 20170615=20170614, 20170928=20170927, 20170616=20170615,, 20170925=20170922, 20170613=20170612, 20170926=20170925, 20170614=20170613, 20170516=20170515, 20170517=220170512, 20170406=20170405, 20170512=20170511, 20170718=20170717, 20170407=20170406, 20170719=20170718, 200170405=20170331, 20170511=20170510, 20170717=20170714, 20170606=20170605, 20170607=20170606, 20170608=201761229, 20170609=20170608, 20170602=20170601, 20170605=20170602, 20170601=20170531, 20161222=20161221, 201611220=20161219, 20161221=20161220, 20170731=20170728, 20161226=20161223, 20161227=20161226, 20161228=201612228, 20171226=20171225, 20161031=20161028, 20171225=20171222, 20171228=20171227, 20171227=20171226, 201712290=20171219, 20171222=20171221, 20171221=20171220, 20171020=20171019, 20171023=20171020, 20171024=20171023,  20171026=20171025, 20171027=20171026, 20161020=20161019, 20161021=20161020, 20161026=20161025, 20161027=200161021, 20161025=20161024, 20161028=20161027, 20171030=20171027, 20171031=20171030}";
        String calendarStr = "";
        Pattern cp = Pattern.compile("(\\d{8})=(\\d{8})");
        Matcher cm = cp.matcher(calendar);
        Map<String,String> allCalendar = new HashMap<String,String>();
        while(cm.matches()){
            allCalendar.put(cm.group(1), cm.group(2));
        }
        
    }
    /*
    **
    * @param natureDates
    */
   public static Map<String,String> workDateProcesser(List<Map<String,String>> workDates)
   {
       //Map<caleanDate,workDate> caleanDate-->清算日期--->workDate:清算对应的工作日
       Map<String,String> cleanDateMap = new HashMap<String, String>();
       int workDayCount = workDates.size();
       for(int i = workDayCount - 1 ; i >= 0 ; --i)
       {
           Map<String,String> cc = workDates.get(i);
           String preWorkDate = null;
           while(preWorkDate == null)
           {
               try{
                   preWorkDate = workDates.get(i+1).get("natureday");
               }catch(Exception e){
                   //越界或没有对应的工作日
                   break;
               }
           }
           cleanDateMap.put(cc.get("natureday"), preWorkDate);
       }
       
       return cleanDateMap;
   }

   @Test
   public void testCopyArray(){
       List<String> l = new ArrayList<String>();
       List<String> d = new ArrayList<String>();
       for(int i = 0 ; i<10; ++i){
           l.add(" " + i);
       }
       int start = l.size() - 5;
       for(int i = start; i<l.size() ; ++i){
           d.add(l.get(i));
       }
       System.out.println(d);
   }
   
   
}
