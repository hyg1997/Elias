package pe.edu.upc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.entity.Persona;
import pe.edu.upc.service.IPersonaService;

@Controller
@RequestMapping("/persona")

public class PersonaController {
	@Autowired
	private IPersonaService pService;

	@RequestMapping("/")
	public String irPersona(Map<String, Object> model) {
		model.put("listapersonas", pService.listar());
		return "listPersona";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("persona", new Persona());
		return "persona";
	}

	@RequestMapping("/registrar")
	public String registrar(Map<String, Object> model, @ModelAttribute Persona persona) throws ParseException {
		
		if (persona.getIdPersona() > 0) {
			pService.modificar(persona);
		} else {
			pService.insertar(persona);
		}
		model.put("listaPersonas", pService.listar());
		model.put("Mensaje", "Error!!");
		return "listPersona";
	}

	@RequestMapping("/modificar")
	public String modificar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		Persona per = pService.listarId(id);
		per.getBirthDatePersona();
		model.put("persona", per);
		return "persona";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		if (id != null && id > 0) {
			pService.eliminar(id);
			model.put("listaPersonas", pService.listar());
		}
		return "listPersona";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaPersonas", pService.listar());
		return "listPersona";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Persona persona) {
		pService.listarId(persona.getIdPersona());
		return "listPersona";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Persona persona) throws ParseException {
		List<Persona> listaPersonas;
		if (StringUtils.isNumeric(persona.getDniPersona())) {

			listaPersonas = pService.findByDniPersona(persona.getDniPersona());

		} else {
			persona.setNamePersona(persona.getDniPersona());
			listaPersonas = pService.buscarNombre(persona.getNamePersona());

			if (listaPersonas.isEmpty()) {
				persona.setEmailPersona(persona.getDniPersona());
				listaPersonas = pService.buscarEmail(persona.getEmailPersona());
			}

			if (listaPersonas.isEmpty()) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					persona.setBirthDatePersona(sdf.parse(persona.getDniPersona()));

					listaPersonas = pService.findBybirthDatePersona(persona.getBirthDatePersona());
				} catch (Exception e) {
					model.put("mensaje", "Formato incorrecto!!");

				}
			}

		}

		if (listaPersonas.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}
		model.put("listaPersonas", listaPersonas);
		return "buscar";
	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("persona", new Persona());
		return "buscar";
	}

}

