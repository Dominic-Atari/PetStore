package petStore.cotroller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import petStore.entity.Customer;

@Data
@NoArgsConstructor
public class CustomerData {

		private Long customerId;
		private String customerFirstName;
		private String customerLastName;
		private String email;
		
		public CustomerData(Customer customer) {
			customerId = customer.getCustomerId();
			customerFirstName = customer.getCustomerFirstName();
			customerLastName = customer.getCustomerLastName();
			email = customer.getEmail();
		
	}
}
