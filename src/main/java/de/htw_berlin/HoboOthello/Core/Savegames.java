package de.htw_berlin.HoboOthello.Core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.htw_berlin.HoboOthello.GUI.Gameview;

import java.io.*;

/**
 * Created by Steffen Exler on 11.12.16.
 */
public class Savegames {

    private File file = new File("savegame.json");

    public Game load() {
        Game game = null;

        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            }

            FileReader fr = new FileReader(this.file);
            BufferedReader br = new BufferedReader(fr);

            String row;
            String content = "";

            while ((row = br.readLine()) != null) {
                content = content + row;
            }
            br.close();

            Gson gson = new GsonBuilder().create();



            game = gson.fromJson(content, Game.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return game;
    }

    public void save(Game game) {
        Gson gson = new GsonBuilder().create();
        String content = gson.toJson(game);

        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}