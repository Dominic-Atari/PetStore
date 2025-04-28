package pet_store.controller.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import pet_store.entity.Customer;
import pet_store.entity.Employee;
import pet_store.entity.PetStore;

//DTO Data Transfer Object
@Data
@NoArgsConstructor
public class PetStoreData {
	
	
	private Long petStoreId;
	private String petStoreName;
	private String petStoreEmail;
	private String petStoreAddress;
	private String peStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
	
	//Employee changed to employeeData
		private Set<PetStoreEmployee> employees = new HashSet<>();
		
		private Set<PetStoreCustomer> customers = new HashSet<>();
	
	public PetStoreData(PetStore petStore) {
		petStoreId = petStore.getPetStoreId();
		petStoreName = petStore.getPetStoreName();
		petStoreEmail = petStore.getPetStoreEmail();
		petStoreAddress = petStore.getPetStoreAddress();
		peStoreCity = petStore.getPeStoreCity();
		petStoreState = petStore.getPetStoreState();
		petStoreZip = petStore.getPetStoreZip();
		petStorePhone = petStore.getPetStorePhone();
		
		for(Employee employee : petStore.getEmployees()) {
			employees.add(new PetStoreEmployee(employee));
		}
		
		for(Customer customer : petStore.getCustomers()) {
			customers.add(new PetStoreCustomer(customer));
		}
	}
	
	
	//inner class
	@Data
	@NoArgsConstructor
	public static class PetStoreEmployee{
		private Long employeeId;
		private String employeeFirstName;
		private String employeeLastName;
		private String employeephone;
		private String employeeJobTitle;
		
		public PetStoreEmployee(Employee employee) {
			employeeId = employee.getEmployeeId();
			employeeFirstName = employee.getEmployeeFirstName();
			employeeLastName = employee.getEmployeeLastName();
			employeephone  = employee.getEmployeephone();
			employeeJobTitle = employee.getEmployeeJobTitle();
			
		}
	}
	//inner class
	@Data
	@NoArgsConstructor
	public static class PetStoreCustomer{
		private Long customerId;
		private String customerFirstName;
		private String customerLastName;;
		
		@Column(unique = true)
		private String email; 
		
		private Set<PetStoreData> petStores = new HashSet<>();
		
		PetStoreCustomer(Customer customer){
			customer.setCustomerId(customerId);
			customer.setCustomerFirstName(customerFirstName);
			customer.setCustomerLastName(customerLastName);
			customer.setEmail(email);
			
			for(PetStore petStore : customer.getPetStore()){
				petStores.add(new PetStoreData(petStore));
			}
		}
	}
}
