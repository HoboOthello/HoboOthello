package de.htw_berlin.HoboOthello.Network.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by fubu on 27.11.16.
 */
public class Start {
    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        Socket s = new Socket(serverAddress, 9090);
        BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        System.out.println(answer);
        System.exit(0);
    }
}
