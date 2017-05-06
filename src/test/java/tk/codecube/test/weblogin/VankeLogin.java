package tk.codecube.test.weblogin;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @dec 模拟Web登陆
 * @author songjl 2017年4月25日 上午10:05:07
 *
 */
public class VankeLogin {
    
    private String userName = "15620608044";
    private String passWord = "aqp123456";
    private String validataCode = "";
    private String requestToken = "";
    private CloseableHttpClient httpClient;
    
    private static final String tokenRegx = "\"__RequestVerificationToken\".*value=\"(\\S+)\"";
    private static final String loginUrl = "http://fang.vanke.com/Login?ReturnUrl=%2F";
    private static final String moveToUrl = "http://fang.vanke.com";
    
    private static final String validataUrl = "http://fang.vanke.com/Login/GetValidateCode";
    private static final String selectUrl = "http://fang.vanke.com/ActivityTarget/Floor/30764?activityid=6769";
    
    private static final String wishListUrl = "http://fang.vanke.com/ActivityTarget/Auction?id=1250850&pageslide=true&random=1493106601999";
    
    
    public VankeLogin(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }
    
    public static void main(String[] args) throws Exception {
        HttpClientBuilder hcb = HttpClientBuilder.create();
        CloseableHttpClient chc = hcb.build();
        VankeLogin vl = new VankeLogin(chc);
//        vl.getValidateCode(validataUrl, "D:\\data\\vanke\\validata.jpg");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        
//        System.out.println("请输入用户名：");
//        vl.setUserName(br.readLine());
//        System.out.println("请输入密码：");
//        vl.setPassWord(br.readLine());

        //登陆之前获取token
        CloseableHttpResponse beforeLoginResponse = vl.doGet(loginUrl, new HashMap<String,String>());
        String homeHtml = EntityUtils.toString(beforeLoginResponse.getEntity());
        String token = vl.getToken(homeHtml);
        vl.setRequestToken(token);
        boolean validata = false;
        while(!validata){
            
            /*InputStream validataIs = vl.getValidateCodeStream(validataUrl);
            byte[] bytes = new byte[1024];
            while(validataIs.read(bytes) != -1){
                System.out.write(bytes);
            }*/
            
            String validataCodePath = "D:\\data\\vanke\\validata.jpg";
            vl.getValidataCode(validataUrl, validataCodePath);
            Runtime.getRuntime().exec("rundll32 c:\\Windows\\System32\\shimgvw.dll,ImageView_Fullscreen " + validataCodePath);
            System.out.println("请输入验证码(输入!刷新验证码)：");
            String input = br.readLine();
            if("!".equals(input) && !StringUtils.isBlank(input)){
                continue;
            }
            vl.setValidataCode(input);
            validata = true;
        }
        
        System.out.println("您输入的信息为：" + vl.getUserName() + " : " + vl.getPassWord());
        vl.login();
//        Thread.sleep(10 * 1000);
        //期望列表
//        CloseableHttpResponse whishListResponse = vl.doGet(wishListUrl, new HashMap<String,String>());
//        System.out.println(EntityUtils.toString(whishListResponse.getEntity()));
        
    }
    
    /**
     * @throws IOException 
     * @throws ClientProtocolException 
     * @Dec 模拟网页登陆
     * 2017年4月25日 上午10:05:09 songjl
     */
    public void login() throws Exception{
        
        Map<String,String> params = new HashMap<String,String>();
        params.put("Telphone", this.getUserName());
        params.put("Encrypted_pwd", encrypt(this.getPassWord()));
        params.put("VerificationCode", this.getValidataCode());
        params.put("__RequestVerificationToken", this.getRequestToken());
        
        //开始登陆
        CloseableHttpResponse loginResponse = doPost(loginUrl, params);
        CloseableHttpResponse moveToResponse = doGet(moveToUrl, new HashMap<String,String>());
        
        System.out.println(EntityUtils.toString(loginResponse.getEntity()));
    }
    
    /**
     * @Dec get请求
     * @param _url
     * @param _params
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * 2017年4月25日 上午10:30:08 songjl
     */
    public CloseableHttpResponse doGet(String _url,Map<String,String> _params) throws ClientProtocolException, IOException{
        String paramString = "";
        for(Map.Entry<String,String> entry : _params.entrySet()){
            paramString += entry.getKey() + "&";
        }
        paramString += "timestep=" + Calendar.getInstance().getTimeInMillis();
        HttpGet hg = new HttpGet(_url + "?" + paramString);
        return getHttpClient().execute(hg);
    }
    
    /**
     * @Dec POST请求
     * @param _url
     * @param _params
     * @return
     * 2017年4月25日 上午10:52:50 songjl
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public CloseableHttpResponse doPost(String _url,Map<String,String> _params) throws ClientProtocolException, IOException{
        
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        for(Map.Entry<String, String> entry : _params.entrySet()){
            NameValuePair item = new BasicNameValuePair(entry.getKey(), entry.getValue());
            formParams.add(item);
        }
        
        HttpEntity he = new UrlEncodedFormEntity(formParams);
        HttpPost hp = new HttpPost(_url);
        hp.setEntity(he);
        return getHttpClient().execute(hp);
    }
    

    /**
     * @Dec 从首页中获取token
     * @param homeHtml
     * @return
     * 2017年4月25日 上午10:28:42 songjl
     */
    private String getToken(String homeHtml) {
        Pattern tokenPattern = Pattern.compile(tokenRegx);
        Matcher tokenMatcher = tokenPattern.matcher(homeHtml);
        if(tokenMatcher.find()){
            return tokenMatcher.group(1);
        }
        return null;
    }

    /**
     * @Dec 获取验证码
     * @param _url
     * @param savePath
     * 2017年4月25日 上午11:11:53 songjl
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    private void getValidataCode(String _url,String savePath) throws ClientProtocolException, IOException{
        HttpEntity validateCode = doGet(_url, new HashMap<String,String>()).getEntity();
        InputStream codeData = validateCode.getContent();
        byte[] buffer = new byte[1024];
        OutputStream os = new FileOutputStream(savePath);
        int len = 1024;
        while(codeData.read(buffer,0,len) != -1){
            os.write(buffer);
        }
        os.flush();
        os.close();
        codeData.close();
    }
    
    /**
     * @Dec 返回get请求流
     * @param _url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * 2017年4月25日 下午1:50:13 songjl
     */
    private InputStream getValidataCodeStream(String _url) throws ClientProtocolException, IOException{
        HttpEntity validateCode = doGet(_url, new HashMap<String,String>()).getEntity();
        return validateCode.getContent();
    }

    /**
     * @Dec MD5加密
     * @param passWord
     * @return
     * 2017年4月25日 下午1:04:12 songjl
     * @throws NoSuchAlgorithmException 
     * @throws UnsupportedEncodingException 
     */
    public String encrypt(String passWord) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(passWord.getBytes("UTF-8"));
        return new BigInteger(1,md5.digest()).toString(16);
    }

    
    public String getUserName() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public String getPassWord() {
        return passWord;
    }

    
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    
    public String getValidataCode() {
        return validataCode;
    }

    
    public void setValidataCode(String validataCode) {
        this.validataCode = validataCode;
    }

    
    public String getRequestToken() {
        return requestToken;
    }

    
    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    
    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    
    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }


}
