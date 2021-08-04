package com.example.demo.infrastructure;

import com.example.demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  DoctorRepository extends JpaRepository<Doctor,Long> {

    Doctor findById(long id);
}
