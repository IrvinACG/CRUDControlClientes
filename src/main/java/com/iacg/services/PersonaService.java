package com.iacg.services;

import com.iacg.entity.Persona;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author IrvinCG
 */
public interface PersonaService{
    
    public List<Persona> listarPersonas();
    
    public Persona getPersona(Persona persona);
    
    public void guardarPersona(Persona persona);
    
    public void eliminarPersona(Persona persona);

    public Page<Persona> listarPersonaPageable(Pageable pageable);
}
