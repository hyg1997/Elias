package pe.edu.upc.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dao.IPersonaDAO;
import pe.edu.upc.entity.Persona;
import pe.edu.upc.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaDAO pDao;

	@Override
	public void insertar(Persona persona) {
		pDao.save(persona);

	}

	@Override
	public void modificar(Persona persona) {
		pDao.save(persona);

	}

	@Override
	public void eliminar(int idPersona) {
		pDao.delete(idPersona);

	}

	@Override
	public Persona listarId(int idPersona) {
		return pDao.findOne(idPersona);
	}

	@Override
	public List<Persona> listar() {
		return pDao.findAll();
	}

	@Override
	public List<Persona> findByDniPersona(String dni) {
		return pDao.findByDniPersona(dni);
	}

	@Override
	public List<Persona> buscarNombre(String namePersona) {
		return pDao.buscarNombre(namePersona);
	}

	@Override
	public List<Persona> buscarEmail(String emailPersona) {
		return pDao.buscarEmail(emailPersona);
	}

	@Override
	public List<Persona> findBybirthDatePersona(Date birthDatePersona) {
		return pDao.findBybirthDatePersona(birthDatePersona);
	}

}
