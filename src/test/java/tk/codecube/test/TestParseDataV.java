package tk.codecube.test;

import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

public class TestParseDataV {
    
    private static final String baseUrl = "http://121.40.170.237:8080/ThEdasTemplate-0.0.2/simpleAction.action";
    
    
    /**
     * @Dec 总规模月走势
     * @throws IOException 
     * @throws URISyntaxException 
     * @throws ClientProtocolException 
     * songjl 2017年5月11日 下午1:46:38
     */
    @Test
    public void testFundVolMonthTrend() throws ClientProtocolException, URISyntaxException, IOException{
        String url = baseUrl + "?busiType=DataVAPIQryYebSumFundvolNewestMonth&channelId=0202";
        String request = TestUtils.doGet(url);
        String ret = replaseOldChar(request, "fundvol");

        JSONArray datas = JSONArray.fromObject(ret);
        List<Map<String,Object>> retVal = TestUtils.convertJSONArrayToList(datas,new BigDecimal("1000000000000"),"y");
//        List<Map<String,Object>> retVal = TestUtils.convertJSONArrayToList(datas);
        
        System.out.println(ret);
        System.out.println(JSONArray.fromObject(retVal));
    }
    
    /**
     * @Dec 月开户区域走势
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     * songjl 2017年5月11日 下午1:54:53
     */
    @Test
    public void testAccountMonthTrend() throws ClientProtocolException, URISyntaxException, IOException{
        String url = baseUrl + "?busiType=DataVAPIQryYebAcctTotalMonthCount&channelId=0202";
        String request = TestUtils.doGet(url);
        String ret = replaseOldChar(request, "account");
        System.out.println(ret);
    }
    
    /**
     * @Dec 月消费走势
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     * songjl 2017年5月11日 下午1:57:50
     */
    @Test
    public void testConsumneMonthTrend() throws ClientProtocolException, URISyntaxException, IOException{
        String url = baseUrl + "?busiType=DataVAPIQryYebConsumeDailyAmountSectionMonth&channelId=0202";
        String request = TestUtils.doGet(url);
        JSONArray ret = JSONArray.fromObject(replaseOldChar(request, "consume")) ;
        System.out.println(ret);
        List<Map<String,Object>> retVal = TestUtils.convertJSONArrayToList(ret,new BigDecimal("100000000"),"y");
        System.out.println(JSONArray.fromObject(retVal));
    }
    
    /**
     * @Dec 月转入转出金额走势
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     * songjl 2017年5月11日 下午1:59:31
     */
    @Test
    public void testPurchaseRedeenAmountMonthTrend() throws ClientProtocolException, URISyntaxException, IOException{
        String purl = baseUrl + "?busiType=DataVAPIQryYebPurchaseTotalDailyAmountSectionMonth&channelId=0202";
        String rurl = baseUrl + "?busiType=DataVAPIQryYebRedeemDailyAmountSectionMonth&channelId=0202";
        
        String prsult = TestUtils.doGet(purl);
        String rresult = TestUtils.doGet(rurl);
        
        String pret = replaseOldChar(prsult, "purchase");
        String rret = replaseOldChar(rresult, "redeem");
        
        JSONArray ret = JSONArray.fromObject(pret);
        ret.addAll(JSONArray.fromObject(rret));
        
        List<Map<String,Object>> retVal = TestUtils.convertJSONArrayToList(ret,new BigDecimal("100000000"),"y");
        System.out.println(JSONArray.fromObject(retVal));
    }
    
    /**
     * @Dec 月转入、转出笔数走势
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     * 2017年5月11日 下午2:14:13 songjl
     */
    @Test
    public void testPurchaseRedeenAccountMonthTrend() throws ClientProtocolException, URISyntaxException, IOException{
        String purl = baseUrl + "?busiType=DataVAPIQryYebPurchaseConfirmCountSectionMonth&channelId=0202";
        String rurl = baseUrl + "?busiType=DataVAPIQryYebRedeemConfirmCountSectionMonth&channelId=0202";
        
        String prsult = TestUtils.doGet(purl);
        String rresult = TestUtils.doGet(rurl);
        
        String pret = replaseOldChar(prsult, "purchase");
        String rret = replaseOldChar(rresult, "redeem");
        
        JSONArray ret = JSONArray.fromObject(rret);
        ret.addAll(JSONArray.fromObject(pret));
        
        List<Map<String,Object>> retVal = TestUtils.convertJSONArrayToList(ret,new BigDecimal("1000000"),"y");
        System.out.println(JSONArray.fromObject(retVal));
    }
   
