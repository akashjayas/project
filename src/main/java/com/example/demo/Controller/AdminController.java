package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.Admin.AdminService;
import com.example.demo.Service.Event.EventService;
import com.example.demo.Service.GroupMessage.MessageService;
import com.example.demo.Service.Admin.*;
import com.example.demo.Service.StorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private EventService eventService;

    @PostMapping("/manyUsers")
    public String manyUsers(@RequestBody List<User> users){return adminService.addManyUsers(users);}
    @DeleteMapping("/removeAllUsers")
    public String removeAllUsers(){
        return adminService.removeAllUser();
    }
    @PostMapping("/events")
    public ResponseEntity<String> addEvent(@RequestParam(value = "newEvent") String newEventJson,@RequestParam(value = "eventPicture") MultipartFile file){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        System.out.println(newEventJson);
        try{
            Event newEvent=objectMapper.readValue(newEventJson,Event.class);

            return adminService.addEvent(newEvent,file);
        }catch(JsonProcessingException e){
            System.out.println(newEventJson);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest LoginRequest) {
		return adminService.sigin(LoginRequest);
	}
    @PostMapping("/manyEvents")
    public String addManyEvents(@RequestBody List<Event> events){
        return adminService.addAllEvents(events);
    }
    @PostMapping("/touristSpot")
    public ResponseEntity<?> addTouristSpot(@RequestParam(value = "newSpot") String newTouristSpot,@RequestParam(value = "spotPicture") MultipartFile spotSpicture){
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try{
            TouristSpot newSpot=objectMapper.readValue(newTouristSpot,TouristSpot.class);

            return adminService.addSpot(newSpot,spotSpicture);
        }catch(JsonProcessingException e){
            return ResponseEntity.badRequest().body("Entered newSpot values are not correct.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @PostMapping
	public AuthResponse addUser(@RequestBody User newUser) throws Exception {
		return adminService.addUser(newUser);
	}
    
    @PostMapping("/manySpots")
    public String addManySpots(@RequestBody List<TouristSpot> spots){return adminService.addAllSpots(spots);}
//    @PostMapping("/S3")
//    public ResponseEntity<String> uploadPhoto(@RequestParam(value = "file")MultipartFile file){
//
//        return new ResponseEntity<>(storageService.uploadFile(file), HttpStatus.OK);
//    }
//    @GetMapping("/S3/{fileName}")
//    public ResponseEntity<?> viewPhoto(@PathVariable String fileName){
//        byte [] data=storageService.viewFile(fileName);
//        ByteArrayResource byteArrayResource=new ByteArrayResource(data);
//        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(data);
//    }
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
    public ResponseEntity<String> removeTouristSpot(@PathVariable Integer spotId){
        return adminService.removeTouristSpotById(spotId);
    }
    @DeleteMapping("/organizers/{organizerId}")
    public String removeOrganizer(@PathVariable Integer organizerId) {
    	return adminService.removeOrganizerById(organizerId);
    }
//
//    @DeleteMapping("/S3/{fileName}")
//    public ResponseEntity<String> photoDelete(@PathVariable String fileName){
//        return new ResponseEntity<>(storageService.deleteFile(fileName),HttpStatus.OK);
//    }
}
