package com.example.bankingapplication.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class DropTransaction {
    @PersistenceContext
    private EntityManager entityManager;

        @Transactional
    public void dropTable(String tableName) {
        // Warning: This executes raw SQL and should be used with extreme caution.
        String sqlQuery = "DROP TABLE " + tableName;
        String trunQ= "TRUNCATE TABLE " + tableName;
        entityManager.createNativeQuery(sqlQuery).executeUpdate();
        entityManager.createNativeQuery(trunQ).executeUpdate();
    }
    @Transactional
    public void truncateTable(String tableName) {
        // Warning: This executes raw SQL and should be used with extreme caution.
//        String sqlQuery = "DROP TABLE " + tableName;
        String trunQ= "TRUNCATE TABLE " + tableName;
//        entityManager.createNativeQuery(sqlQuery).executeUpdate();
        entityManager.createNativeQuery(trunQ).executeUpdate();
    }
}
