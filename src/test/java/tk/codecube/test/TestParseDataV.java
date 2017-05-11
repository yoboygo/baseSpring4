package tk.codecube.test;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

public class TestParseDataV {
    
    private static final String baseUrl = "http://121.40.170.237:8080/ThEdasTemplate-0.0.2/simpleAction.action";
    
    /**
     * @throws IOException 
     * @throws URISyntaxException 
     * @throws ClientProtocolException 
     * @Dec 总规模月走势
     * songjl 2017年5月11日 下午1:46:38
     */
    @Test
    public void testFundVolMonthTrend() throws ClientProtocolException, URISyntaxException, IOException{
        String url = baseUrl + "?busiType=DataVAPIQryYebRedeemDailyAmountSectionMonth&channelId=0202";
        String request = TestUtils.doGet(url);
        String ret = replaseOldChar(request, "fundvol");
        System.out.println(ret);
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
        String ret = replaseOldChar(request, "consume") ;
        System.out.println(ret);
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
        String rret = replaseOldChar(prsult, "redeem");
        
        System.out.println(ret);
    }
    
    public String replaseOldChar(String str,String s){
        return "[" + str.replace("[", "").replace("]", "").replace("\"}", "\",\"s\":\""+s+"\"}") + "]";
    }
}
