package com.moing.bo.backend.domain.team.domain.repository;

import com.moing.bo.backend.domain.team.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamCustomRepository {
}
