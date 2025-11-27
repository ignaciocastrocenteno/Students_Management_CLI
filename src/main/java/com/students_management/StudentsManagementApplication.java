package com.students_management;

import com.students_management.cli.MainMenu;
import com.students_management.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.students_management.model.Student;

import java.util.List;
import java.util.Scanner;

import static com.students_management.cli.MainMenu.displayMainMenu;
import static com.students_management.cli.MainMenu.executeOption;

@SpringBootApplication
public class StudentsManagementApplication implements CommandLineRunner {
	/*
		Creating a Logger private final static attribute to send feedback to the user, through the terminal, since we
		are using Spring Framework for the project.
	 */
	private final static Logger logger = LoggerFactory.getLogger(StudentsManagementApplication.class);
	private static boolean END_OF_DATA = false;

	/*
		Dependency Injection from the 'Service' layer to the 'Presentation' layer. It's important to specify the
		concrete class to be passed in to the 'Generic Mechanism' to avoid potential 'Unchecked Raw Type & Warnings'
	 */
	@Autowired
	private IStudentService<Student> studentService;

	public static void main(String[] args) {
		logger.info("Starting System!");
		// Starting app the 'Spring's Object Factory' to init the application's lifecycle
		SpringApplication.run(StudentsManagementApplication.class, args);
		logger.info("Closing System!");
	}

	@Override
	public void run(String... args) throws Exception {
        logger.info("{}Executing Spring's Entity Manager Factory, through the .run() method...{}",
				MainMenu.lineDivider, MainMenu.lineDivider);
		var userInput = new Scanner(System.in);
		while(!END_OF_DATA){
			displayMainMenu(logger);
			END_OF_DATA = executeOption(logger, userInput, studentService);
			// logger.info(lineDivider);
		}
	}
}
