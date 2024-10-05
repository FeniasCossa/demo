package mz.sga.ujc.demo.service.auth;


import java.io.BufferedReader;
import java.io.DataOutputStream;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmsSender {

    private static Logger logger = Logger.getLogger(SmsSender.class.getName());

    private static final String HTTP_METHOD = "POST";
    private static final String HTTP_PING_METHOD = "GET";
    private static final String SERVICE = "pensa";
    private static final int MILLSECOND = 5000;
    private static final String USSD_GW_PING_URL = "http://172.16.100.2:8080/sms/ping";
    private static final String SMS_GW_URL = "http://172.16.100.2:8080/sms/send";
    private URL url, ping_url;

    public SmsSender() {
        // smsDao = new SMSDAO();
        // TODO Auto-generated constructor stub
        try {
            url = new URL(SMS_GW_URL);
            ping_url = new URL(USSD_GW_PING_URL);
        } catch (MalformedURLException e) {
            logger.severe("Improper SMS gateway URL: " + SMS_GW_URL);
            e.printStackTrace();
        }
    }

    public String send(long to, String text) {
        StringBuffer responseStr = new StringBuffer();
        try {
            logger.fine("prepare connection");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //conn.setRequestProperty("Accept", MIME_TYPE);
            conn.setRequestMethod(HTTP_METHOD);
            conn.setDoOutput(true);
            conn.setConnectTimeout(MILLSECOND);
            // { "service" : "pensa" , "destination" : "258820004444" , "text" : "test sms" }
            logger.fine("prepare JSON SMS body");
            String JsonSMS = "{ \"service\" : \"" + SERVICE + "\" , "
                    + "\"destination\" : \"" + to + "\" , "
                    + "\"text\" : \"" + text + "\" } ";

            logger.fine("send POST data");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(JsonSMS);
            out.flush();
            out.close();

            logger.fine("read HTTP response");
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            logger.log(Level.INFO, "sending SMS to {0} via " + SMS_GW_URL, to);
            responseStr.append("" + responseCode + ": ");
            while ((inputLine = in.readLine()) != null) {
                responseStr.append(inputLine);
            }
            in.close();

        } catch (MalformedURLException mue) {
            logger.log(Level.SEVERE, "Error sending SMS due to malformed URL: {0}", url.toString());
            mue.printStackTrace();
            return "Error sending SMS due to malformed URL: " + url.toString();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error sending SMS due to connection failure for {0} Millsecunds via " + SMS_GW_URL, MILLSECOND);
            e.printStackTrace();
            return "Error sending SMS due to connection failure for" + MILLSECOND + " Millsecunds via " + SMS_GW_URL;
        }
        return responseStr.toString();
    }

    public int pingUssdSender() {
        logger.fine("prepare connection to ping");
        int responseCode = 0;
        try {
            HttpURLConnection conn = (HttpURLConnection) ping_url.openConnection();
            conn.setRequestMethod(HTTP_PING_METHOD);
            conn.setDoOutput(true);

            responseCode = conn.getResponseCode();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseCode;
    }
}