package ge.edu.sangu.logger.appender;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseAppender extends Appender {

    private final Connection connection;
    private final String tableName;
    private final String columnName;

    public DatabaseAppender(String tableName, String columnName, String url, String username, String password) {
        this.tableName = tableName;
        this.columnName = columnName;
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OutputStream getOutputStream() {
        return new OutputStream() {
            private StringBuilder sb = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                char chr = (char) b;
                if (chr == '\n') {
                    int idx = sb.lastIndexOf("\r");
                    if (idx > 0) {
                        sb.deleteCharAt(idx);
                    }
                    try {
                        saveInDbColumn(sb.toString());
                    } catch (SQLException e) {
                        throw new IOException(e);
                    }
                    sb = new StringBuilder();
                }
                sb.append(chr);
            }

            private int saveInDbColumn(String message) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO " + tableName + "(" + columnName + ") VALUES(?);");
                preparedStatement.setString(1, message);
                return preparedStatement.executeUpdate();
            }
        };
    }
}
