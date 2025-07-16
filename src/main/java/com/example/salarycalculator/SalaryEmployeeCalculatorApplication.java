// =====================================================
// MAIN SPRING BOOT APPLICATION CLASS
// =====================================================
// This is the entry point of your Spring Boot application
// When you run this class, Spring Boot starts the entire application

package com.example.salarycalculator;

// Spring Boot imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application Class
 * 
 * This class serves as the entry point for the entire application.
 * The @SpringBootApplication annotation is a convenience annotation that adds all of the following:
 * - @Configuration: Tags the class as a source of bean definitions
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings
 * - @ComponentScan: Tells Spring to look for other components, configurations, and services
 */
@SpringBootApplication
public class SalaryEmployeeCalculatorApplication {

	/**
	 * Main method - Application entry point
	 * 
	 * This method is called when you start the application.
	 * SpringApplication.run() does the following:
	 * 1. Creates the ApplicationContext (Spring container)
	 * 2. Registers a CommandLinePropertySource to expose command line arguments as Spring properties
	 * 3. Refreshes the application context, loading all singleton beans
	 * 4. Triggers any CommandLineRunner beans
	 * 5. Starts the embedded web server (Tomcat by default)
	 * 
	 * @param args Command line arguments passed to the application
	 */
	public static void main(String[] args) {
		// SpringApplication.run() starts the entire Spring Boot application
		// It automatically configures everything based on your dependencies
		SpringApplication.run(SalaryEmployeeCalculatorApplication.class, args);
	}

}
