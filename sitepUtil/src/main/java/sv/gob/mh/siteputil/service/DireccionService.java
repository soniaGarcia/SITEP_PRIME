package sv.gob.mh.siteputil.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sv.gob.mh.sitepcommon.domain.Direccion;
import sv.gob.mh.sitepcommon.repository.DireccionRepository;
import sv.gob.mh.sitepcommon.repository.util.BaseRepository;
import sv.gob.mh.siteputil.service.base.BaseService;

@Stateless
public class DireccionService extends BaseService<Direccion, Long> {

	@Inject
	private DireccionRepository direccionRepository;

	@Override
	public BaseRepository<Direccion, Long> getRepository() {
		return direccionRepository;
	}

} 