package com.meshalkina.restaurant_competition.service;

import com.meshalkina.restaurant_competition.model.*;
import com.meshalkina.restaurant_competition.repository.MealRepository;
import com.meshalkina.restaurant_competition.repository.RestaurantRepository;
import com.meshalkina.restaurant_competition.repository.UserRepository;
import com.meshalkina.restaurant_competition.repository.VoteRepository;
import com.meshalkina.restaurant_competition.util.TimeUtil;
import com.meshalkina.restaurant_competition.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Vote createVote(Long restaurant_id) {
        User currentUser = UserUtil.getCurrentUser();
        if (currentUser.getVote() != null) {
            return null;
        }
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).orElse(null);
        if (restaurant == null) {
            return null;
        }
        Vote vote = new Vote();
        vote.setRestaurant(restaurant);
        vote.setUser(currentUser);

        LocalDateTime dateTime = LocalDateTime.now();
        vote.setCreated(dateTime);
        vote.setUpdated(dateTime);

        Vote newVote = voteRepository.save(vote);

        log.info("IN createVote - added a vote from the user {} for the restaurant {}",
                currentUser.getUsername(), restaurant.getName());
        return newVote;
    }

    public List<Vote> getAllVotes() {
        List<Vote> result = voteRepository.findAll();

        log.info("IN getAllVotes - {} votes found", result.size());
        return result;
    }

    public Vote getVoteById(Long vote_id) {
        log.info("IN getVoteById - found a vote with id {}", vote_id);
        return voteRepository.findById(vote_id).orElse(null);
    }

    public Vote updateVote(Long restaurant_id) {
        User currentUser = UserUtil.getCurrentUser();
        Vote vote = currentUser.getVote();
        Vote fromDB = voteRepository.findById(vote.getId()).orElse(null);
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).orElse(null);

        LocalTime now = LocalTime.now();
        LocalTime timeLimit = LocalTime.parse(TimeUtil.TIME_LIMIT);

        if (now.isAfter(timeLimit)) {
            return vote;
        }

        vote.setCreated(fromDB.getCreated());
        vote.setUpdated(LocalDateTime.now());

        vote.setRestaurant(restaurant);
        voteRepository.save(vote);

        log.info("IN updateVote - the vote with id {} has been changed", vote.getId());
        return vote;
    }

    public void deleteVote(Long vote_id) {
        User currentUser = UserUtil.getCurrentUser();
        Vote vote = getVoteById(vote_id);
        if (currentUser == vote.getUser()) {
            voteRepository.deleteById(vote_id);
            log.info("IN deleteVote - the vote with id {} has been deleted", vote_id);
        } else if (currentUser.getRole() == Role.ADMIN) {
            voteRepository.deleteById(vote_id);
            log.info("IN deleteVote - the vote with id {} has been deleted", vote_id);
        }
    }
}
