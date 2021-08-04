package com.example.demo.infrastructure;

import com.example.demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DoctorRepository extends JpaRepository<Doctor,Long> {
//    Doctor findDoctorByUsername(String username);
    Doctor findById(long id);
}
