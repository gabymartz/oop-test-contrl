package com.ups.oop.service;

import com.ups.oop.dto.PersonDTO;
import com.ups.oop.dto.StudentDTO;
import com.ups.oop.entity.Person;
import com.ups.oop.entity.Student;
import com.ups.oop.repository.PersonRepository;
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
    private List<PersonDTO> personDTOList = new ArrayList<>();

    public ResponseEntity createStudent(StudentDTO studentDTO) {
        String studentId = studentDTO.getId();
        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);

        if (studentOptional.isPresent()) {
            String errorMessage = "Person with id " + studentId + " doesn't exist :C";
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
        Iterable <Student> studentIterable = studentRepository.findAll();
        List<StudentDTO> studentList = new ArrayList<>();

        for(Student st: studentIterable){
            StudentDTO student= new StudentDTO();
            student.setId(st.getStudentCode());
            student.setName(st.getName());
            student.setLastName(st.getLastname());
            studentList.add(student);
        }

        if(studentList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PersonDTO List Not Found :C");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }

    public ResponseEntity getStudentById(String studentId){

        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);
        if(studentOptional.isPresent()){
            //if record was found
            Student studentFound = studentOptional.get();
            StudentDTO student = new StudentDTO((studentFound.getStudentCode()),
                    studentFound.getName() + " - " + studentFound.getLastname()
            return ResponseEntity.status(HttpStatus.OK).body(student);
        } else {
            //if record wasn't found
            String errorMessage = "Person with id " + studentId + " doesn't exist :C";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
