package petSore.cotroller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import petSore.entity.Customer;
import petSore.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {

	private Long petStoreId;
	private String petStoreName;
	private String petStoreEmail;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;

	private Set<CustomerResponse> customers = new HashSet<>();
	
	public PetStoreData(PetStore petStore) {
		petStoreId = petStore.getPetStoreId();
		petStoreName = petStore.getPetStoreName();
		petStoreEmail = petStore.getPetStoreEmail();
		petStoreAddress = petStore.getPetStoreAddress();
		petStoreCity = petStore.getPeStoreCity();
		petStoreState = petStore.getPetStoreState();
		petStoreZip = petStore.getPetStoreZip();
		petStorePhone = petStore.getPetStorePhone();
		
		for(Customer customer : petStore.getCustomers()) {
			customers.add(new CustomerResponse(customer));
		}
		
	}
	
	@Data
	@NoArgsConstructor
	public class CustomerResponse{
		private Long customerId;
		private String customerFirstName;
		private String customerLastName;
		private String email;
		
		public CustomerResponse(Customer customer) {
			customerId = customer.getCustomerId();
			customerFirstName = customer.getCustomerFirstName();
			customerLastName = customer.getCustomerLastName();
			email = customer.getEmail();
		}

	}
}
