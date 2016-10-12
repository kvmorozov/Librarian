package ru.kmorozov.librarian.data.heroku;

/**
 * Created by sbt-morozov-kv on 12.10.2016.
 */
public class HerokuServer {

    public static void main(String args[]) {
        PostgreZooServer server = new PostgreZooServer();
        while (true) {
            server.run();
        }
    }
}
