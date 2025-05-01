package petSore.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import petSore.entity.PetStore;

public interface PetStoreDao extends JpaRepository<PetStore, Long> {

	Optional<PetStore> findByPetStoreEmail(String petStoreEmail);

}