package test.mrgan.mongodb.repository;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.mrgan.mongodb.Application;
import com.mrgan.mongodb.model.Customer;
import com.mrgan.mongodb.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:/config/test.properties")
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void save() throws Exception {
		customerRepository.save(new Customer("firstName3", "lastName", new Date()));
	}

	@Test
	public void findAll() throws Exception {
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
	}

	@Test
	public void template() throws Exception {
	}
}
