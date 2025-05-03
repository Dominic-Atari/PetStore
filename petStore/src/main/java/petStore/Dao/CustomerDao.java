package petStore.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import petStore.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);

}