    /**
     * @Dec 日开户走势
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     * 2017年5月11日 下午2:37:56 songjl
     */
    @Test
    public void testaccountDayTrend() throws ClientProtocolException, URISyntaxException, IOException{
        String url = baseUrl + "?busiType=DataVAPIQryYebAcctHourMultiIntervalCountMap&channelId=0202";
        
        String result = TestUtils.doGet(url);
        
        String ret = replaseOldChar(result, "account");
        
        System.out.println(ret);
    }
    
    /**
     * @Dec 日转入转出走势
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     * 2017年5月11日 下午2:40:56 songjl
     */
    @Test
    public void testPurchaseRedeenAmountDayTrend() throws ClientProtocolException, URISyntaxException, IOException{
        String purl = baseUrl + "?busiType=DataVAPIQryYebPHourMultiIntervalAmountMap&channelId=0202";
        String rurl = baseUrl + "?busiType=DataVAPIQryYebRHourMultiIntervalAmountMap&channelId=0202";
        
        String prsult = TestUtils.doGet(purl);
        String rresult = TestUtils.doGet(rurl);
        
        String pret = replaseOldChar(prsult, "purchase");
        String rret = replaseOldChar(rresult, "redeem");
        
        JSONArray ret = JSONArray.fromObject(pret);
        ret.addAll(JSONArray.fromObject(rret));
        System.out.println(ret.toString());
        List<Map<String,Object>> retVal = TestUtils.convertJSONArrayToList(ret,new BigDecimal("100000000"),"y");
        System.out.println(JSONArray.fromObject(retVal));
    }
    
    /**
     * @Dec 消费24小时走势
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     * 2017年5月11日 下午2:42:47 songjl
     */
    @Test
    public void testConsumneDayTrend() throws ClientProtocolException, URISyntaxException, IOException{
        String url = baseUrl + "?busiType=DataVAPIQryYebConsumeMinuteMultiIntervalAmountMap&channelId=0202";
        String request = TestUtils.doGet(url);
        JSONArray ret = JSONArray.fromObject(replaseOldChar(request, "consume"));
        System.out.println(ret);
        
        List<Map<String,Object>> retVal = TestUtils.convertJSONArrayToList(ret,new BigDecimal("1000000"),"y");
        System.out.println(JSONArray.fromObject(retVal));
    }
    
    
    /**
     * @Dec 呼吸泡
     * @throws IOException 
     * @throws URISyntaxException 
     * @throws ClientProtocolException 
     * 2017年5月11日 下午2:47:56 songjl
     */
    @Test
    public void testHxp() throws ClientProtocolException, URISyntaxException, IOException{
        
        String url =  baseUrl + "?busiType=DataVAPIQryYebAcctRegionTotalDailyCountDispNationalMap&channelId=0202";
        String request = TestUtils.doGet(url);
//        String ret = replaseOldChar(request, "account");
        Map<String,List<City>> cityLocation = readLocation();
        JSONArray datas = JSONArray.fromObject(request);
        JSONArray ret = new JSONArray();
        for(Object o : datas){
            JSONObject item = new JSONObject();
            JSONObject data = JSONObject.fromObject(o);
            City c = cityLocation.get(data.getString("adcode")).get(0);
            String cityCode = c.getCode();
            item.put("lat", c.getLat());
            item.put("lng", c.getLng());
            item.put("value", data.getInt("value"));
            //天津
            if("120000".equals(cityCode)){
                item.put("type", "2");
            }else{
                item.put("type", "1");
            }
                
            ret.add(item);
        }
        
        System.out.println(ret);
        
    }
    
    
    /**
     * @Dec 飞线
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     * 2017年5月11日 下午7:06:00 songjl
     * @throws IntrospectionException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    @Test
    public void testFlyLine() throws ClientProtocolException, URISyntaxException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException{
        int total = 100;//随机生成飞线的数量
        String url =  baseUrl + "?busiType=DataVAPIQryYebPurchaseAppDispFlyingline&channelId=0202";
        String request = TestUtils.doGet(url);
        JSONArray ret = JSONArray.fromObject(request);
        JSONObject to =  JSONObject.fromObject("{\"lng\": 117.210815,\"lat\": 39.14393}");
        Map<String,String> locationCalculate = getCalculateLocation(total);
        Map<String,List<City>> cityLocation = readLocation();
        
        List<City> destLocation = geDestLocation(cityLocation,locationCalculate);
        
        for(City c : destLocation){
//            JSONObject from =  TestUtils.objectToJson(c);
            JSONObject from =  TestUtils.objectToJsonUseFields(c);
            JSONObject item = new JSONObject();
            item.put("from", from);
            item.put("to", to);
            ret.add(item);
        }
        System.out.println(ret);
                
    }
    
    

    /**
     * @Dec 从数组中随机取值
     * @param cityLocation
     * @param locationCalculate
     * @return
     * 2017年5月13日 下午5:33:24 songjl
     */
    private List<City> geDestLocation(Map<String, List<City>> cityLocation,
            Map<String, String> locationCalculate) {
        List<City> ret = new ArrayList<City>();
        for(Map.Entry<String, List<City>> entry : cityLocation.entrySet()){
            String cityCode = entry.getKey();
            //这里不能是Int,在get到null时会报空指针
            String counterStr = locationCalculate.get(cityCode);
            if(counterStr == null) continue;
            int counter = Integer.parseInt(counterStr);
            int size = entry.getValue().size() - 1;
            for(int i = 0; i < counter ; ++i){
                
                int randomKey =  RandomUtils.nextInt(size);
                ret.add(entry.getValue().get(randomKey));
            }
        }
        return ret;
    }

