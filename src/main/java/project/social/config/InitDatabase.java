package project.social.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.social.domain.*;
import project.social.domain.enums.FollowStatus;
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

            User user1 = new User.Builder()
                    .username("bruno").email("bruno@gmail.com").password("123456")
                    .fullName("Bruno Freire").bio("Amante de tecnologia")
                    .profilePictureUrl("https://picsum.photos/200").location("São Paulo")
                    .birthDate(new Date(95, 5, 10)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followingIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>())
                    .build();

            User user2 = new User.Builder()
                    .username("ana").email("ana@gmail.com").password("123456")
                    .fullName("Ana Souza").bio("Fotógrafa de viagens")
                    .profilePictureUrl("https://picsum.photos/201").location("Rio de Janeiro")
                    .birthDate(new Date(90, 8, 20)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followingIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>())
                    .build();

            User user3 = new User.Builder().username("carlos").email("carlos@gmail.com").password("123456")
                    .fullName("Carlos Lima").bio("Apaixonado por esportes")
                    .profilePictureUrl("https://picsum.photos/202").location("Belo Horizonte")
                    .birthDate(new Date(88, 2, 15)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followingIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user4 = new User.Builder().username("juliana").email("juliana@gmail.com").password("123456")
                    .fullName("Juliana Ribeiro").bio("Chef de cozinha")
                    .profilePictureUrl("https://picsum.photos/203").location("Curitiba")
                    .birthDate(new Date(92, 11, 5)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followingIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user5 = new User.Builder().username("rodrigo").email("rodrigo@gmail.com").password("123456")
                    .fullName("Rodrigo Martins").bio("Viciado em games")
                    .profilePictureUrl("https://picsum.photos/204").location("Salvador")
                    .birthDate(new Date(94, 3, 12)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followingIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user6 = new User.Builder().username("fernanda").email("fernanda@gmail.com").password("123456")
                    .fullName("Fernanda Lopes").bio("Leitora voraz")
                    .profilePictureUrl("https://picsum.photos/205").location("Fortaleza")
                    .birthDate(new Date(91, 7, 19)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followingIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user7 = new User.Builder().username("lucas").email("lucas@gmail.com").password("123456")
                    .fullName("Lucas Almeida").bio("Músico")
                    .profilePictureUrl("https://picsum.photos/206").location("Brasília")
                    .birthDate(new Date(93, 10, 25)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followingIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user8 = new User.Builder().username("marina").email("marina@gmail.com").password("123456")
                    .fullName("Marina Castro").bio("Aventureira")
                    .profilePictureUrl("https://picsum.photos/207").location("Florianópolis")
                    .birthDate(new Date(96, 1, 30)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followingIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user9 = new User.Builder().username("pedro").email("pedro@gmail.com").password("123456")
                    .fullName("Pedro Henrique").bio("Cinéfilo")
                    .profilePictureUrl("https://picsum.photos/208").location("Recife")
                    .birthDate(new Date(87, 4, 18)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followingIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user10 = new User.Builder().username("patricia").email("patricia@gmail.com").password("123456")
                    .fullName("Patricia Silva").bio("Apaixonada por moda")
                    .profilePictureUrl("https://picsum.photos/209").location("Porto Alegre")
                    .birthDate(new Date(95, 6, 2)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followingIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

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

            Post post1 = new Post.Builder().authorUsername("bruno").authorId(user1.getId())
                    .content("Dia produtivo de estudo em Java!").title("Estudos").createdAt(new Date())
                    .likes(List.of()).comments(List.of()).hasUserLiked(false).build();

            Post post2 = new Post.Builder().authorUsername("ana").authorId(user2.getId())
                    .content("Foto incrível da viagem à praia.").title("Viagem").createdAt(new Date())
                    .likes(List.of()).comments(List.of()).hasUserLiked(true).build();

            Post post3 = new Post.Builder().authorUsername("carlos").authorId(user3.getId())
                    .content("Treino concluído: 15km hoje!").title("Corrida").createdAt(new Date())
                    .likes(List.of()).comments(List.of()).hasUserLiked(false).build();

            Post post4 = new Post.Builder().authorUsername("juliana").authorId(user4.getId())
                    .content("Nova receita de bolo postada!").title("Culinária").createdAt(new Date())
                    .likes(List.of()).comments(List.of()).hasUserLiked(true).build();

            Post post5 = new Post.Builder().authorUsername("rodrigo").authorId(user5.getId())
                    .content("Finalizei The Last of Us 2! Emocionante.").title("Games").createdAt(new Date())
                    .likes(List.of()).comments(List.of()).hasUserLiked(false).build();

            Post post6 = new Post.Builder().authorUsername("fernanda").authorId(user6.getId())
                    .content("Começando um novo livro de suspense.").title("Leitura").createdAt(new Date())
                    .likes(List.of()).comments(List.of()).hasUserLiked(true).build();

            Post post7 = new Post.Builder().authorUsername("lucas").authorId(user7.getId())
                    .content("Nova música autoral saindo em breve.").title("Música").createdAt(new Date())
                    .likes(List.of()).comments(List.of()).hasUserLiked(true).build();

            Post post8 = new Post.Builder().authorUsername("marina").authorId(user8.getId())
                    .content("Acabei de voltar de um mochilão pelo sul!").title("Viagem").createdAt(new Date())
                    .likes(List.of()).comments(List.of()).hasUserLiked(false).build();

            Post post9 = new Post.Builder().authorUsername("pedro").authorId(user9.getId())
                    .content("Assisti a um clássico do cinema ontem.").title("Filmes").createdAt(new Date())
                    .likes(List.of()).comments(List.of()).hasUserLiked(true).build();

            Post post10 = new Post.Builder().authorUsername("patricia").authorId(user10.getId())
                    .content("Nova coleção de moda outono-inverno!").title("Moda").createdAt(new Date())
                    .likes(List.of()).comments(List.of()).hasUserLiked(false).build();

            postRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5, post6, post7, post8, post9, post10));

            List<Post> postList = postRepository.findAll();

            Like like1 = new Like(null, "ana", user1.getId(), postList.getFirst().getId(), new Date().toString());
            Like like2 = new Like(null, "carlos", user2.getId(), postList.getFirst().getId(), new Date().toString());
            Like like3 = new Like(null, "bruno", user3.getId(), postList.get(2).getId(), new Date().toString());
            Like like4 = new Like(null, "juliana", user4.getId(), postList.get(5).getId(), new Date().toString());
            Like like5 = new Like(null, "rodrigo", user5.getId(), postList.get(3).getId(), new Date().toString());
            Like like6 = new Like(null, "fernanda", user6.getId(), postList.get(4).getId(), new Date().toString());
            Like like7 = new Like(null, "lucas", user7.getId(), postList.get(6).getId(), new Date().toString());
            Like like8 = new Like(null, "marina", user8.getId(), postList.get(7).getId(), new Date().toString());
            Like like9 = new Like(null, "pedro", user9.getId(), postList.get(8).getId(), new Date().toString());
            Like like10 = new Like(null, "patricia", user10.getId(), postList.get(9).getId(), new Date().toString());

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

            Comment comment1 = new Comment(null, optionalPost1.get().getId(), user2.getId(), "Parabéns pelo post, Bruno!", new Date());
            Comment comment2 = new Comment(null, optionalPost2.get().getId(), user1.getId(), "Lugar incrível, Ana!", new Date());
            Comment comment3 = new Comment(null, optionalPost3.get().getId(), user4.getId(), "Mandou bem no treino, Carlos!", new Date());
            Comment comment4 = new Comment(null, optionalPost4.get().getId(), user5.getId(), "Vou tentar essa receita!", new Date());
            Comment comment5 = new Comment(null, optionalPost5.get().getId(), user6.getId(), "A história é emocionante, né?", new Date());
            Comment comment6 = new Comment(null, optionalPost6.get().getId(), user7.getId(), "Qual o nome do livro?", new Date());
            Comment comment7 = new Comment(null, optionalPost7.get().getId(), user8.getId(), "Ansiosa pra ouvir!", new Date());
            Comment comment8 = new Comment(null, optionalPost8.get().getId(), user9.getId(), "Que viagem top!", new Date());
            Comment comment9 = new Comment(null, optionalPost9.get().getId(), user10.getId(), "Adoro esse filme também!", new Date());
            Comment comment10 = new Comment(null, optionalPost10.get().getId(), user1.getId(), "A coleção tá linda!", new Date());

            commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3, comment4, comment5, comment6,
                    comment7, comment8, comment9, comment10));

            Follow follow1 = new Follow.Builder()
                    .followerId(user2.getId())
                    .followingId(user1.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow2 = new Follow.Builder()
                    .followerId(user3.getId())
                    .followingId(user1.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow3 = new Follow.Builder()
                    .followerId(user4.getId())
                    .followingId(user2.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow4 = new Follow.Builder()
                    .followerId(user5.getId())
                    .followingId(user3.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow5 = new Follow.Builder()
                    .followerId(user6.getId())
                    .followingId(user4.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow6 = new Follow.Builder()
                    .followerId(user7.getId())
                    .followingId(user5.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow7 = new Follow.Builder()
                    .followerId(user8.getId())
                    .followingId(user6.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow8 = new Follow.Builder()
                    .followerId(user9.getId())
                    .followingId(user7.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow9 = new Follow.Builder()
                    .followerId(user10.getId())
                    .followingId(user8.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow10 = new Follow.Builder()
                    .followerId(user1.getId())
                    .followingId(user9.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            followRepository.saveAll(Arrays.asList(follow1, follow2, follow3, follow4, follow5, follow6,
                    follow7, follow8, follow9, follow10));
        };
    }
}
