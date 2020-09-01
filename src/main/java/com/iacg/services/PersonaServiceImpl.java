package com.iacg.services;

import com.iacg.dao.PersonaDao;
import com.iacg.entity.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author IrvinCG
 */
@Service
public class PersonaServiceImpl implements PersonaService{
    
    @Autowired
    private PersonaDao personaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }

    @Override
    @Transactional
    public void guardarPersona(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminarPersona(Persona persona) {
        personaDao.deleteById(persona.getIdPersona());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Persona> listarPersonaPageable(Pageable pageable) {
        return personaDao.findAll(pageable);
    }

    
}
