package com.taskflow.service3.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamRequestDTO {
    Integer teamId;
    List<Integer> teamMembersIds;
    public TeamRequestDTO(
            Integer teamId,
            List<Integer> teamMembersIds
    ){
        this.teamId = teamId;
        this.teamMembersIds = teamMembersIds;
    }
}
