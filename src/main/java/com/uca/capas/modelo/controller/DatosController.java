package com.uca.capas.modelo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DatosController {
	@RequestMapping("/resultado")
	public ModelAndView resultado(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String nombre = request.getParameter("name");
		String apellido = request.getParameter("last");
		String fecha = request.getParameter("date");
		String lugar = request.getParameter("born");
		String colegio = request.getParameter("school");
		String fijo = request.getParameter("tel");
		String movil = request.getParameter("cel");
		int bandera = 0;
		
		List<String> mensajes = new ArrayList<>();
		
		if(nombre.trim().isEmpty() || nombre.length() > 25) {
			mensajes.add("El nombre debe tenes al menor 1 caracter y máximo 25!");
			bandera = 1;
		}
		
		if(apellido.trim().isEmpty() || apellido.length() > 25) {
			mensajes.add("El apellido debe tenes al menor 1 caracter y máximo 25!");
			bandera = 1;
		}
		
		if(lugar.trim().isEmpty() || lugar.length() > 25) {
			mensajes.add("El lugar de nacimiento debe tener al menos 1 caracter y máximo 25!");
			bandera = 1;
		}
		
		if(colegio.trim().isEmpty() || colegio.length() > 100) {
			mensajes.add("El instituto o colegio debe tener al menos 1 caracter y máximo 100!");
			bandera = 1;
		}
		
		if(!fijo.matches("\\d*") || fijo.trim().isEmpty() || fijo.length() < 8 || fijo.length() > 8) {
			mensajes.add("El numero de teléfono fijo debe tener 8 digitos!");
			bandera = 1;
		}
		
		if(!movil.matches("\\d*") || movil.trim().isEmpty() || movil.length() < 8 || movil.length() > 8) {
			mensajes.add("El numero de teléfono móvil debe tener 8 digitos!");
			bandera = 1;
		}
		
		if(!fecha.isEmpty()) {
			String fechaInicio = "2003-01-01"; //fecha de ejemplo
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date fechaInicioDate = date.parse(fechaInicio);
				Date fechaActual = date.parse(fecha);
				if(fechaInicioDate.after(fechaActual)) { 
					mensajes.add("La fecha no puede ser menor a 01/01/2003!");
					bandera = 1;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else {
			mensajes.add("La fecha no puede quedar vacia!");
			bandera = 1;
		}
		
		if(bandera == 0) {
			mensajes.add("El alumno se ha ingresado con exito!");
		}
		
		
		mav.addObject("message", mensajes);
		mav.addObject("bandera", bandera);
		mav.setViewName("form/resultado");
		return mav;
	}
}
