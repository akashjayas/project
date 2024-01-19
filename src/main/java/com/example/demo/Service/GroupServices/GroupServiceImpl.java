package com.example.demo.Service.GroupServices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.Organizer.OrganizerService;
import com.example.demo.Service.Scheduling;
import com.example.demo.Service.SchedulingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import javax.swing.text.html.Option;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupRepository grpRepo;
	@Autowired
	private OrganizerRepository organizerRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private TouristSpotRepository spotRepository;
	@Autowired
	private ParticipantRepository participantRepository;
	@Autowired
	private Scheduling scheduling;
	
 	@Override
	public List<Group> getAllGroups() {
		return (List<Group>) grpRepo.findAll();
	}

	@Override
	public String addGroup(Group newGroup) {
		Optional<Group> grp=grpRepo.findByOrganizerId(newGroup.getOrganizerId());
		if(newGroup.getEventName()!=null){
			Optional<Event> event=eventRepository.findByEventName(newGroup.getEventName());
			event.get().increasePeopleCount(newGroup.getParticipantsLimit());
			eventRepository.save(event.get());
		}else{
			Optional<TouristSpot> spot=spotRepository.findBySpotName(newGroup.getSpotName());
			spot.get().increasePeopleCount(newGroup.getParticipantsLimit());
			spotRepository.save(spot.get());
		}
		if(grp.isPresent()) {
			if(grp.get().getGroupStatus() == GroupStatus.InActive) {
				Optional<Organizer> organizer=organizerRepository.findById(grp.get().getOrganizerId());
				organizer.get().increseOrganizedCount(organizer.get().getOrganizedCount());
				organizerRepository.save(organizer.get());
				grpRepo.save(newGroup);
				scheduling.addActiveGrpId(newGroup.getGroupId());
				return "GROUP SUCCESSFULLY CREATED";
			}
			else {
				return "YOU ARE ALREADY ORGANIZING ONE EVENT";
			}
		}
		else {
			grpRepo.save(newGroup);
			Optional<Organizer> organizer=organizerRepository.findById(newGroup.getOrganizerId());
			organizer.get().increseOrganizedCount(organizer.get().getOrganizedCount());
			organizerRepository.save(organizer.get());
			scheduling.addActiveGrpId(newGroup.getGroupId());
			return "GROUP SUCCESSFULLY CREATED";
		}
	}
	@Override
	public String removeGroupById(Integer groupId) {
		Optional<Group> grp=grpRepo.findById(groupId);
		if(grp.isPresent()){
			if(grp.get().getEventName()!=null){
				Optional<Event> event=eventRepository.findByEventName(grp.get().getEventName());
				event.get().decreasePeopleCount(grp.get().getParticipantsLimit());
				eventRepository.save(event.get());
			}else{
				Optional<TouristSpot> spot=spotRepository.findBySpotName(grp.get().getSpotName());
				spot.get().decreasePeopleCount(grp.get().getParticipantsLimit());
				spotRepository.save(spot.get());
			}
			Optional<Organizer> organizer=organizerRepository.findById(grp.get().getOrganizerId());
			List<Participant> participants=participantRepository.findAllByGroupId(grp.get().getGroupId());
			participants.forEach(participant -> participant.setParticipantStatus(UserStatus.Free));
			participantRepository.saveAll(participants);
			organizer.get().setOrganizerStatus(UserStatus.Free);
			organizerRepository.save(organizer.get());
			scheduling.getActiveGrpId().remove(groupId);
			grpRepo.deleteById(groupId);
			return "Group with id: "+groupId+" is removed successfully";
		}
		else {
			return "Group with id: "+groupId+" is not found";
		}
	}
	@Override
	public Group getGroupById(Integer grpId) {
		Optional<Group> grp=grpRepo.findById(grpId);
        return grp.orElse(null);
	}
	@Override
	public Group getGroupByOrganizerId(Integer orgId) {
		Optional<Group> grp=grpRepo.findByOrganizerId(orgId);
        return grp.orElse(null);
	}
}
