package com.meshalkina.restaurant_competition.repository;

import com.meshalkina.restaurant_competition.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
