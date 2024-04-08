package com.example.Task.Management.Repository;

import com.example.Task.Management.Entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee,Integer> {
}
