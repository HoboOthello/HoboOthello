package de.htw_berlin.HoboOthello.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

/**
 * Created by fubu on 12.12.16.
 */
public class Client {
    public String startClient(String boardJson, String serverIp) throws IOException, InterruptedException {
        while (true)

        {
            try {
                Socket s = new Socket(serverIp, 9090);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                PrintWriter out =
                        new PrintWriter(s.getOutputStream(), true);
                out.println(boardJson);

                String answer = in.readLine();
                return answer;
            } catch (ConnectException e) {

            }
            Thread.sleep( 1000 );
        }
    }
}
