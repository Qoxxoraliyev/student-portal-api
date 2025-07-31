package com.project.mapper;

import com.project.domain.Student;
import com.project.dto.StudentCreateDto;
import com.project.dto.StudentResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {

    public StudentResponseDto toDto(Student student){
        if (student==null){
            return null;
        }

        StudentResponseDto dto=new StudentResponseDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setPhoneNumber(student.getPhoneNumber());
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setGender(student.getGender());
        dto.setAddress(student.getAddress());
        dto.setFaculty(student.getFaculty());
        dto.setStudentGroup(student.getStudentGroup());
        dto.setCreatedAt(student.getCreatedAt());
        dto.setUpdatedAt(student.getUpdatedAt());
        dto.setStatus(student.getStatus());
        dto.setRoles(student.getRoles());
        return dto;
    }

    public Student toEntity(StudentCreateDto dto){
        if (dto==null){
            return null;
        }

        Student student=new Student();
        student.setLogin(dto.getLogin());
        student.setPassword(dto.getPassword());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setAddress(dto.getAddress());
        student.setFaculty(dto.getFaculty());
        student.setStudentGroup(dto.getStudentGroup());
        return student;
    }


    public List<StudentResponseDto> toDto(List<Student> students){
        List<StudentResponseDto> dtoList=new ArrayList<>();
        for (Student student:students){
            dtoList.add(toDto(student));
        }
        return dtoList;
    }



}
