package sv.gob.mh.siteputil.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sv.gob.mh.sitepcommon.domain.Departamento;
import sv.gob.mh.sitepcommon.repository.DepartamentoRepository;
import sv.gob.mh.sitepcommon.repository.util.BaseRepository;
import sv.gob.mh.siteputil.service.base.BaseService;


@Stateless
public class DepartamentoService extends BaseService<Departamento, Long> {

	@Inject
	private DepartamentoRepository departamentoRepository;

	@Override
	public BaseRepository<Departamento, Long> getRepository() {
		return departamentoRepository;
	}

} 