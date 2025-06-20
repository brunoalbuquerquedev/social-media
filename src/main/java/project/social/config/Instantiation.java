package project.social.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.social.domain.Post;
import project.social.domain.User;
import project.social.dto.AuthorDto;
import project.social.dto.CommentDto;
import project.social.repository.PostRepository;
import project.social.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Component
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            User maria = new User(null, "Maria Brown", "maria.brown@gmail.com");
            User alex = new User(null, "Alex Green", "alex.green@gmail.com");
            User bob = new User(null, "Bob Grey", "bob@gmail.com");

//            userRepository.deleteAll();
//            userRepository.saveAll(Arrays.asList(maria, alex, bob));

            Post post1 = new Post(null, sdf.parse("21/03/2018"),
                    "Partiu Viagem",
                    "Vou viajar para São Paulo. Abraços!",
                    new AuthorDto(maria)
            );

            Post post2 = new Post(null, sdf.parse("23/03/2018"),
                    "Bom dia",
                    "Acordei feliz hoje!",
                    new AuthorDto(maria)
            );

            CommentDto c1 = new CommentDto(
                    "Boa viagem mano!",
                    sdf.parse("21/03/2018"),
                    new AuthorDto(alex)
            );

            CommentDto c2 = new CommentDto(
                    "Aproveite!",
                    sdf.parse("22/03/2018"),
                    new AuthorDto(bob)
            );

            CommentDto c3 = new CommentDto(
                    "Tenha um ótimo dia!",
                    sdf.parse("23/03/2018"),
                    new AuthorDto(alex)
            );

//            post1.getComments().addAll(Arrays.asList(c1, c2));
//            post2.getComments().add(c3);
//
//            postRepository.deleteAll();
//            postRepository.saveAll(Arrays.asList(post1, post2));
//
//            maria.getPosts().addAll(Arrays.asList(post1, post2));
//
//            userRepository.save(maria);
        } catch (Exception e) {
            System.out.println("Error at Instantiation.");
        }
    }
}
