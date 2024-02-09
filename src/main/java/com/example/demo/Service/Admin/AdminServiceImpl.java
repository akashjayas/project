package com.example.demo.Service.Admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.Event;
import com.example.demo.Model.EventStatus;
import com.example.demo.Model.Group;
import com.example.demo.Model.GroupStatus;
import com.example.demo.Model.Organizer;
import com.example.demo.Model.Participant;
import com.example.demo.Model.Role;
import com.example.demo.Model.TouristSpot;
import com.example.demo.Model.User;
import com.example.demo.Model.UserStatus;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Repository.GroupRepository;
import com.example.demo.Repository.OrganizerRepository;
import com.example.demo.Repository.ParticipantRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.Event.EventService;
import com.example.demo.Service.GroupServices.GroupService;
import com.example.demo.Service.Organizer.OrganizerService;
import com.example.demo.Service.ParticipantServices.ParticipantService;
import com.example.demo.Service.TouristSpot.TouristSpotService;
import com.example.demo.Service.UserServices.UserService;
import com.example.demo.config.CustomUserDetailsService;
import com.example.demo.config.JwtProvider;
import com.example.demo.Service.Admin.*;

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
    
    
	@Autowired
	private CustomUserDetailsService customerUserDetails;
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String addManyUsers(List<User> users) {
        userRepository.saveAll(users);
        return "Success";
    }

    @Override
    public ResponseEntity<String> addEvent(Event newEvent, MultipartFile file) throws IOException {
        return eventService.addEvent(newEvent,file);
    }

    @Override
    public String addAllEvents(List<Event> events) {
        return eventService.addAllEvents(events);
    }

    @Override
    public ResponseEntity<?> addSpot(TouristSpot spot, MultipartFile spotPicture ) throws IOException {
        return touristSpotService.addSpot(spot, spotPicture);
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
    public String removeAllUser() {
        userRepository.deleteAll();
        return "removed all users";
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
    public ResponseEntity<String> removeTouristSpotById(Integer spotId) {
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
//	@Override
//	public ResponseEntity<String> addadmin(User newUser) {
//		Optional<User> user = userRepository.findByUserEmail(newUser.getUserEmail());
//		if (user.isPresent()) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body("User mail already exists");
//		} else {
//			newUser.setRole(Role.Admin_Role);
//			userRepository.save(newUser);
//			return ResponseEntity.status(HttpStatus.CREATED).body("User with id: " + newUser.getUserId() + " is registered");
//		}
//	}
	@Override
	public User findUserByJwt(String jwt) {
		String email= JwtProvider.getEmailFromJwtToken(jwt);
		Optional<User> user=userRepository.findByUserEmail(email);
		return user.orElse(null);
		}
	
    @Override
    public AuthResponse addUser(User newUser) throws Exception {
        Optional<User> user = userRepository.findByUserEmail(newUser.getUserEmail());
        if (user.isPresent()) {
           throw new Exception("User is already exit");
        } else {
        	String temp = passwordEncoder.encode(newUser.getUserPassword());
			newUser.setUserPassword(temp);
            newUser.setRole(Role.Admin_Role);
            User saveduser = userRepository.save(newUser);
            Authentication authentication= new UsernamePasswordAuthenticationToken(saveduser.getUserEmail(), saveduser.getUserPassword());
            String token = JwtProvider.generateToken(authentication);

                AuthResponse res=new AuthResponse(token, "Register Success");
                return res;
            }
            
        }
    
    
	 @Override
	    public AuthResponse sigin(LoginRequest LoginRequest ) {
	    	Authentication authentication = authencate(LoginRequest.getEmail(), LoginRequest.getPassword());
	    	String token = JwtProvider.generateToken(authentication);
	    	AuthResponse res=new AuthResponse(token, "Login Sucess");
	    	
	    	return res;
	    }

	    private Authentication authencate(String email, String password) {
	    	UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
	    	if(userDetails==null) {
	    		throw new BadCredentialsException("Invalid Username");
	    	
	    	}
	    	if(!passwordEncoder.matches(password,  userDetails.getPassword())) {
	    		throw new BadCredentialsException("password not match");
	    	}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	    
    @Override
    public Organizer getOrganizerById(Integer organizerId) {
        return organizerService.getOrganizerById(organizerId);
    }

//	@Override
//	public ResponseEntity<String> addadmin(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
