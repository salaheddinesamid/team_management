package com.taskflow.service3.service;


import com.taskflow.service3.dto.TeamDTO;
import com.taskflow.service3.dto.TeamRequestDTO;
import com.taskflow.service3.dto.UserDetailsDTO;
import com.taskflow.service3.model.Team;
import com.taskflow.service3.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private RestTemplate restTemplate;

    private final TeamRepository teamRepository;


    public TeamService(TeamRepository teamRepository, RestTemplate restTemplate) {
        this.teamRepository = teamRepository;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> createNewTeam(TeamDTO teamDTO) {
        List<Integer> memberIds = teamDTO.getTeamMembersId();
        String url = "http://localhost:8080/api/user/validate_users";

        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Set the Content-Type to JSON

        // Wrap the headers and body into an HttpEntity
        HttpEntity<List<Integer>> requestEntity = new HttpEntity<>(memberIds, headers);

        // Send the POST request
        ResponseEntity<Boolean[]> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Boolean[].class
        );


        // Check the response
        response.getBody();
        if (!Arrays.asList(response.getBody()).contains(true)) {
            return new ResponseEntity<>("Some user IDs are invalid or do not exist.", HttpStatus.BAD_REQUEST);
        }
        // Check if the provided user IDs exist

        // Create a new Team instance
        Team newTeam = new Team();
        newTeam.setTeamName(teamDTO.getTeamName());
        newTeam.setTeamMembersId(memberIds);

        // Save the new Team entity
        Team savedTeam = teamRepository.save(newTeam);

        // Return the saved Team object with HTTP 201 status
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    // Add team member
    public ResponseEntity<Object> addTeamMember(Integer teamId, Integer userId){
        Team team = teamRepository.findById(teamId).get();
        List<Integer> teamMembers = team.getTeamMembersId();
        if(!teamMembers.contains(userId)){
            teamMembers.add(userId);
            team.setTeamMembersId(teamMembers);
            teamRepository.save(team);
        }
        return new ResponseEntity<>("TEAM MEMBER HAS BEEN ADDED",HttpStatus.OK);
    }

    // Get team information:
    public ResponseEntity<List<UserDetailsDTO>> GetTeamInformation(Integer teamId){
        Optional<Team> team  =teamRepository.findById(teamId);
        List<Integer> teamMembers = team.get().getTeamMembersId();

        String url = "http://localhost:8080/api/user/get_team_members";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Integer>> request = new HttpEntity<>(teamMembers, headers);
        ResponseEntity<List<UserDetailsDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.POST, // Ensure the method matches the server
                request,
                new ParameterizedTypeReference<List<UserDetailsDTO>>() {}
        );
        return new ResponseEntity<>(response.getBody(),HttpStatus.OK);
    }

    // Delete team:
    public ResponseEntity<Object> deleteTeam(Integer teamId){
        if(teamRepository.existsById(teamId)){
            teamRepository.deleteById(teamId);
            return new ResponseEntity<>("TEAM IS DELETED",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("TEAM NOT FOUND",HttpStatus.NOT_FOUND);
        }
    }

}
