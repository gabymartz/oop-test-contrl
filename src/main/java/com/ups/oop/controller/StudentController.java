package com.ups.oop.controller;

import com.ups.oop.dto.PersonDTO;
import com.ups.oop.dto.StudentDTO;
import com.ups.oop.service.PersonService;
import com.ups.oop.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get-all-student")
    public ResponseEntity getAllStudent() {
        return this.studentService.getAllStudent();
    }

    @GetMapping("/get-person")
    public ResponseEntity getStudentById(@RequestParam String id) {
        return this.studentService.getStudentById(id);
    }

    @PostMapping("/person")
    public ResponseEntity createStudent(@RequestBody StudentDTO studentDTO) {
        return this.studentService.createStudent(studentDTO);
    }

    @PutMapping("/update-student")
    public ResponseEntity updateStudent(@RequestBody StudentDTO studentDTO) {
        return this.studentService.updateStudent(studentDTO);
    }

    @DeleteMapping("/remove-student")
    public ResponseEntity deleteStudent(@RequestParam String id) {
        return this.studentService.deleteStudentById(id);
    }
}

}
