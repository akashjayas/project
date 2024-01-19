package com.example.demo.Repository;

import com.example.demo.Model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant,Integer> {
	Optional<Participant> findByUserId(Integer userId);
	List<Participant> findAllByGroupId(Integer groupId);
	List<Participant> findAllByParticipantStatus(UserStatus userStatus);
}
