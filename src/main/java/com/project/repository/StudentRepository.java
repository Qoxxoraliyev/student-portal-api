package com.project.repository;

import com.project.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

    boolean existsByLogin(String login);
    Student findByLogin(String login);
}
