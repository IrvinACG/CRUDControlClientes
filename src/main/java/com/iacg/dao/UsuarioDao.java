package com.iacg.dao;

import com.iacg.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author IrvinCG
 */
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
    public Usuario findByUsername(String username);
    
}
