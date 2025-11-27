package com.students_management.service;

import com.students_management.model.Student;
import com.students_management.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService<Student> {
    // Dependency Injection from the 'Repository' layer into the 'Service' layer
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public List<Student> searchAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student searchByID(Integer studentID) {
       return studentRepository.findById(studentID).orElse(null);
    }

    @Override
    public void saveRegister(Student studentToSave) {
        studentRepository.save(studentToSave);
    }

    @Override
    public void deleteRegister(Student studentToDelete) {
        studentRepository.delete(studentToDelete);
    }
}
