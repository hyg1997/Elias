package pe.edu.upc.service;

import java.util.Date;
import java.util.List;

import pe.edu.upc.entity.Persona;

public interface IPersonaService {

	public void insertar(Persona persona);

	public void modificar(Persona persona);

	public void eliminar(int idPersona);

	public Persona listarId(int idPersona);

	public List<Persona> listar();

	List<Persona> findByDniPersona(String dni);

	List<Persona> buscarNombre(String namePersona);

	List<Persona> buscarEmail(String emailPersona);

	List<Persona> findBybirthDatePersona(Date birthDatePersona);

}
