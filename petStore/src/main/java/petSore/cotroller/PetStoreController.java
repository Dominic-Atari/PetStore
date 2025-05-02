package petSore.cotroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import petSore.cotroller.model.PetStoreData;
import petSore.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {

	@Autowired
	private PetStoreService petStoreService;

	@PostMapping("petStore")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creating Pet Store {}", petStoreData);

		return petStoreService.savePetStore(petStoreData);
	}

	@PutMapping("/petStore/{petStoreId}")
	public PetStoreData updatePetstore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Updating Pet Store", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}

	@GetMapping("/petStore")
	public List<PetStoreData> retrieveAllPetStores() {
		log.info("Retrieving all Pet Stores");
		return petStoreService.retrieveAllPetStores();
	}

	@GetMapping("/petStore/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
		log.info("Retrieving Pet Store by ID={}", petStoreId);
		return petStoreService.retrievePetStoreById(petStoreId);
	}
	
	@DeleteMapping("/petStore/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId){
		log.info("Deleting Pet Store by ID={}", petStoreId);
		
		petStoreService.deletePetStoreById(petStoreId);
		
		return Map.of("Message", "Pet Store with ID= " + petStoreId + " is deleted successfully.");
	}
	
	@DeleteMapping("/petStore")
	void deleteAllPetStores() {
		log.info("Attempting to delete all Pet Stores");
		throw new UnsupportedOperationException("You are not allowed to Delete all Pet Stores");
	}
}
