package cn.li.util;


import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * 发送http请求
 *
 */
public class HttpUtils {
    private static final String TARGETURL = "https://we.cqu.pt/api/others/student.php?key=STUDENTNUM";
    //private static final String TARGETURL = "http://ip.taobao.com/service/getIpInfo.php?ip=STUDENTNUM";
    public static String sendGet(String studentNum) throws Exception {
        URL url = new URL(TARGETURL.replace("STUDENTNUM", studentNum));
        URLConnection urlConnection = url.openConnection();
        // 此处的urlConnection对象实际上是根据URL的
        // 请求协议(此处是http)生成的URLConnection类
        // 的子类  HttpsURLConnection,故此处最好将其转化
        // 为HttpsURLConnection类型的对象,以便用到
        // HttpsURLConnection更多的API.如下:
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;
        //设置httpsURLConnection参数
        httpsURLConnection.setRequestMethod("GET"); //默认也是GET
        //httpsURLConnection.setRequestProperty("Content-Type", "application/json");
        //httpsURLConnection.setRequestProperty("contentType", "utf8");
        //httpsURLConnection.setRequestProperty("Accept-Charset", "utf8");
        // 设置是否从httpUrlConnection读入，默认情况下是true;
        //httpsURLConnection.setDoInput(true);
        // 连接，从上述url.openConnection()至此的配置必须要在connect之前完成，
        //httpsUrlConnection.connect();
        // 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，
        // 所以在开发中不调用上述的connect()也可以)。
        //OutputStream outStrm = httpsURLConnection.getOutputStream();
        // 现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。
        //ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm);
        // 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,
        // 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器
        //objOutputStrm.close();

        // 调用HttpURLConnection连接对象的getInputStream()函数,
        // 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
        InputStream inStrm = httpsURLConnection.getInputStream(); // <===注意，实际发送请求的代码段就在这里
        //读取输入流里的内容
        StringBuilder result = new StringBuilder("");
        InputStreamReader inputStreamReader = new InputStreamReader(inStrm, "UTF-8");
        BufferedReader br = new BufferedReader(inputStreamReader);
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            result.append(inputLine);
        }
        return result.toString();
    }




    /*public static String sendGet(String studentNum){
        String url = TARGETURL.replace("STUDENTNUM",studentNum);
        //获取httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //生成一个get请求
        HttpGet httpGet =new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            //执行get请求并返回结果
            response = httpClient.execute(httpGet);
            System.out.println(response);
        }catch (IOException e1){

            e1.printStackTrace();
        }
        if (response == null) return null;
        String result = null;
        try {
            //处理结果为字符串
            HttpEntity entity = response.getEntity();
            if(entity!=null){
                result = EntityUtils.toString(entity);
            }
        }catch (Exception e){

            e.printStackTrace();
        }finally {
            try {
                response.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return result;
    }*/

}
