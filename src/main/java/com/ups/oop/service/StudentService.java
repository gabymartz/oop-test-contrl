package com.ups.oop.service;


import com.ups.oop.dto.StudentDTO;
import com.ups.oop.entity.Student;
import com.ups.oop.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private List<StudentDTO> studentDTOList = new ArrayList<>();

    public ResponseEntity createStudent(StudentDTO studentDTO) {
        String studentId = studentDTO.getId();
        Optional<Student> studentOptional = studentRepository.findByStudentCode(studentId);

        if (studentOptional.isPresent()) {
            String errorMessage = "Student with id " + studentId + " already exist :C";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

        } else {
            if (studentDTO.getName().contains(" ")) {
                Student studentRecord = new Student();
                studentRecord.setStudentCode(studentDTO.getId());
                String[] nameStrings = studentDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                studentRecord.setName(name);
                studentRecord.setLastname(lastname);
                studentRepository.save(studentRecord);
                return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student name must contain two strings separated by a whitespaces");
            }

        }
    }
    public ResponseEntity getAllStudent(){
        Iterable<Student> studentIterable = studentRepository.findAll();
        List<StudentDTO> studentList = new ArrayList<>();

        for(Student st: studentIterable){
            StudentDTO student= new StudentDTO();
            student.setId(st.getStudentCode());
            student.setName(st.getName() + "-" + st.getLastname());
            studentList.add(student);
        }

        if(studentList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found :C");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }

    public ResponseEntity getStudentByCode(String studentCode){

        Optional<Student> studentOptional = studentRepository.findByStudentCode(studentCode);
        if(studentOptional.isPresent()){
            //if record was found
            Student studentFound = studentOptional.get();
            StudentDTO student = new StudentDTO((studentFound.getStudentCode()),
                    studentFound.getName() + " - " + studentFound.getLastname());

            return ResponseEntity.status(HttpStatus.OK).body(student);
        } else {
            //if record wasn't found
            String errorMessage = "Student with id " + studentCode + " doesn't exist :C";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
    public ResponseEntity updateStudent(StudentDTO studentDTO) {
        String studentId = studentDTO.getId();

        Optional<Student> studentOptional = studentRepository.findByStudentCode(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            if (studentDTO.getName().contains(" ")) {
                student.setStudentCode(studentId);
                String[] nameStrings = studentDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                student.setName(name);
                student.setLastname(lastname);
                studentRepository.save(student);
                return ResponseEntity.status(HttpStatus.OK).body(student);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student name must contain two strings separated by a whitespaces");
            }
        } else {
            String errorMessage = "Student with id " + studentId + "Not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteStudentById(String id){
        String message = " Student with id " +  id;
        Optional<Student> studentOptional = studentRepository.findByStudentCode(id);
        if(studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed succesfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " Not found");
        }
    }
}
