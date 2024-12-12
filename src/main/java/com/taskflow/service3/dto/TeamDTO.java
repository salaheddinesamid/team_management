package com.taskflow.service3.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamDTO {

    Integer teamId;
    String teamName;
    List<Integer> teamMembersId;

    public TeamDTO(
            Integer teamId,
            String teamName,
            List<Integer> teamMembersId
    ){
        this.teamId = teamId;
        this.teamMembersId = teamMembersId;
        this.teamName = teamName;
    }
}
