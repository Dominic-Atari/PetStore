package pet_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet_store.controller.model.PetStoreData;
import pet_store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;
	
	@PostMapping("/petStore")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData savePetStore(
			@RequestBody PetStoreData petStoreData) {
		log.info("Creating petStore eith ID={}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	} 
}
