package com.blasco991.Java.ChatCLI;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Chat {

    static final int reactivity = 10000;
    static final String USER_AGENT = "Mozilla/5.0";
    static final String list = "https://chat-application-blasco991.herokuapp.com/ListMessages";
    static final String add = "https://chat-application-blasco991.herokuapp.com/AddMessage";

    public static void main(String[] args) throws Exception {

        /*Chat http = new Chat();
        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();
        System.out.println("\nTesting 2 - Send Http POST request");
        http.sendPost();*/
        IncomingHandler incomingHandler = new IncomingHandler(System.out);
        incomingHandler.start();
    }

    // HTTP GET request
    private void sendGet() throws Exception {


        URL obj = new URL(list);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + con.getURL());
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }

    // HTTP POST request
    private void sendPost() throws Exception {

        URL obj = new URL(add);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + con.getURL());
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}