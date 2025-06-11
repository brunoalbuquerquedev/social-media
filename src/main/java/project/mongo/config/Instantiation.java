package project.mongo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.mongo.domain.User;
import project.mongo.repository.UserRepository;

import java.util.Arrays;

@Component
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        try {
            User maria = new User(null, "Maria Brown", "maria.brown@gmail.com");
            User alex = new User(null, "Alex Green", "alex.green@gmail.com");
            User bob = new User(null, "Bob Grey", "bob@gmail.com");

            userRepository.deleteAll();
            userRepository.saveAll(Arrays.asList(maria, alex, bob));
        } catch (Exception e) {
            System.out.println("Error at Instantiation.");
        }
    }
}
