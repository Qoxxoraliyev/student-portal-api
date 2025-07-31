package com.project.resource;

import com.project.dto.StudentCreateDto;
import com.project.dto.StudentResponseDto;
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




    @PostMapping("/register")
    public ResponseEntity<StudentResponseDto> registerStudent(@RequestBody StudentCreateDto studentCreateDto){
        studentService.validateRegister(studentCreateDto);
        StudentResponseDto result=studentService.save(studentCreateDto);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/student/{id}")
    public ResponseEntity<StudentResponseDto> getById(@PathVariable Long id) {
        StudentResponseDto studentDto=studentService.findById(id);
        return ResponseEntity.ok(studentDto);
    }


    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDto>> getAll(){
        List<StudentResponseDto> studentDtos=studentService.findAll();
        return ResponseEntity.ok(studentDtos);
    }


    @PutMapping("/student/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentResponseDto studentDto){
        studentService.update(id,studentDto);
        return ResponseEntity.ok("Student successfully updated");
    }


    @PatchMapping("/student/{id}")
    public ResponseEntity<StudentResponseDto> updatePartial(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        StudentResponseDto updatedStudent=studentService.updatedPartial(id,updates);
        return ResponseEntity.ok(updatedStudent);
    }


   @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.ok("Student successfully deleted");
   }



}
