package com.project.service;

import com.project.domain.Role;
import com.project.domain.Student;
import com.project.dto.StudentCreateDto;
import com.project.dto.StudentResponseDto;
import com.project.enums.Faculty;
import com.project.enums.Gender;
import com.project.mapper.StudentMapper;
import com.project.repository.RoleRepository;
import com.project.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public StudentResponseDto save(StudentCreateDto dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Student student=studentMapper.toEntity(dto);
        Set<Role> roles = dto.getRoles().stream()
                .map(r -> roleRepository.findById(r.getName())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + r.getName())))
                .collect(Collectors.toSet());

        student.setRoles(roles);
        Student saved=studentRepository.save(student);
        return studentMapper.toDto(saved);
    }




    public boolean existsByLogin(String login){
        return studentRepository.existsByLogin(login);
    }



    public void validateRegister(StudentCreateDto dto){
        if (existsByLogin(dto.getLogin())){
            throw new RuntimeException("This login is already taken");
        }
        if (dto.getPassword().length()<=4){
            throw new RuntimeException("Password length must be greater than 4 characters");
        }
    }


    public StudentResponseDto findById(Long id){
        Student student=studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student not found"));
        return studentMapper.toDto(student);
    }



    public List<StudentResponseDto> findAll(){
        List<Student> student=studentRepository.findAll();
        return studentMapper.toDto(student);
    }



    public StudentResponseDto update(Long id, StudentResponseDto studentRequest){

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



    public StudentResponseDto updatedPartial(Long id, Map<String, Object> updates){
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



    public StudentResponseDto delete(Long id){
      Student student=studentRepository.findById(id)
              .orElseThrow(()->new RuntimeException("Student not found"));
      studentRepository.delete(student);
      return studentMapper.toDto(student);
    }


}
