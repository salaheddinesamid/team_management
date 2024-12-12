package com.taskflow.service3.controller;

import com.taskflow.service3.dto.TeamDTO;
import com.taskflow.service3.dto.TeamMemberDTO;
import com.taskflow.service3.dto.TeamRequestDTO;
import com.taskflow.service3.dto.UserDetailsDTO;
import com.taskflow.service3.model.Team;
import com.taskflow.service3.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @PostMapping("/new")
    public ResponseEntity<Object> createTeam(@RequestBody TeamDTO team){
        return teamService.createNewTeam(team);
    }

    @GetMapping("/get_team_members/{teamId}")
    public ResponseEntity<List<UserDetailsDTO>> getTeamMembers(@PathVariable Integer teamId){
        return teamService.GetTeamInformation(teamId);
    }

    @PostMapping("/add_team_member")
    public ResponseEntity<Object> addTeamMember(@RequestBody TeamMemberDTO teamMemberDTO){
        return teamService.addTeamMember(teamMemberDTO.getTeamId(),teamMemberDTO.getUserId());
    }

    @DeleteMapping("/delete_team/{teamId}")
    public ResponseEntity<Object> deleteTeam(@PathVariable Integer teamId){
        return teamService.deleteTeam(teamId);
    }
}

