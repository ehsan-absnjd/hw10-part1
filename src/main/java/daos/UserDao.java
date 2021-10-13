package daos;

import configs.DbConfig;
import entities.User;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao extends DaoAbstract<User>{

    @Override
    public int save(User entity) {
        return 0;
    }

}
