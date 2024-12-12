package com.taskflow.service3.dto;

import com.taskflow.service3.model.Team;
import lombok.Data;

@Data
public class TeamMemberDTO {

    Integer teamId;
    Integer userId;

    public TeamMemberDTO(
            Integer teamId,
            Integer userId
    ){
        this.teamId = teamId;
        this.userId = userId;
    }
}
