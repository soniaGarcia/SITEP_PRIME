package sv.gob.mh.siteputil.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sv.gob.mh.sitepcommon.domain.Municipio;
import sv.gob.mh.sitepcommon.repository.MunicipioRepository;
import sv.gob.mh.sitepcommon.repository.util.BaseRepository;
import sv.gob.mh.siteputil.service.base.BaseService;

@Stateless
public class MunicipioService extends BaseService<Municipio, Long> {

	@Inject
	private MunicipioRepository municipioRepository;

	@Override
	public BaseRepository<Municipio, Long> getRepository() {
		return municipioRepository;
	}

} 