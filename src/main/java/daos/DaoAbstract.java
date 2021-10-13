package daos;

import configs.DbConfig;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.*;

public abstract class DaoAbstract <T> {
    private MariaDbDataSource dataSource = new MariaDbDataSource();
    protected Connection connection;

    public DaoAbstract(){
        try {
            dataSource.setUser(DbConfig.USERNAME);
            dataSource.setPassword(DbConfig.PASSWORD);
            dataSource.setUrl(DbConfig.URL);
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract int save ( T entity);

    protected PreparedStatement statementForVarArgs(String query, Object... params){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            for(int i=1; i<=params.length; i++){
                Object param = params[i - 1];
                Class paramClass = param.getClass();
                if (Integer.class.equals(paramClass)) {
                    preparedStatement.setInt(i , (int)param);
                } else if (Double.class.equals(paramClass)) {
                    preparedStatement.setDouble(i , (double)param);
                } else if (String.class.equals(paramClass)) {
                    preparedStatement.setString(i , (String)param);
                } else if (Boolean.class.equals(paramClass)) {
                    preparedStatement.setBoolean(i , (Boolean) param); ;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    protected PreparedStatement idReturnStatementForVarArgs(String query, Object... params){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
            for(int i=1; i<=params.length; i++){
                Object param = params[i - 1];
                Class paramClass = param.getClass();
                if (Integer.class.equals(paramClass)) {
                    preparedStatement.setInt(i , (int)param);
                } else if (Double.class.equals(paramClass)) {
                    preparedStatement.setDouble(i , (double)param);
                } else if (String.class.equals(paramClass)) {
                    preparedStatement.setString(i , (String)param);
                } else if (Boolean.class.equals(paramClass)) {
                    preparedStatement.setBoolean(i , (Boolean) param); ;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
