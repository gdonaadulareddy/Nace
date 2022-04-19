package com.nace.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nace.app.model.Nace;
import com.nace.app.service.NaceService;
import com.nace.app.util.CSVUtil;

@RestController
@RequestMapping("nace/v1/")
public class NaceController {
	
	@Autowired
	private NaceService service;

	@PostMapping( path = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Nace> saveNaceData(@RequestBody final Nace nace) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveNaceInfo(nace));
	}

	@GetMapping( path = "get/{order}")
	public ResponseEntity<Nace> getNaceData(@PathVariable("order") final Long order) {
		return ResponseEntity.ok(service.getNaceByOrder(order));
	}
	
	@PostMapping( path = "load/all", consumes = "multipart/form-data")
	public ResponseEntity<HttpStatus> uploadFile(@RequestParam("file") MultipartFile file) {
		if (CSVUtil.hasCSVFormat(file)) {
			try {
				service.loadAllNaceDetails(CSVUtil.csvToNaces(file.getInputStream()));
				return ResponseEntity.status(HttpStatus.CREATED).build();
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
