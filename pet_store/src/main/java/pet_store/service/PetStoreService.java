package pet_store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import pet_store.controller.model.PetStoreData;
import pet_store.dao.PetStoreDao;
import pet_store.entity.PetStore;

@Service
@Data
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;

	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId);
		
		setFieldsPetStore(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));
	}

	private void setFieldsPetStore(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreEmail(petStoreData.getPetStoreEmail());
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		PetStore petStore;
		
		if(Objects.isNull(petStoreId)) {
			petStore = new PetStore();
		}
		else {
			petStore = findPetStoreById(petStoreId);
		}
		
		return petStore;
	}

	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId).
				orElseThrow(() -> new NoSuchElementException(
						"PetStoreId ID= " + petStoreId + " was not found"));
	}

}
