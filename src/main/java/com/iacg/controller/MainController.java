package com.iacg.controller;

import com.iacg.configuration.Pages;
import com.iacg.entity.Persona;
import com.iacg.services.PersonaService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author IrvinCG
 */
@Controller
@RequestMapping
public class MainController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public ModelAndView getIndex(@RequestParam Map<String,Object> param) {
        ModelAndView model = new ModelAndView(Pages.PAGE_INDEX);
        
        int page = param.get("page") != null ? Integer.valueOf(param.get("page").toString()) -1 : 0;
        PageRequest pageRequest = PageRequest.of(page, 5);
        
       
        Page<Persona> pagePersona= personaService.listarPersonaPageable(pageRequest);
        int totalPage= pagePersona.getTotalPages();
        
        if(totalPage>0){
            List<Integer> paginas = IntStream.rangeClosed(1,totalPage).boxed().collect(Collectors.toList());
            model.addObject("paginas",paginas);
        }
        
        
        double saldoTotal = 0.0;
        for (Persona persona : pagePersona.getContent()) 
            saldoTotal+=persona.getSaldo();
        
        model.addObject("pagAnterior", page);
        model.addObject("pagActual", page+1);
        model.addObject("pagSiguiente", page+2);
        model.addObject("pagUltima", totalPage);
        model.addObject("saldoTotal", saldoTotal);
        model.addObject("totalClientes", pagePersona.getContent().size());
        model.addObject("personas", pagePersona.getContent());
        model.addObject("persona", new Persona());
        return model;
    }

    @GetMapping("/agregar")
    public ModelAndView getAgregarActualizar() {
        ModelAndView model = new ModelAndView(Pages.PAGE_FORM_PERSONA);
        model.addObject("persona", new Persona());
        return model;
    }

    @PostMapping("/guardar")
    public String getGuardar(@Valid @ModelAttribute Persona persona, Errors errores) {
        if (errores.hasErrors()) {
            return Pages.PAGE_FORM_PERSONA;
        }
        personaService.guardarPersona(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public ModelAndView getEditar(@ModelAttribute Persona persona) {
        ModelAndView model = new ModelAndView(Pages.PAGE_FORM_PERSONA);
        model.addObject("persona", personaService.getPersona(persona));
        return model;
    }

    //Forma 1 Path Variable
    @GetMapping("/eliminar/{idPersona}")
    public String getEliminar(@ModelAttribute Persona persona) {
        personaService.eliminarPersona(persona);
        return "redirect:/";
    }
    /*Forma 2 Path Query
    @GetMapping("/eliminar")
    public String getEliminar2(@ModelAttribute Persona persona){
        personaService.eliminarPersona(persona);
        return "redirect:/";
    }
     */
}
