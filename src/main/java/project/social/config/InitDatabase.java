package project.social.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.social.domain.*;
import project.social.repositories.*;

import java.util.*;

@Configuration
public class InitDatabase {

    @Bean
    CommandLineRunner init(UserRepository userRepository,
                           PostRepository postRepository,
                           FollowRepository followRepository,
                           LikeRepository likeRepository,
                           CommentRepository commentRepository) {
        return args -> {

            userRepository.deleteAll();
            postRepository.deleteAll();
            followRepository.deleteAll();
            likeRepository.deleteAll();
            commentRepository.deleteAll();

            User user1 = new User(null, "bruno", "bruno@gmail.com", "123456", "Bruno Freire", "Amante de tecnologia", "https://picsum.photos/200", "São Paulo", new Date(95, 5, 10), new Date(), true, new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user2 = new User(null, "ana", "ana@gmail.com", "123456", "Ana Souza", "Fotógrafa de viagens", "https://picsum.photos/201", "Rio de Janeiro", new Date(90, 8, 20), new Date(), true, new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user3 = new User(null, "carlos", "carlos@gmail.com", "123456", "Carlos Lima", "Apaixonado por esportes", "https://picsum.photos/202", "Belo Horizonte", new Date(88, 2, 15), new Date(), true, new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user4 = new User(null, "juliana", "juliana@gmail.com", "123456", "Juliana Ribeiro", "Chef de cozinha", "https://picsum.photos/203", "Curitiba", new Date(92, 11, 5), new Date(), true, new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user5 = new User(null, "rodrigo", "rodrigo@gmail.com", "123456", "Rodrigo Martins", "Viciado em games", "https://picsum.photos/204", "Salvador", new Date(94, 3, 12), new Date(), true, new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user6 = new User(null, "fernanda", "fernanda@gmail.com", "123456", "Fernanda Lopes", "Leitora voraz", "https://picsum.photos/205", "Fortaleza", new Date(91, 7, 19), new Date(), true, new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user7 = new User(null, "lucas", "lucas@gmail.com", "123456", "Lucas Almeida", "Músico", "https://picsum.photos/206", "Brasília", new Date(93, 10, 25), new Date(), true, new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user8 = new User(null, "marina", "marina@gmail.com", "123456", "Marina Castro", "Aventureira", "https://picsum.photos/207", "Florianópolis", new Date(96, 1, 30), new Date(), true, new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user9 = new User(null, "pedro", "pedro@gmail.com", "123456", "Pedro Henrique", "Cinéfilo", "https://picsum.photos/208", "Recife", new Date(87, 4, 18), new Date(), true, new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user10 = new User(null, "patricia", "patricia@gmail.com", "123456", "Patricia Silva", "Apaixonada por moda", "https://picsum.photos/209", "Porto Alegre", new Date(95, 6, 2), new Date(), true, new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            List<User> savedUsers = userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));

            user1 = savedUsers.get(0);
            user2 = savedUsers.get(1);
            user3 = savedUsers.get(2);
            user4 = savedUsers.get(3);
            user5 = savedUsers.get(4);
            user6 = savedUsers.get(5);
            user7 = savedUsers.get(6);
            user8 = savedUsers.get(7);
            user9 = savedUsers.get(8);
            user10 = savedUsers.get(9);

            user1.setFollowing(Arrays.asList(user2.getId(), user3.getId()));
            user2.setFollowers(Arrays.asList(user1.getId()));

            user2.setFollowing(Arrays.asList(user3.getId(), user4.getId()));
            user3.setFollowers(Arrays.asList(user1.getId(), user2.getId()));

            user3.setFollowing(Arrays.asList(user1.getId(), user5.getId()));
            user4.setFollowers(Arrays.asList(user2.getId()));

            user4.setFollowing(Arrays.asList(user2.getId(), user6.getId()));
            user5.setFollowers(Arrays.asList(user3.getId()));

            user5.setFollowing(Arrays.asList(user1.getId(), user4.getId()));
            user6.setFollowers(Arrays.asList(user4.getId()));

            user6.setFollowing(Arrays.asList(user2.getId(), user7.getId()));
            user7.setFollowers(Arrays.asList(user6.getId()));

            user7.setFollowing(Arrays.asList(user3.getId(), user8.getId()));
            user8.setFollowers(Arrays.asList(user7.getId()));

            user8.setFollowing(Arrays.asList(user5.getId(), user9.getId()));
            user9.setFollowers(Arrays.asList(user8.getId()));

            user9.setFollowing(Arrays.asList(user6.getId(), user10.getId()));
            user10.setFollowers(Arrays.asList(user9.getId()));

            user10.setFollowing(Arrays.asList(user1.getId(), user7.getId()));
            user1.getFollowers().addAll(Arrays.asList(user3.getId(), user5.getId(), user10.getId()));

            userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));

            Optional<User> optionalUser1 = userRepository.findByUsername(user1.getUsername());
            Optional<User> optionalUser2 = userRepository.findByUsername(user2.getUsername());
            Optional<User> optionalUser3 = userRepository.findByUsername(user3.getUsername());
            Optional<User> optionalUser4 = userRepository.findByUsername(user4.getUsername());
            Optional<User> optionalUser5 = userRepository.findByUsername(user5.getUsername());
            Optional<User> optionalUser6 = userRepository.findByUsername(user6.getUsername());
            Optional<User> optionalUser7 = userRepository.findByUsername(user7.getUsername());
            Optional<User> optionalUser8 = userRepository.findByUsername(user8.getUsername());
            Optional<User> optionalUser9 = userRepository.findByUsername(user9.getUsername());
            Optional<User> optionalUser10 = userRepository.findByUsername(user10.getUsername());

            user1.setFollowing(List.of(optionalUser2.get().getId(), optionalUser3.get().getId()));
            user2.setFollowing(List.of(optionalUser3.get().getId(), optionalUser4.get().getId()));
            user3.setFollowing(List.of(optionalUser1.get().getId(), optionalUser5.get().getId()));
            user4.setFollowing(List.of(optionalUser2.get().getId(), optionalUser6.get().getId()));
            user5.setFollowing(List.of(optionalUser1.get().getId(), optionalUser4.get().getId()));
            user6.setFollowing(List.of(optionalUser2.get().getId(), optionalUser7.get().getId()));
            user7.setFollowing(List.of(optionalUser3.get().getId(), optionalUser8.get().getId()));
            user8.setFollowing(List.of(optionalUser5.get().getId(), optionalUser9.get().getId()));
            user9.setFollowing(List.of(optionalUser6.get().getId(), optionalUser10.get().getId()));
            user10.setFollowing(List.of(optionalUser1.get().getId(), optionalUser7.get().getId()));

            user2.setFollowers(List.of(optionalUser1.get().getId(), optionalUser4.get().getId(), optionalUser6.get().getId()));
            user3.setFollowers(List.of(optionalUser1.get().getId(), optionalUser2.get().getId(), optionalUser7.get().getId()));
            user1.setFollowers(List.of(optionalUser3.get().getId(), optionalUser5.get().getId(), optionalUser10.get().getId()));
            user4.setFollowers(List.of(optionalUser2.get().getId(), optionalUser5.get().getId()));
            user5.setFollowers(List.of(optionalUser3.get().getId(), optionalUser8.get().getId()));
            user6.setFollowers(List.of(optionalUser4.get().getId(), optionalUser9.get().getId()));
            user7.setFollowers(List.of(optionalUser6.get().getId(), optionalUser10.get().getId()));
            user8.setFollowers(List.of(optionalUser7.get().getId()));
            user9.setFollowers(List.of(optionalUser8.get().getId()));
            user10.setFollowers(List.of(optionalUser9.get().getId()));

            userRepository.deleteAll();
            userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));

            Post post1 = new Post(null, "bruno", optionalUser1.get().getId(), "Dia produtivo de estudo em Java!", "Estudos", new Date(), List.of(), List.of(), false);
            Post post2 = new Post(null, "ana", optionalUser2.get().getId(), "Foto incrível da viagem à praia.", "Viagem", new Date(), List.of(), List.of(), true);
            Post post3 = new Post(null, "carlos", optionalUser3.get().getId(), "Treino concluído: 15km hoje!", "Corrida", new Date(), List.of(), List.of(), false);
            Post post4 = new Post(null, "juliana", optionalUser4.get().getId(), "Nova receita de bolo postada!", "Culinária", new Date(), List.of(), List.of(), true);
            Post post5 = new Post(null, "rodrigo", optionalUser5.get().getId(), "Finalizei The Last of Us 2! Emocionante.", "Games", new Date(), List.of(), List.of(), false);
            Post post6 = new Post(null, "fernanda", optionalUser6.get().getId(), "Começando um novo livro de suspense.", "Leitura", new Date(), List.of(), List.of(), true);
            Post post7 = new Post(null, "lucas", optionalUser7.get().getId(), "Nova música autoral saindo em breve.", "Música", new Date(), List.of(), List.of(), true);
            Post post8 = new Post(null, "marina", optionalUser8.get().getId(), "Acabei de voltar de um mochilão pelo sul!", "Viagem", new Date(), List.of(), List.of(), false);
            Post post9 = new Post(null, "pedro", optionalUser9.get().getId(), "Assisti a um clássico do cinema ontem.", "Filmes", new Date(), List.of(), List.of(), true);
            Post post10 = new Post(null, "patricia", optionalUser10.get().getId(), "Nova coleção de moda outono-inverno!", "Moda", new Date(), List.of(), List.of(), false);

            postRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5, post6, post7, post8, post9, post10));

            Like like1 = new Like(null, "ana", optionalUser1.get().getId(), post1.getId(), new Date().toString());
            Like like2 = new Like(null, "carlos", optionalUser2.get().getId(), post1.getId(), new Date().toString());
            Like like3 = new Like(null, "bruno", optionalUser3.get().getId(), post2.getId(), new Date().toString());
            Like like4 = new Like(null, "juliana", optionalUser4.get().getId(), post5.getId(), new Date().toString());
            Like like5 = new Like(null, "rodrigo", optionalUser5.get().getId(), post3.getId(), new Date().toString());
            Like like6 = new Like(null, "fernanda", optionalUser6.get().getId(), post4.getId(), new Date().toString());
            Like like7 = new Like(null, "lucas", optionalUser7.get().getId(), post6.getId(), new Date().toString());
            Like like8 = new Like(null, "marina", optionalUser8.get().getId(), post7.getId(), new Date().toString());
            Like like9 = new Like(null, "pedro", optionalUser9.get().getId(), post8.getId(), new Date().toString());
            Like like10 = new Like(null, "patricia", optionalUser10.get().getId(), post9.getId(), new Date().toString());

            likeRepository.saveAll(Arrays.asList(like1, like2, like3, like4, like5, like6, like7, like8, like9, like10));


            Optional<Post> optionalPost1 = postRepository.findByAuthorUsername(post1.getAuthorUsername());
            Optional<Post> optionalPost2 = postRepository.findByAuthorUsername(post2.getAuthorUsername());
            Optional<Post> optionalPost3 = postRepository.findByAuthorUsername(post3.getAuthorUsername());
            Optional<Post> optionalPost4 = postRepository.findByAuthorUsername(post4.getAuthorUsername());
            Optional<Post> optionalPost5 = postRepository.findByAuthorUsername(post5.getAuthorUsername());
            Optional<Post> optionalPost6 = postRepository.findByAuthorUsername(post6.getAuthorUsername());
            Optional<Post> optionalPost7 = postRepository.findByAuthorUsername(post7.getAuthorUsername());
            Optional<Post> optionalPost8 = postRepository.findByAuthorUsername(post8.getAuthorUsername());
            Optional<Post> optionalPost9 = postRepository.findByAuthorUsername(post9.getAuthorUsername());
            Optional<Post> optionalPost10 = postRepository.findByAuthorUsername(post10.getAuthorUsername());

            Comment comment1 = new Comment(null, optionalPost1.get().getId(), optionalUser2.get().getId(), "Parabéns pelo post, Bruno!", new Date());
            Comment comment2 = new Comment(null, optionalPost2.get().getId(), optionalUser1.get().getId(), "Lugar incrível, Ana!", new Date());
            Comment comment3 = new Comment(null, optionalPost3.get().getId(), optionalUser4.get().getId(), "Mandou bem no treino, Carlos!", new Date());
            Comment comment4 = new Comment(null, optionalPost4.get().getId(), optionalUser5.get().getId(), "Vou tentar essa receita!", new Date());
            Comment comment5 = new Comment(null, optionalPost5.get().getId(), optionalUser6.get().getId(), "A história é emocionante, né?", new Date());
            Comment comment6 = new Comment(null, optionalPost6.get().getId(), optionalUser7.get().getId(), "Qual o nome do livro?", new Date());
            Comment comment7 = new Comment(null, optionalPost7.get().getId(), optionalUser8.get().getId(), "Ansiosa pra ouvir!", new Date());
            Comment comment8 = new Comment(null, optionalPost8.get().getId(), optionalUser9.get().getId(), "Que viagem top!", new Date());
            Comment comment9 = new Comment(null, optionalPost9.get().getId(), optionalUser10.get().getId(), "Adoro esse filme também!", new Date());
            Comment comment10 = new Comment(null, optionalPost10.get().getId(), optionalUser1.get().getId(), "A coleção tá linda!", new Date());

            commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3, comment4, comment5, comment6,
                    comment7, comment8, comment9, comment10));

            Follow follow1 = new Follow(null, optionalUser2.get().getId(), user1.getId(), new Date());
            Follow follow2 = new Follow(null, optionalUser3.get().getId(), user1.getId(), new Date());
            Follow follow3 = new Follow(null, optionalUser4.get().getId(), user2.getId(), new Date());
            Follow follow4 = new Follow(null, optionalUser5.get().getId(), user3.getId(), new Date());
            Follow follow5 = new Follow(null, optionalUser6.get().getId(), user4.getId(), new Date());
            Follow follow6 = new Follow(null, optionalUser7.get().getId(), user5.getId(), new Date());
            Follow follow7 = new Follow(null, optionalUser8.get().getId(), user6.getId(), new Date());
            Follow follow8 = new Follow(null, optionalUser9.get().getId(), user7.getId(), new Date());
            Follow follow9 = new Follow(null, optionalUser10.get().getId(), user8.getId(), new Date());
            Follow follow10 = new Follow(null, optionalUser1.get().getId(), user9.getId(), new Date());

            followRepository.saveAll(Arrays.asList(follow1, follow2, follow3, follow4, follow5, follow6,
                    follow7, follow8, follow9, follow10));
        };
    }
}
