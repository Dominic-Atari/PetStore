package petStore.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import petStore.entity.Customer;
import petStore.entity.PetStore;

public interface PetStoreDao extends JpaRepository<PetStore, Long> {

	Optional<PetStore> findByPetStoreEmail(String petStoreEmail);

	

}