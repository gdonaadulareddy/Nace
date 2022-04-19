package com.nace.app.service;

import java.util.List;

import com.nace.app.model.Nace;

public interface NaceService {

	Nace saveNaceInfo(Nace nace);
	
	List<Nace> loadAllNaceDetails(List<Nace> naces);
	
	Nace getNaceByOrder(Long order);
}
