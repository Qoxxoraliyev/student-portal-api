package com.project.resource;

import com.project.dto.StudentDto;
import com.project.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentResource {
    private final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/student")
    public StudentDto create(@RequestBody StudentDto studentDto){
        return studentService.save(studentDto);
    }


    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable Long id) {
        StudentDto studentDto=studentService.findById(id);
        return ResponseEntity.ok(studentDto);
    }


    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAll(){
        List<StudentDto> studentDtos=studentService.findAll();
        return ResponseEntity.ok(studentDtos);
    }


    @PutMapping("/student/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody StudentDto studentDto){
        studentService.update(id,studentDto);
        return ResponseEntity.ok("Student successfully updated");
    }


    @PatchMapping("/student/{id}")
    public ResponseEntity<StudentDto> updatePartial(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        StudentDto updatedStudent=studentService.updatedPartial(id,updates);
        return ResponseEntity.ok(updatedStudent);
    }


   @DeleteMapping("/student/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.ok("Student successfully deleted");
   }



}
