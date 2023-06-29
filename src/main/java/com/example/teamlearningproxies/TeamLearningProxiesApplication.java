package com.example.teamlearningproxies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TeamLearningProxiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamLearningProxiesApplication.class, args);
    }


    @Component
    public static class MyApplicationRunner implements ApplicationRunner {

        private final CustomerRepository customerRepository;

        @Autowired
        public MyApplicationRunner(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            Customer customer = new Customer();
            customer.setUsername("john_doe");
            customer.setEmail("john.doe@example.com");

            // Save the user
            customerRepository.save(customer);
            System.out.println("User saved with ID: " + customer.getId());

            System.out.println("find by ID:");
            System.out.println(customerRepository.findById(customer.getId()));

            System.out.println("find by email");
            System.out.println(customerRepository.findByEmail("john.doe@example.com"));

            System.out.println("findByUsername");;
        }
    }

}
