package de.htw_berlin.HoboOthello.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.htw_berlin.HoboOthello.Core.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by fubu on 27.11.16.
 * tutorial by http://cs.lmu.edu/~ray/notes/javanetexamples/
 */
public class Server {
    public String startServer(String boardJson) throws IOException {
        String response = null;

        ServerSocket listener = new ServerSocket(9090);

        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    BufferedReader input = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    PrintWriter output =
                            new PrintWriter(socket.getOutputStream(), true);
                    output.println(new Date().toString());

                    return input.readLine();

                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
    }

}