    /**
     * @Dec 替换格式
     * @param str
     * @param s
     * @return
     * 2017年5月11日 下午2:49:55 songjl
     */
    public String replaseOldChar(String str,String s){
        return "[" + str.replace("[", "").replace("]", "").replace("\"}", "\",\"s\":\""+s+"\"}") + "]";
    }
    
    /**
     * @Dec 计算开户数据各省份占比
     * @throws IOException 
     * @throws URISyntaxException 
     * @throws ClientProtocolException 
     * 2017年5月11日 下午2:56:47 songjl
     */
    public Map<String,String> getCalculateLocation(int total) throws ClientProtocolException, URISyntaxException, IOException{
        BigDecimal bdTotal = new BigDecimal(total);
        String url = baseUrl + "?busiType=DataVAPIQryYebAcctRegionTotalDailyCountDispNationalMap&channelId=0202";
        String request = TestUtils.doGet(url);
        BigDecimal locationCounter = new BigDecimal(0);
        Map<String,BigDecimal> location = new HashMap<String,BigDecimal>(); 
        Map<String,String> lbd = new HashMap<String,String>();
        
        Pattern p = Pattern.compile("\"(\\w+)\":\"(\\d+)\",\"(\\w+)\":(\\d+)");
        Matcher m = p.matcher(request);
        while(m.find()){
            BigDecimal bd = new BigDecimal(m.group(4));
            locationCounter = locationCounter.add(bd);
            location.put(m.group(2), bd);
        }
        for(Map.Entry<String, BigDecimal> entry : location.entrySet()){
            BigDecimal temp = entry.getValue().divide(locationCounter,10,BigDecimal.ROUND_HALF_UP);
            BigDecimal v = temp.multiply(bdTotal).setScale(0, BigDecimal.ROUND_HALF_UP);
            lbd.put(entry.getKey(), v.longValueExact() + "");
        }
        
        System.out.println(location.toString());
        System.out.println(locationCounter);
        System.out.println(lbd.toString());
        
        return lbd;
    }
    
