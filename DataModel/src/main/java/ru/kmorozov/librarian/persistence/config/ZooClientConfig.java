package ru.kmorozov.librarian.persistence.config;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

import static ru.kmorozov.librarian.data.heroku.PostgreZooServer.ZOO_PORT;

/**
 * Created by sbt-morozov-kv on 13.10.2016.
 */

@Configuration
public class ZooClientConfig {

    @Bean
    public ZooKeeper zooClient() {
        CountDownLatch connSignal = new CountDownLatch(1);
        ZooKeeper zoo = null;
        try {
            zoo = new ZooKeeper("localhost" + ":" + ZOO_PORT, 5000, event -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    connSignal.countDown();
                }
            });

            connSignal.await();
        } catch (Exception e) {
            return null;
        }

        return zoo;
    }
}
