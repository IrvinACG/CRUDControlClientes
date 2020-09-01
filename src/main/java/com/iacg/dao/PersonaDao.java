package com.iacg.dao;

import com.iacg.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author IrvinCG
 */
public interface PersonaDao extends JpaRepository<Persona, Long>{
    
}
