package ru.kmorozov.librarian.test.data;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by sbt-morozov-kv on 12.10.2016.
 */
public class HerokuTest {

    private static final String HEROKU_POSTGRE_CONN_STR = "jdbc:postgresql://ec2-184-73-254-144.compute-1.amazonaws.com:5432/" +
            "d2kffvhm8s2cb?user=wekyotlrjbuiyq&password=qZbXBGUvTgUgAOUEBRxTnqmTfk";

    @Test
    public void herokuPostgreConnectTest() {
        try {
            Connection connection = DriverManager.getConnection(HEROKU_POSTGRE_CONN_STR);

            Assert.assertNotNull(connection);
        } catch (SQLException e) {
            e.printStackTrace();

            Assert.assertTrue(false);
        }
    }
}
