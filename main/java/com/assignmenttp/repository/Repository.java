package com.assignmenttp.repository;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Administrator on 2016/05/13.
 */
public interface Repository<E,ID> {
    E findByid(ID id) throws SQLException;
    E save(E entity) throws SQLException;
    E update(E entity) throws SQLException;
    E delete(E entity) throws SQLException;
    Set<E> findAll() throws SQLException;
    int deleteAll() throws SQLException;
}
