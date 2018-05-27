package pe.edu.upc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Persona;

@Repository
public interface IPersonaDAO extends JpaRepository<Persona, Integer> {
	
	List<Persona> findByDniPersona(String dni);

	@Query("from Persona p where p.namePersona like %:namePersona%")
	List<Persona> buscarNombre(@Param("namePersona")String namePersona);
	
	@Query("from Persona p where p.emailPersona like %:emailPersona%")
	List<Persona> buscarEmail(@Param("emailPersona")String emailPersona);

	
	List<Persona> findBybirthDatePersona(Date birthDatePersona);

	

}
