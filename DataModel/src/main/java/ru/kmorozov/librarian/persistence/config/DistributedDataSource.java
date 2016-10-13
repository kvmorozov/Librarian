package ru.kmorozov.librarian.persistence.config;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.cglib.proxy.CallbackHelper;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by sbt-morozov-kv on 13.10.2016.
 */
public class DistributedDataSource extends AbstractDataSource {

    private DataSource dataSource;
    private ZooKeeper zooClient;

    public DistributedDataSource(DataSource dataSource, ZooKeeper zooClient) {
        this.dataSource = dataSource;
        this.zooClient = zooClient;
    }

    public Connection getConnection() throws SQLException {
        return getWrappedConnection(dataSource.getConnection());
    }

    public Connection getConnection(String s, String s1) throws SQLException {
        return getWrappedConnection(dataSource.getConnection(s, s1));
    }

    private Connection getWrappedConnection(Connection _connection) {
        Enhancer enhancer = new Enhancer();

        CallbackHelper callbackHelper = new CallbackHelper(_connection.getClass(), new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if (method.getName().equals("initMybatisConfiguration")) {
                    return null;
                } else {
                    return NoOp.INSTANCE;
                }
            }
        };

        enhancer.setSuperclass(_connection.getClass());
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());

        return (Connection) enhancer.create();
    }
}
