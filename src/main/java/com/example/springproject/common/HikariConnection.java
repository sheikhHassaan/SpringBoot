package com.example.springproject.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Scope("singleton")
public class HikariConnection {

    @Autowired
    HikariDataSource hikariDataSource;

    public void initializeDataSource(){

        synchronized (HikariConnection.class){
            if(hikariDataSource == null) {
                HikariConfig config = new HikariConfig();
                config.setJdbcUrl("jdbc:mysql://localhost:3306/Department");        // todo: yet to check, if the URL is correct?
                config.setUsername("Hassaan");
                config.setPassword("Helloworld");
                config.setMaximumPoolSize(25);
                config.setMinimumIdle(5);

                hikariDataSource = new HikariDataSource(config);
            }
        }
    }
    public Connection getConnection() throws ClassNotFoundException, SQLException, ConnectionNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        initializeDataSource();
        Connection connection = hikariDataSource.getConnection();
        if (connection != null)
            return connection;
        throw new ConnectionNotFoundException();
    }
}
