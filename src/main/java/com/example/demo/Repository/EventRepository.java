package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{
	Optional<Event> findByEventName(String eventName);
	List<Event> findAllByEventStatus(EventStatus eventStatus);
}
