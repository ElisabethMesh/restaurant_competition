package com.meshalkina.restaurant_competition.service;

import com.meshalkina.restaurant_competition.model.Restaurant;
import com.meshalkina.restaurant_competition.model.Role;
import com.meshalkina.restaurant_competition.model.User;
import com.meshalkina.restaurant_competition.model.Vote;
import com.meshalkina.restaurant_competition.repository.RestaurantRepository;
import com.meshalkina.restaurant_competition.repository.VoteRepository;
import com.meshalkina.restaurant_competition.util.TimeUtil;
import com.meshalkina.restaurant_competition.util.UserUtil;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@EnableAspectJAutoProxy
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

        return voteRepository.save(vote);
    }

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public Vote getVoteById(Long vote_id) {
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

        return vote;
    }

    public void deleteVote(Long vote_id) {
        User currentUser = UserUtil.getCurrentUser();
        Vote vote = getVoteById(vote_id);
        if (currentUser == vote.getUser()) {
            voteRepository.deleteById(vote_id);
        } else if (currentUser.getRole() == Role.ADMIN) {
            voteRepository.deleteById(vote_id);
        }
    }
}
