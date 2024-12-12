package com.taskflow.service3.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamInfoDTO {
    String teamName;
    List<UserDetailsDTO> userDetailsDTOList;
}
