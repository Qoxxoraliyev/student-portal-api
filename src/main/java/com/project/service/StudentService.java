package com.project.service;

import com.project.domain.Student;
import com.project.dto.StudentDto;
import com.project.enums.Faculty;
import com.project.enums.Gender;
import com.project.mapper.StudentMapper;
import com.project.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    public StudentDto save(StudentDto dto){
        Student student=studentMapper.toEntity(dto);
        Student saved=studentRepository.save(student);
        return studentMapper.toDto(saved);
    }


    public StudentDto findById(Long id){
        Student student=studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student not found"));
        return studentMapper.toDto(student);
    }



    public List<StudentDto> findAll(){
        List<Student> student=studentRepository.findAll();
        return studentMapper.toDto(student);
    }



    public StudentDto update(Long id,StudentDto studentRequest){

        Student existingStudent=studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student not found"));

        existingStudent.setFirstName(studentRequest.getFirstName());
        existingStudent.setLastName(studentRequest.getLastName());
        existingStudent.setEmail(studentRequest.getEmail());
        existingStudent.setPhoneNumber(studentRequest.getPhoneNumber());
        existingStudent.setDateOfBirth(studentRequest.getDateOfBirth());
        existingStudent.setGender(studentRequest.getGender());
        existingStudent.setAddress(studentRequest.getAddress());
        existingStudent.setFaculty(studentRequest.getFaculty());
        existingStudent.setStudentGroup(studentRequest.getStudentGroup());
        existingStudent.setUpdatedAt(studentRequest.getUpdatedAt());
        existingStudent.setCreatedAt(studentRequest.getCreatedAt());
        existingStudent.setStatus(studentRequest.getStatus());

        Student updatedStudent=studentRepository.save(existingStudent);

        return studentMapper.toDto(updatedStudent);
    }



    public StudentDto updatedPartial(Long id, Map<String, Object> updates){
        Student existingstudent=studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student not found"));
        if (updates.containsKey("firstName")){
            existingstudent.setFirstName((String) updates.get("firstName"));
        }
        if (updates.containsKey("lastName")){
            existingstudent.setLastName((String) updates.get("lastName"));
        }
        if (updates.containsKey("email")){
            existingstudent.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("phoneNumber")){
            existingstudent.setPhoneNumber((String) updates.get("phoneNumber"));
        }
        if (updates.containsKey("dateOfBirth")){
            existingstudent.setDateOfBirth((LocalDate.parse((String) updates.get("dateOfBirth"))));
        }
        if (updates.containsKey("gender")){
            existingstudent.setGender((Gender) updates.get("gender"));
        }
        if (updates.containsKey("address")){
            existingstudent.setAddress((String) updates.get("address"));
        }
        if (updates.containsKey("faculty")){
            existingstudent.setFaculty((Faculty) updates.get("faculty"));
        }
        if (updates.containsKey("studentGroup")){
            existingstudent.setStudentGroup((String) updates.get("studentGroup"));
        }
        if (updates.containsKey("status")){
            existingstudent.setStatus((Boolean) updates.get("status"));
        }
        existingstudent.setUpdatedAt(LocalDateTime.now());

        Student updatedStudent=studentRepository.save(existingstudent);
        return studentMapper.toDto(updatedStudent);
    }



    public StudentDto delete(Long id){
      Student student=studentRepository.findById(id)
              .orElseThrow(()->new RuntimeException("Student not found"));
      studentRepository.delete(student);
      return studentMapper.toDto(student);
    }


}
