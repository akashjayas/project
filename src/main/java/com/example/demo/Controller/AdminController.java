package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.Admin.AdminService;
import com.example.demo.Service.Event.EventService;
import com.example.demo.Service.Organizer.OrganizerService;
import com.example.demo.Service.TouristSpot.TouristSpotService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/manyUsers")
    public String manyUsers(@RequestBody List<User> users){return adminService.addManyUsers(users);}
    
    @PostMapping("/events")
    public String addEvent(@RequestBody Event newEvent){
        return adminService.addEvent(newEvent);
    }
    @PostMapping("/manyEvents")
    public String addManyEvents(@RequestBody List<Event> events){
        return adminService.addAllEvents(events);
    }
    @PostMapping("/touristSpot")
    public String addTouristSpot(@RequestBody TouristSpot newSpot){
        return adminService.addSpot(newSpot);
    }
    @PostMapping("/manySpots")
    public String addManySpots(@RequestBody List<TouristSpot> spots){return adminService.addAllSpots(spots);}

    @GetMapping("/participants")
    public List<Participant> getAllParticipants(){
        return adminService.getAllParticipant();
    }
    @GetMapping("/groupParticipants/{groupId}")
    public List<Participant> getAllParticipantsByGroupId(@PathVariable Integer groupId){
        return adminService.getAllParticipantByGroupId(groupId);
    }
    @GetMapping("/organizers")
    public List<Organizer> getAllOrganizers(){return adminService.getAllOrganizer();}
    @GetMapping("/organizers/{organizerId}")
    public Organizer getOrganizerById(@PathVariable Integer organizerId){
        return adminService.getOrganizerById(organizerId);
    }
    @GetMapping("/busyOrganizers")
    public List<Organizer> getAllBusyOrganizer(){
        return adminService.getAllBusyOrganizers();
    }
    @GetMapping("/freeOrganizers")
    public List<Organizer> getAllFreeOrganizer(){
        return adminService.getAllFreeOrganizers();
    }
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return adminService.getAllUser();
    }
    @GetMapping("/groups")
    public List<Group> getAllGroup(){
        return adminService.getAllGroup();
    }
    @GetMapping("/events")
    public List<Event> getAllEvent(){
        return adminService.getAllEvent();
    }
    @GetMapping("/touristSpots")
    public List<TouristSpot> getAllTouristSpot(){return adminService.getAllSpot();}
    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Integer userId){
        return adminService.getUserById(userId);
    }
    @GetMapping("/participants/{participantId}")
    public Participant getParticipantById(@PathVariable Integer participantId){
        return adminService.getParticipantById(participantId);
    }
    @GetMapping("/BusyParticipants")
    public List<Participant> getAllBusyParticipants(){
        return adminService.getAllBusyParticipants();
    }
    @GetMapping("/FreeParticipants")
    public List<Participant> getAllFreeParticipants(){
        return adminService.getAllFreeParticipants();
    }
    @GetMapping("/groups/{groupId}")
    public Group getGroupById(@PathVariable Integer groupId){
        return adminService.getGroupById(groupId);
    }
    @GetMapping("/ActiveGroups")
    public List<Group> getAllActiveGroups(){
        return adminService.getAllActiveGroups();
    }
    @GetMapping("/InActiveGroups")
    public List<Group> getAllInActiveGroups(){
        return adminService.getAllInActiveGroups();
    }
    @GetMapping("/events/{eventId}")
    public Event getEventById(@PathVariable Integer eventId){
        return adminService.getEventById(eventId);
    }
    @GetMapping("/inActiveEvents")
    public List<Event> getAllInActiveEvents(){
        return adminService.getAllInActiveEvents();
    }
    @GetMapping("/ActiveEvents")
    public List<Event> getAllActiveEvents(){
        return adminService.getAllActiveEvents();
    }
    @GetMapping("/touristSpots/{spotId}")
    public TouristSpot getSpotById(@PathVariable Integer spotId){
        return adminService.getSpotById(spotId);
    }

    @DeleteMapping("/users/{userId}")
    public String removeUser(@PathVariable Integer userId){
        return adminService.removeUserById(userId);
    }


    @DeleteMapping("/participants/{participantId}")
    public String removeParticipant(@PathVariable Integer participantId){
        return adminService.removeParticipantById(participantId);
    }
    @DeleteMapping("/groups/{groupId}")
    public String removeGroup(@PathVariable Integer groupId){
        return adminService.removeGroupById(groupId);
    }
    @DeleteMapping("/events/{eventId}")
    public String removeEvent(@PathVariable Integer eventId){
        return adminService.removeEventById(eventId);
    }
    @DeleteMapping("/inActiveEvents")
    public String removeInActiveEvents(){
        return adminService.removeAllInActiveEvents();
    }
    @DeleteMapping("/touristSpots/{spotId}")
    public String removeTouristSpot(@PathVariable Integer spotId){
        return adminService.removeTouristSpotById(spotId);
    }
    @DeleteMapping("/organizers/{organizerId}")
    public String removeOrganizer(@PathVariable Integer organizerId) {
    	return adminService.removeOrganizerById(organizerId);
    }
}
