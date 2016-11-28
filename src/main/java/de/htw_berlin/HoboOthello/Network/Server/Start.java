package de.htw_berlin.HoboOthello.Network.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by fubu on 27.11.16.
 * tutorial by http://cs.lmu.edu/~ray/notes/javanetexamples/
 */
public class Start {
    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out =
                            new PrintWriter(socket.getOutputStream(), true);
                    out.println("BLACK TURN");
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
