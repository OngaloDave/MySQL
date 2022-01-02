package pro.husk.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import pro.husk.Database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Supports the connection to most databases through Hikari connection pool.
 *
 * @author Huskehhh
 */
public class GenericDataSource extends Database {

    private final HikariDataSource dataSource;

    /**
     * Creates a new MySQL instance
     *
     * @param url      | URL of the database
     * @param username | Username
     * @param password | Password
     */
    public GenericDataSource(String url, String username, String password) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        dataSource = new HikariDataSource(config);
    }

    /**
     * Getter for the {@link HikariDataSource} object
     *
     * @return {@link HikariDataSource} object
     */
    public HikariDataSource getDataSource() {
        return dataSource;
    }

    /**
     * Gets current connection, or a new connection from {@link HikariDataSource}
     *
     * @return connection
     * @throws SQLException if cannot get a connection
     */
    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
        }
        return connection;
    }
}
