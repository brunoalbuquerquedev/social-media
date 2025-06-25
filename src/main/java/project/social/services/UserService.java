package project.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.social.domain.User;
import project.social.repositories.UserRepository;
import project.social.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        User obj = findById(user.getId());
        obj.updateData(user);
        return userRepository.save(obj);
    }

    public void delete(User user) {
        Optional<User> optionalUser = Optional.of(findById(user.getId()));
        userRepository.delete(optionalUser.get());
    }

    public void deleteById(String id) {
        Optional<User> user = Optional.of(findById(id));
        userRepository.deleteById(id);
    }

    public User followUser(String followerId, String followingId) {
        if (followerId.equals(followingId))
            throw new IllegalArgumentException("You cannot follow yourself.");

        User follower = findById(followerId);
        User following = findById(followingId);

        if (!follower.getFollowing().contains(followingId))
            follower.getFollowing().add(followingId);

        if (!following.getFollowers().contains(followerId))
            following.getFollowers().add(followerId);

        userRepository.save(follower);
        return userRepository.save(following);
    }

    public User unfollowUser(String followerId, String unfollowingId) {
        if (followerId.equals(unfollowingId))
            throw new IllegalArgumentException("You cannot unfollow yourself.");

        User follower = findById(followerId);
        User unfollowing = findById(unfollowingId);

        follower.getFollowers().remove(unfollowingId);
        unfollowing.getFollowers().remove(followerId);

        userRepository.save(follower);
        return userRepository.save(unfollowing);
    }
}
