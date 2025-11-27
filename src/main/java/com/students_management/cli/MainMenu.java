package com.students_management.cli;

import com.students_management.model.Student;
import com.students_management.service.IStudentService;
import org.slf4j.Logger;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    //
    public final static String lineDivider = System.lineSeparator();

    public static void displayMainMenu(Logger logger){
        //logger.info(nl);
        logger.info("""
						*** Students Management Application ***
						1. List all students
						2. List student by ID
						3. Create a new student register
						4. Modify an existing student register
						5. Delete a student register by ID
						6. Exit
						
						Choose an option to proceed:""");
    }

    public static boolean executeOption(Logger logger , Scanner userInput, IStudentService<Student> studentService) {
        var option = Integer.parseInt(userInput.nextLine());
        var exit = false;

        switch (option){
            case 1 -> {
                logger.info(lineDivider + "Students List: " + lineDivider);
                List<Student> students = studentService.searchAll();
                students.forEach((student -> logger.info(student.toString() + lineDivider)));
            }
            case 2 -> {
                logger.info("Enter the student ID to search: ");
                Integer studentID = Integer.parseInt(userInput.nextLine());
                Student studentFound = studentService.searchByID(studentID);
                if(studentFound != null) {
                    logger.info("Student Found: " + studentFound + lineDivider);
                } else {
                    logger.info("Student not found with ID: " + studentFound + lineDivider);
                }
            }
            case 3 -> {
                logger.info("Create a student register: " + lineDivider);
                logger.info("Firstname: ");
                var firstName = userInput.nextLine();
                logger.info("Lastname: ");
                var lastName = userInput.nextLine();
                logger.info("Cellphone: ");
                var cellphone = userInput.nextLine();
                logger.info("Email: ");
                var email = userInput.nextLine();
                // The student ID is 'null' since we're creating a new register on the database.
                var student = new Student(null, firstName, lastName, cellphone, email);
                studentService.saveRegister(student);
                logger.info("Student created successfully: " + student + lineDivider);
            }
            case 4 -> {
                logger.info("Modify an existing student register: " + lineDivider);
                logger.info("Enter the student ID to be updated: ");
                Integer studentID = Integer.parseInt(userInput.nextLine());
                Student studentFound = studentService.searchByID(studentID);
                if(studentFound != null) {
                    logger.info("Enter the firstname: ");
                    var firstName = userInput.nextLine();
                    logger.info("Enter the lastname: ");
                    var lastName = userInput.nextLine();
                    logger.info("Enter the cellphone: ");
                    var cellphone = userInput.nextLine();
                    logger.info("Enter email: ");
                    var email = userInput.nextLine();
                    studentFound.setFirstName(firstName);
                    studentFound.setLastName(lastName);
                    studentFound.setCellphone(cellphone);
                    studentFound.setEmail(email);
                    studentService.saveRegister(studentFound);
                    logger.info("Student updated successfully: " + studentFound + lineDivider);
                }
                else {
                    logger.info("Student not found with ID: " + studentID + lineDivider);
                }
            }
            case 5 -> { // Eliminar estudiante
                logger.info("Delete a student register: " + lineDivider);
                logger.info("Enter the student ID to be deleted: ");
                var studentID = Integer.parseInt(userInput.nextLine());
                var studentFound = studentService.searchByID(studentID);
                if(studentFound != null) {
                    studentService.deleteRegister(studentFound);
                    logger.info("Student removed successfully: " + studentFound + lineDivider);
                }
                else {
                    logger.info("Student not found with ID: " + studentID);
                }
            }
            case 6 -> {
                logger.info("*** GOODBYE! ***" + lineDivider + lineDivider);
                exit = true;
            }
            default -> logger.info("Unknown option entered! Please try with a valid menu option: " + option + lineDivider);
        }

        return exit;
    }
}