    /**
     * @Dec 读取文件中的 代码|城市名称|经度|维度
     * @throws FileNotFoundException
     * @throws IOException
     * 2017年5月11日 下午5:39:42 songjl
     */
    public  Map<String,List<City>> readLocation() throws FileNotFoundException, IOException{
        
        String locationPath = "src/test/resources/data/locationCity.txt";
        Map<String,List<City>> allCityMap = new HashMap<String,List<City>>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(locationPath)))){
            while(br.ready()){
                String line = br.readLine();
                if(line.startsWith("#")){
                    continue;
                }
                String[] datas = line.split("\\|");
                if(datas.length != 4) continue;
                City c = new City();
                c.setCode(datas[0]);
                c.setName(datas[1]);
                c.setLng(datas[2]);
                c.setLat(datas[3]);
                
                List<City> item = allCityMap.get(c.getCode());
                if(item == null){
                    item  = new ArrayList<City>();
                }
                item.add(c);
                allCityMap.put(c.getCode(), item);
            }
        }
        System.out.println(allCityMap.toString());
        return  allCityMap;
    }
    
    /**
     * @throws ParseException 
     * @Dec 测试离线申购笔数
     * 2017年5月13日 上午9:37:51 songjl
     */
    @Test
    public void testPurchaseOfflineCount() throws ParseException{
        String datas = "[{\"date\":\"20170413\",\"successCount\":\"15942815\"},{\"date\":\"20170414\",\"successCount\":\"15966367\"},{\"date\":\"20170417\",\"successCount\":\"47409163\"},{\"date\":\"20170418\",\"successCount\":\"17522687\"},{\"date\":\"20170419\",\"successCount\":\"16913051\"},{\"date\":\"20170420\",\"successCount\":\"16317705\"},{\"date\":\"20170421\",\"successCount\":\"17192881\"},{\"date\":\"20170424\",\"successCount\":\"45099577\"},{\"date\":\"20170425\",\"successCount\":\"17096297\"},{\"date\":\"20170426\",\"successCount\":\"17215120\"},{\"date\":\"20170427\",\"successCount\":\"16718039\"},{\"date\":\"20170428\",\"successCount\":\"17589160\"},{\"date\":\"20170502\",\"successCount\":\"63137058\"},{\"date\":\"20170503\",\"successCount\":\"17883220\"},{\"date\":\"20170504\",\"successCount\":\"16831340\"},{\"date\":\"20170505\",\"successCount\":\"16887678\"},{\"date\":\"20170508\",\"successCount\":\"46743344\"},{\"date\":\"20170509\",\"successCount\":\"18123745\"},{\"date\":\"20170510\",\"successCount\":\"18476895\"},{\"date\":\"20170511\",\"successCount\":\"19054573\"}]";
        JSONArray ad = JSONArray.fromObject(datas);
        List<Map<String,Object>> purchaseCount = TestUtils.convertJSONArrayToList(ad);
        String retVal = TestUtils.buildPurchaseOffLineCount(purchaseCount);
        System.out.println(retVal);
        
    }
    
    @Test
    public void testGetNull(){
    	Map<String,String> datas = new HashMap<String,String>();
    	datas.put("a", "123");
    	datas.put("b", "456");
    	String s = null;
    	System.out.println(datas.get(s));
    }
    
    @Test
    public void testDate(){
        Calendar start = Calendar.getInstance();
        System.err.println(convertToISO8601(start.getTime()));
    }
    
    public String convertToISO8601(Date date) {
    	SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    	iso8601Format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return iso8601Format.format(date);
    }
    @Test
    public void timeFormatTest(){
    	//145717
    	Calendar time = Calendar.getInstance();
    	time.setTimeInMillis(145717);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	System.out.println(sdf.format(time.getTime()));
    }
    
    @Test
    public void testJSONParse() throws UnsupportedEncodingException, FileNotFoundException, IOException{
    	String dataPath = "src/test/resources/data/YebAccount.data";
    	Pattern jsonValPattern = Pattern.compile("\"(\\w+)\":\"(\\S+)\"");
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(dataPath)),"UTF-8"))){
    		Map<String, String> commonKv = new HashMap<String, String>();
    		Map<String, String> tempKvs = new HashMap<String, String>();
    		while(br.ready()){
    			String line = br.readLine();
    			line = line.replace(",", ", ");
    			Matcher jsonVal = jsonValPattern.matcher(line);
            	while(jsonVal.find()){
            		tempKvs.put(jsonVal.group(1), jsonVal.group(2));
            	}
            	zxFillupCommonKv(commonKv, tempKvs);
            	
            	System.out.println(commonKv);
    		}
    		
    	}
    	
    	
    }
    
    private void zxFillupCommonKv(Map<String, String> commonKv, Map<String, String> tempKvs) {
        commonKv.put("IGNORE","false");
        // 直销固定销售商代码242
        commonKv.put("distributorcode", "242");
        commonKv.put("alino", tempKvs.get("contractNo"));
        commonKv.put("contractno", tempKvs.get("contractNo"));
        commonKv.put("userid", tempKvs.get("userId"));
        commonKv.put("subcardno", tempKvs.get("subCardNo"));
        
        String sysid = StringUtils.substring(commonKv.get("alino"), -3);
        commonKv.put("nodeid", sysid);
        
        commonKv.put("sysid", "2");// g_sysid对应sysid
        commonKv.put("tano", "H0");
        commonKv.put("certid", tempKvs.get("certId"));
        commonKv.put("idvaliddate", tempKvs.get("idValidDate"));
        commonKv.put("hostname", tempKvs.get("hostname"));
        commonKv.put("postcode", tempKvs.get("postCode"));
        commonKv.put("investorname", tempKvs.get("investorName"));
        commonKv.put("invtp", tempKvs.get("isindividual"));// isindividual对应invtp
                                                           // 0-机构,1-个人
        commonKv.put("nationality", tempKvs.get("nationality"));
        commonKv.put("certificatetype", tempKvs.get("certType"));// certtype对应certificatetype
        // 如果证件类型不是身份证,则记录忽略
        String certificatetype = commonKv.get("certificatetype");
        if (!"0".equalsIgnoreCase(certificatetype)) {
            commonKv.put("IGNORE","true");
            return;
        }
        commonKv.put("certificateno", tempKvs.get("certificateNo"));
        // 如果性别为空,由身份证取性别
        String gender = tempKvs.get("gender");
        if (StringUtils.isNotEmpty(gender)) {
            commonKv.put("sex", Integer.parseInt(gender) % 2 + "");// gender(1-男,2-女)对应sex(1-男,0-女)
        } else {
            String certificateno = commonKv.get("certificateno");
            String sex = Integer.parseInt(certificateno.substring(16, 17)) % 2 + "";
            commonKv.put("sex", sex);
        }
        commonKv.put("investorsbirthday", tempKvs.get("investorBirthday"));
        commonKv.put("vocationcode", tempKvs.get("vocationCode"));
        commonKv.put("email", tempKvs.get("email"));
        commonKv.put("mobiletelno", tempKvs.get("mobilePhone"));// mobilephone对应mobiletelno
        commonKv.put("hometelno", tempKvs.get("homeTel"));
        commonKv.put("fax", tempKvs.get("fax"));
        commonKv.put("extension", tempKvs.get("extension"));
        commonKv.put("officetelno", tempKvs.get("officetel"));
        commonKv.put("address", tempKvs.get("address"));
        commonKv.put("investmentexperience", tempKvs.get("investmentExperience"));
        commonKv.put("paycenterid", "0202");
        commonKv.put("profitaccountno", tempKvs.get("profitAccountNo"));
        commonKv.put("operorg", "9999");
        commonKv.put("version", tempKvs.get("version"));
        commonKv.put("channelid", tempKvs.get("channelId"));
        commonKv.put("instid", tempKvs.get("instId"));
    }
    
}

class City{
    
    private String name;
    private String code;
    
    private String lng;
    private String lat;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getLng() {
        return lng;
    }
    
    public void setLng(String lng) {
        this.lng = lng;
    }
    
    public String getLat() {
        return lat;
    }
    
    public void setLat(String lat) {
        this.lat = lat;
    }
    
}

class LocationNode{
    
    private String adcode;
    private String value;
    
    public String getAdcode() {
        return adcode;
    }
    
    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    
}
