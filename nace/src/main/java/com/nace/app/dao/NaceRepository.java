package com.nace.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nace.app.model.Nace;

public interface NaceRepository extends JpaRepository<Nace, Long> {
		
	@Query("select n from Nace n where n.order=?1")
	Nace findByOrder(Long order);
	
}
