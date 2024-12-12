package com.taskflow.service3.repository;

import com.taskflow.service3.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Integer> {
}
