package sv.gob.mh.siteputil.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import sv.gob.mh.sitepcommon.domain.Cliente;
import sv.gob.mh.sitepcommon.repository.ClienteRepository;
import sv.gob.mh.sitepcommon.repository.util.BaseRepository;
import sv.gob.mh.siteputil.service.base.BaseService;


@Stateless
public class ClienteService extends BaseService<Cliente, Long> {

	@Inject
	private ClienteRepository clienteRepository;

	@Override
	public BaseRepository<Cliente, Long> getRepository() {
		return clienteRepository;
	}

} 