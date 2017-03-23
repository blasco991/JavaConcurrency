package com.blasco991.chatCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by blasco991 on 10/03/17.
 */
class IncomingHandler extends Thread {

    private PrintStream printStream;

    IncomingHandler(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void run() {
        try {
            URL obj = new URL(Chat.list);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", Chat.USER_AGENT);

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println(response.toString());
            }

            try {
                Thread.sleep(Chat.reactivity);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}