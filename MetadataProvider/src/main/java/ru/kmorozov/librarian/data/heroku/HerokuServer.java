package ru.kmorozov.librarian.data.heroku;

import java.io.IOException;

/**
 * Created by sbt-morozov-kv on 12.10.2016.
 */
public class HerokuServer {

    public static void main(String args[]) {
        PostgreConnectorServer server = new PostgreConnectorServer();
        while (true) {
            try {
                server.run();
            } catch (IOException e) {
                System.exit(-1);
            }
        }
    }
}
