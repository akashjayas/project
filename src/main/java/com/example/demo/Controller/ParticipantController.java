package com.example.demo.Controller;

import com.example.demo.Model.Participant;
import com.example.demo.Service.ParticipantServices.ParticipantService;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Participant")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;
    @GetMapping("/group/{groupId}")
    public List<Participant> getAllParticipantsInGroup(@PathVariable Integer groupId){
        return participantService.getAllParticipantsByGroupId(groupId);
    }
    @GetMapping("/leaveGroupByParticipantId")
    public ResponseEntity<String> leaveGroupByParticipantId(@RequestParam(value = "participantId") Integer participantId,@RequestParam(value = "groupId") Integer groupId){
        return participantService.leaveGroupByParticipantId(participantId,groupId);
    }
    @PostMapping
    public ResponseEntity<String> addParticipant(@RequestBody Participant newParticipant){
        return participantService.addParticipant(newParticipant);
    }

}
