package com.anjoe.SpringJPA.controller;

import com.anjoe.SpringJPA.model.Student;
import com.anjoe.SpringJPA.service.StudentService;
import com.anjoe.SpringJPA.util.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    //Return a list of all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    //Returns student with /student/<uid>
    @GetMapping("/{uid}")
    public ResponseEntity<Student> getStudentById(@PathVariable int uid) {
        Optional<Student> optionalStudent = studentService.getStudentById(uid);
        return optionalStudent.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    //Creates new student with json in request body with POST request
    @PostMapping()
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (studentService.createStudent(student)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Updates existing student with /student/<uid> and json in request body using PUT request
    @PutMapping("/{uid}")
    public ResponseEntity<Student> updateStudent(@PathVariable int uid, @RequestBody Student newStudent) {
        if (studentService.updateStudent(uid, newStudent)) {
            return ResponseEntity.ok().body(newStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deleting existing student with /student/<uid> using DELETE request
    @DeleteMapping("/{uid}")
    public ResponseEntity<String> deleteStudent(@PathVariable int uid) {
        if (studentService.deleteStudent(uid)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
