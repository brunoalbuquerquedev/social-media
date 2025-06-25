package project.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.social.domain.Post;
import project.social.repositories.PostRepository;
import project.social.services.exceptions.ObjectNotFoundException;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post not found."));
    }

    public List<Post> findByTitle(String text) {
        return postRepository.findByTitleContainingIgnoreCase(text);
    }
}
