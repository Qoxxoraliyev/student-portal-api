package com.project.mapper;

import com.project.domain.Student;
import com.project.dto.StudentDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {

    public StudentDto toDto(Student student){
        if (student==null){
            return null;
        }

        StudentDto dto=new StudentDto();
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
        return dto;
    }

    public Student toEntity(StudentDto dto){
        if (dto==null){
            return null;
        }

        Student student=new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setAddress(dto.getAddress());
        student.setFaculty(dto.getFaculty());
        student.setStudentGroup(dto.getStudentGroup());
        student.setCreatedAt(dto.getCreatedAt());
        student.setUpdatedAt(dto.getUpdatedAt());
        student.setStatus(dto.getStatus());
        return student;
    }


    public List<StudentDto> toDto(List<Student> students){
        List<StudentDto> dtoList=new ArrayList<>();
        for (Student student:students){
            dtoList.add(toDto(student));
        }
        return dtoList;
    }



}
