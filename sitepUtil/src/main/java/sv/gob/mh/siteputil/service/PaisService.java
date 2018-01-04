package sv.gob.mh.siteputil.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sv.gob.mh.sitepcommon.domain.Pais;
import sv.gob.mh.sitepcommon.repository.PaisRepository;
import sv.gob.mh.sitepcommon.repository.util.BaseRepository;
import sv.gob.mh.siteputil.service.base.BaseService;


@Stateless
public class PaisService extends BaseService<Pais, Long> {

	@Inject
	private PaisRepository paisRepository;

	@Override
	public BaseRepository<Pais, Long> getRepository() {
		return paisRepository;
	}

} 