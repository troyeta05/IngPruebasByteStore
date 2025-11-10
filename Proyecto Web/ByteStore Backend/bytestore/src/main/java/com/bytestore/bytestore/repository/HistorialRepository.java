package com.bytestore.bytestore.repository;

import com.bytestore.bytestore.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, String> {
    
}
