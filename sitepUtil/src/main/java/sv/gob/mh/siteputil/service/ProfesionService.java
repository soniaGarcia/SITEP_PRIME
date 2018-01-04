package sv.gob.mh.siteputil.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sv.gob.mh.sitepcommon.domain.Profesion;
import sv.gob.mh.sitepcommon.repository.ProfesionRepository;
import sv.gob.mh.sitepcommon.repository.util.BaseRepository;
import sv.gob.mh.siteputil.service.base.BaseService;

@Stateless
public class ProfesionService extends BaseService<Profesion, Long> {

	@Inject
	private ProfesionRepository profesionRepository;

	@Override
	public BaseRepository<Profesion, Long> getRepository() {
		return profesionRepository;
	}

} 