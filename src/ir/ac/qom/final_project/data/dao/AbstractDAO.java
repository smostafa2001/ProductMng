package ir.ac.qom.final_project.data.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<E> {
    protected Connection connection;

    public AbstractDAO(){
        connection = ConnectionUtility.getConnection();
    }
    public abstract E save(E e) throws SQLException;
    public abstract E update(E e) throws SQLException;
    public abstract E delete(E e) throws SQLException;
    public abstract int count() throws SQLException;
    public abstract List<E> findAll() throws SQLException;

}

