package petSore.service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import petSore.Dao.PetStoreDao;
import petSore.cotroller.model.PetStoreData;
import petSore.entity.PetStore;

@Service
@Data //iIt still running well without Data annotation
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;

	//It still compiles with out error even when I did not include @Transactuonal(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId, petStoreData.getPetStoreEmail());
		
		setFieldsInPetStore(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));
	}

	private void setFieldsInPetStore(PetStore petStore, PetStoreData petStoreData) {
		
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreEmail(petStoreData.getPetStoreEmail());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPeStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		
	}

	private PetStore findOrCreatePetStore(Long petStoreId, String petStoreEmail) {
		PetStore petStore;
		
		if(Objects.isNull(petStoreId)) {
			
			Optional<PetStore> opPetStore = petStoreDao.findByPetStoreEmail(petStoreEmail);
			
			if(opPetStore.isPresent()) {
				throw new DuplicateKeyException("Pet Store withr Email " + petStoreEmail + " already exist." );
			}
			petStore = new PetStore();
		}
		else {
			petStore = findPetStoreById(petStoreId);
			
		}
		return petStore;
		
	}

	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId).orElseThrow();
		
	}

	public List<PetStoreData> retrieveAllPetStores() {
		//@formatter:off
		return petStoreDao.findAll()
				.stream()
				.map(PetStoreData::new)
				.toList();
		//@formatter:on
	}
	//@Transactional(readOnly = true)
	public PetStoreData retrievePetStoreById(Long petStoreId) {
		PetStore petStore = findPetStoreById(petStoreId);
		return new PetStoreData(petStore);
	}

	public void deletePetStoreById(Long petStoreId) {
		PetStore petStore = findPetStoreById(petStoreId);
		
		petStoreDao.delete(petStore);
		
	}

	
}
