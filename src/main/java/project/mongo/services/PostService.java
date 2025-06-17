package project.mongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.mongo.domain.Post;
import project.mongo.repository.PostRepository;
import project.mongo.services.exceptions.ObjectNotFoundException;

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
}
