package project.mongo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.mongo.domain.Post;
import project.mongo.domain.User;
import project.mongo.dto.AuthorDto;
import project.mongo.repository.PostRepository;
import project.mongo.repository.UserRepository;

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

            userRepository.deleteAll();
            userRepository.saveAll(Arrays.asList(maria, alex, bob));

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

            postRepository.saveAll(Arrays.asList(post1, post2));

        } catch (Exception e) {
            System.out.println("Error at Instantiation.");
        }
    }
}
