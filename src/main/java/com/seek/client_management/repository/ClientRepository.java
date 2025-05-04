package com.seek.client_management.repository;

import com.seek.client_management.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = """
                SELECT 
                    AVG(age) as averageAge, 
                    STDDEV(age) as standardDeviation
                FROM clients
            """, nativeQuery = true)
    Object getClientMetrics();
}
