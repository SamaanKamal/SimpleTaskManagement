package com.example.Task.Management.Repository;

import com.example.Task.Management.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Integer> {
}
