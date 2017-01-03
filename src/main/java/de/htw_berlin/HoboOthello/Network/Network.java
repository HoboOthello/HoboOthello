package de.htw_berlin.HoboOthello.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.htw_berlin.HoboOthello.Core.*;

import java.io.IOException;

/**
 * Created by laura on 24.11.16.
 */
public class Network extends Player {

    private NetworkType networkType;
    private String serverIp;

    public Network(Color color, String serverIp) {
        super(color);
        networkType = NetworkType.CLIENT;
        this.serverIp = serverIp;
        setPlayerType(PlayerType.NETWORK_CLIENT);
    }

    public Network(Color color) {
        super(color);
        networkType = NetworkType.SERVER;
        setPlayerType(PlayerType.NETWORK_SERVER);
    }

    public Field setMove(Board board) {
        String response = null;

        Gson gson = new GsonBuilder().create();
        String boardJson = gson.toJson(board);

        if (this.networkType == NetworkType.SERVER) {
            Server server = new Server();
            try {
                response = server.startServer(boardJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (this.networkType == NetworkType.CLIENT) {
            Client client = new Client();

            try {
                response = client.startClient(boardJson, this.serverIp);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Board boardNetwork = gson.fromJson(response, Board.class);

        return getTurn(board, boardNetwork);
    }

    /**
     * Compared 2 boards and get the first field in boardNetwork which is not empty instead of boardOriginal
     *
     * @param boardOriginal
     * @param boardNetwork
     * @return turn Field
     */
    private Field getTurn(Board boardOriginal, Board boardNetwork) {
        // todo return error if the size differ
        for (int x = 0; x < boardOriginal.getBoardSize(); x++) {
            for (int y = 0; y < boardOriginal.getBoardSize(); y++) {
                if (boardOriginal.getFields()[x][y].isEmpty() != boardNetwork.getFields()[x][y].isEmpty()) {
                    return boardNetwork.getFields()[x][y];
                }
            }
        }

        return null;
    }
}
