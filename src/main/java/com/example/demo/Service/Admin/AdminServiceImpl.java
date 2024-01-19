package com.example.demo.Service.Admin;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.Event.EventService;
import com.example.demo.Service.GroupServices.GroupService;
import com.example.demo.Service.Organizer.OrganizerService;
import com.example.demo.Service.ParticipantServices.ParticipantService;
import com.example.demo.Service.TouristSpot.TouristSpotService;
import com.example.demo.Service.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TouristSpotService touristSpotService;
    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private OrganizerRepository organizerRepository;

    @Override
    public String addManyUsers(List<User> users) {
        userRepository.saveAll(users);
        return "Success";
    }

    @Override
    public String addEvent(Event newEvent) {
        return eventService.addEvent(newEvent);
    }

    @Override
    public String addAllEvents(List<Event> events) {
        return eventService.addAllEvents(events);
    }

    @Override
    public String addSpot(TouristSpot spot) {
        return touristSpotService.addSpot(spot);
    }

    @Override
    public String addAllSpots(List<TouristSpot> spots) {
        return touristSpotService.addAllSpots(spots);
    }

    @Override
    public List<Participant> getAllParticipant() {
        return (List<Participant>) participantService.getAllParticipants();
    }

    @Override
    public List<Participant> getAllBusyParticipants() {
        return participantRepository.findAllByParticipantStatus(UserStatus.Busy);
    }

    @Override
    public List<Participant> getAllFreeParticipants() {
        return participantRepository.findAllByParticipantStatus(UserStatus.Free);
    }

    @Override
    public List<Participant> getAllParticipantByGroupId(Integer groupId) {
        return participantService.getAllParticipantsByGroupId(groupId);
    }

    @Override
    public List<Group> getAllGroup() {
        return groupService.getAllGroups();
    }

    @Override
    public List<Group> getAllActiveGroups() {
        return groupRepository.findAllByGroupStatus(GroupStatus.Active);
    }

    @Override
    public List<Group> getAllInActiveGroups() {
        return groupRepository.findAllByGroupStatus(GroupStatus.InActive);
    }

    @Override
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @Override
    public List<Event> getAllEvent() {
        return eventService.getAllEvents();
    }

    @Override
    public List<Event> getAllInActiveEvents() {
        return eventRepository.findAllByEventStatus(EventStatus.InActive);
    }

    @Override
    public List<Event> getAllActiveEvents() {
        return eventRepository.findAllByEventStatus(EventStatus.Active);
    }

    @Override
    public List<TouristSpot> getAllSpot() {
        return touristSpotService.getAllSpots();
    }

    @Override
    public List<Organizer> getAllOrganizer() {
        return organizerService.getAllOrganizer();
    }

    @Override
    public List<Organizer> getAllBusyOrganizers() {
        return organizerRepository.findAllByOrganizerStatus(UserStatus.Busy);
    }

    @Override
    public List<Organizer> getAllFreeOrganizers() {
        return organizerRepository.findAllByOrganizerStatus(UserStatus.Free);
    }

    @Override
    public String removeUserById(Integer userId) {
        return userService.removeUserById(userId);
    }
    @Override
    public String removeParticipantById(Integer participantId) {
        return participantService.removeParticipantById(participantId);
    }

    @Override
    public String removeGroupById(Integer groupId) {
        return groupService.removeGroupById(groupId);
    }
    @Override
    public String removeEventById(Integer eventId) {
        return eventService.deleteEventById(eventId);
    }
    @Override
    public String removeTouristSpotById(Integer spotId) {
        return touristSpotService.removeSpotById(spotId);
    }
    @Override
    public String removeOrganizerById(Integer organizerId) {
        return organizerService.removeOrganizerById(organizerId);
    }

    @Override
    public String removeAllInActiveEvents() {
        eventRepository.deleteAll(getAllInActiveEvents());
        return "InActive Events removed successfully";
    }

    @Override
    public User getUserById(Integer userId) {
        return userService.getUserById(userId);
    }
    @Override
    public Participant getParticipantById(Integer participantId) {
        return participantService.getParticipantById(participantId);
    }
    @Override
    public Group getGroupById(Integer groupId) {
        return groupService.getGroupById(groupId);
    }
    @Override
    public Event getEventById(Integer eventId) {
        return eventService.getEventById(eventId);
    }
    @Override
    public TouristSpot getSpotById(Integer spotId) {
        return touristSpotService.getSpotById(spotId);
    }
    @Override
    public Organizer getOrganizerById(Integer organizerId) {
        return organizerService.getOrganizerById(organizerId);
    }
}
