package com.example.demo.Controller;


import com.example.demo.Model.*;
import com.example.demo.Service.Event.EventService;
import com.example.demo.Service.Event.EventServiceImpl;
import com.example.demo.Service.GroupServices.GroupService;
import com.example.demo.Service.StorageService;
import com.example.demo.Service.TouristSpot.TouristSpotService;
import com.example.demo.Service.TouristSpot.TouristSpotServiceImpl;
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
public class EventSpotController {
    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private GroupService groupService;

    @Autowired
    private TouristSpotServiceImpl spotService;



    @GetMapping("/activeEvents")
    public List<Event> getAllActiveEvents(){
        return eventService.getAllActiveEvents();
    }
    @GetMapping("/activeEvent/{eventName}")
    public ResponseEntity<Event> getEventByName(@PathVariable String eventName){
        return eventService.getEventByEventName(eventName);
    }
    @GetMapping("/activeEvents/{id}")
    public Event getEventById(@PathVariable Integer id){
        return eventService.getEventById(id);
    }
    @GetMapping("/event/group/{eventName}")
    public List<Group> getGrpByEventName(@PathVariable String eventName){
        return groupService.getAllGroupByEventName(eventName);
    }
    @GetMapping("/event/pictureList/{eventId}")
    public List<String> getAllEventPictureById(@PathVariable Integer eventId){
        return eventService.getAllPicturesByEventId(eventId);
    }

    @PostMapping("/updateEventPicture")
    public void updateEventPicture(@RequestParam(value = "eventId") Integer eventId, @RequestParam(value = "picture") MultipartFile eventPicture) throws IOException {
        eventService.addEventPicture(eventId,eventPicture);
    }
    @PostMapping("/updateSpotPicture")
    public void updateSpotPicture(@RequestParam(value = "spotId") Integer spotId, @RequestParam(value = "picture") MultipartFile spotPicture) throws IOException {
        spotService.addSpotPictures(spotId,spotPicture);
    }
    @GetMapping("/spots")
    public List<TouristSpot> getAllSpots(){
        return spotService.getAllSpots();
    }
    @GetMapping("/spots/{id}")
    public TouristSpot getSpotById(@PathVariable Integer id){
        return spotService.getSpotById(id);
    }
    @GetMapping("/spot/{spotName}")
    public ResponseEntity<TouristSpot> getSpotBySpotName(@PathVariable String spotName){
        return spotService.getSpotBySpotName(spotName);
    }
    @GetMapping("/spot/pictureList/{spotId}")
    public List<String> getAllPicturesBySpotId(@PathVariable Integer spotId){
        return spotService.getSpotPictureById(spotId);
    }
    @GetMapping("/spot/group/{spotName}")
    public List<Group> getGrpBySpotName(@PathVariable String spotName){
        return groupService.getAllGroupBySpotName(spotName);
    }
    @GetMapping("/PopularSpots")
     public List<TouristSpot> getAllPopularSpots(){
        return spotService.getAllPopularTouristSpot();
    }
    @GetMapping("/PopularEvents")
    public List<Event> getAllPopularEvents(){
        return eventService.getAllPopularEvents();
    }

}
