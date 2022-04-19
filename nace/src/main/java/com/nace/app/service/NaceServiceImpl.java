package com.nace.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nace.app.dao.NaceRepository;
import com.nace.app.exception.NaceNotFoundException;
import com.nace.app.model.Nace;

@Service
public class NaceServiceImpl implements NaceService {
	
	@Autowired
	private NaceRepository repository;

	@Override
	public Nace saveNaceInfo(Nace nace) {
		return repository.save(nace);
	}

	@Override
	public List<Nace> loadAllNaceDetails(List<Nace> naces) {
		return repository.saveAll(naces);
	}

	@Override
	public Nace getNaceByOrder(Long order) {
		return Optional.ofNullable(repository.findByOrder(order))
				.orElseThrow(() -> new NaceNotFoundException("No Nace found for order - " + order));
	}

}
