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

            User user1 = User.builder()
                    .username("bruno").email("bruno@gmail.com").password("123456")
                    .fullName("Bruno Freire").bio("Amante de tecnologia")
                    .profilePictureUrl("https://picsum.photos/200").location("São Paulo")
                    .birthDate(new Date(95, 5, 10)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followedIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>())
                    .build();

            User user2 = User.builder()
                    .username("ana").email("ana@gmail.com").password("123456")
                    .fullName("Ana Souza").bio("Fotógrafa de viagens")
                    .profilePictureUrl("https://picsum.photos/201").location("Rio de Janeiro")
                    .birthDate(new Date(90, 8, 20)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followedIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>())
                    .build();

            User user3 = User.builder().username("carlos").email("carlos@gmail.com").password("123456")
                    .fullName("Carlos Lima").bio("Apaixonado por esportes")
                    .profilePictureUrl("https://picsum.photos/202").location("Belo Horizonte")
                    .birthDate(new Date(88, 2, 15)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followedIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user4 = User.builder().username("juliana").email("juliana@gmail.com").password("123456")
                    .fullName("Juliana Ribeiro").bio("Chef de cozinha")
                    .profilePictureUrl("https://picsum.photos/203").location("Curitiba")
                    .birthDate(new Date(92, 11, 5)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followedIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user5 = User.builder().username("rodrigo").email("rodrigo@gmail.com").password("123456")
                    .fullName("Rodrigo Martins").bio("Viciado em games")
                    .profilePictureUrl("https://picsum.photos/204").location("Salvador")
                    .birthDate(new Date(94, 3, 12)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followedIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user6 = User.builder().username("fernanda").email("fernanda@gmail.com").password("123456")
                    .fullName("Fernanda Lopes").bio("Leitora voraz")
                    .profilePictureUrl("https://picsum.photos/205").location("Fortaleza")
                    .birthDate(new Date(91, 7, 19)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followedIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user7 = User.builder().username("lucas").email("lucas@gmail.com").password("123456")
                    .fullName("Lucas Almeida").bio("Músico")
                    .profilePictureUrl("https://picsum.photos/206").location("Brasília")
                    .birthDate(new Date(93, 10, 25)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followedIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user8 = User.builder().username("marina").email("marina@gmail.com").password("123456")
                    .fullName("Marina Castro").bio("Aventureira")
                    .profilePictureUrl("https://picsum.photos/207").location("Florianópolis")
                    .birthDate(new Date(96, 1, 30)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followedIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user9 = User.builder().username("pedro").email("pedro@gmail.com").password("123456")
                    .fullName("Pedro Henrique").bio("Cinéfilo")
                    .profilePictureUrl("https://picsum.photos/208").location("Recife")
                    .birthDate(new Date(87, 4, 18)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followedIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            User user10 = User.builder().username("patricia").email("patricia@gmail.com").password("123456")
                    .fullName("Patricia Silva").bio("Apaixonada por moda")
                    .profilePictureUrl("https://picsum.photos/209").location("Porto Alegre")
                    .birthDate(new Date(95, 6, 2)).createdAt(new Date())
                    .isActive(true).lastLogin(new Date())
                    .followersIds(new ArrayList<>()).followedIds(new ArrayList<>()).posts(new ArrayList<>())
                    .blockedByIds(new ArrayList<>()).blockedUsersIds(new ArrayList<>()).build();

            List<User> savedUsers = userRepository.saveAll(
                    Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10)
            );

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

            Post post1 = Post.builder().authorUsername("bruno").authorId(user1.getId())
                    .content("Dia produtivo de estudo em Java!").title("Estudos").createdAt(new Date())
                    .authorProfilePictureUrl(null).hasUserLiked(false).build();

            Post post2 = Post.builder().authorUsername("ana").authorId(user2.getId())
                    .content("Foto incrível da viagem à praia.").title("Viagem").createdAt(new Date())
                    .authorProfilePictureUrl(null).hasUserLiked(true).build();

            Post post3 = Post.builder().authorUsername("carlos").authorId(user3.getId())
                    .content("Treino concluído: 15km hoje!").title("Corrida").createdAt(new Date())
                    .authorProfilePictureUrl(null).hasUserLiked(false).build();

            Post post4 = Post.builder().authorUsername("juliana").authorId(user4.getId())
                    .content("Nova receita de bolo postada!").title("Culinária").createdAt(new Date())
                    .authorProfilePictureUrl(null).hasUserLiked(true).build();

            Post post5 = Post.builder().authorUsername("rodrigo").authorId(user5.getId())
                    .content("Finalizei The Last of Us 2! Emocionante.").title("Games").createdAt(new Date())
                    .authorProfilePictureUrl(null).hasUserLiked(false).build();

            Post post6 = Post.builder().authorUsername("fernanda").authorId(user6.getId())
                    .content("Começando um novo livro de suspense.").title("Leitura").createdAt(new Date())
                    .authorProfilePictureUrl(null).hasUserLiked(true).build();

            Post post7 = Post.builder().authorUsername("lucas").authorId(user7.getId())
                    .content("Nova música autoral saindo em breve.").title("Música").createdAt(new Date())
                    .authorProfilePictureUrl(null).hasUserLiked(true).build();

            Post post8 = Post.builder().authorUsername("marina").authorId(user8.getId())
                    .content("Acabei de voltar de um mochilão pelo sul!").title("Viagem").createdAt(new Date())
                    .authorProfilePictureUrl(null).hasUserLiked(false).build();

            Post post9 = Post.builder().authorUsername("pedro").authorId(user9.getId())
                    .content("Assisti a um clássico do cinema ontem.").title("Filmes").createdAt(new Date())
                    .authorProfilePictureUrl(null).hasUserLiked(true).build();

            Post post10 = Post.builder().authorUsername("patricia").authorId(user10.getId())
                    .content("Nova coleção de moda outono-inverno!").title("Moda").createdAt(new Date())
                    .authorProfilePictureUrl(null).hasUserLiked(false).build();

            postRepository.saveAll(
                    Arrays.asList(post1, post2, post3, post4, post5, post6, post7, post8, post9, post10)
            );

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

            likeRepository.saveAll(
                    Arrays.asList(like1, like2, like3, like4, like5, like6, like7, like8, like9, like10)
            );

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

            Comment comment1 = Comment.builder()
                    .id(null)
                    .postId(optionalPost1.get().getId())
                    .authorId(user2.getId())
                    .content("Parabéns pelo post, Bruno!")
                    .createdAt(new Date())
                    .build();

            Comment comment2 = Comment.builder()
                    .id(null)
                    .postId(optionalPost2.get().getId())
                    .authorId(user1.getId())
                    .content("Lugar incrível, Ana!")
                    .createdAt(new Date())
                    .build();

            Comment comment3 = Comment.builder()
                    .id(null)
                    .postId(optionalPost3.get().getId())
                    .authorId(user4.getId())
                    .content("Mandou bem no treino, Carlos!")
                    .createdAt(new Date())
                    .build();

            Comment comment4 = Comment.builder()
                    .id(null)
                    .postId(optionalPost4.get().getId())
                    .authorId(user5.getId())
                    .content("Vou tentar essa receita!")
                    .createdAt(new Date())
                    .build();

            Comment comment5 = Comment.builder()
                    .id(null)
                    .postId(optionalPost5.get().getId())
                    .authorId(user6.getId())
                    .content("A história é emocionante, né?")
                    .createdAt(new Date())
                    .build();

            Comment comment6 = Comment.builder()
                    .id(null)
                    .postId(optionalPost6.get().getId())
                    .authorId(user7.getId())
                    .content("Qual o nome do livro?")
                    .createdAt(new Date())
                    .build();

            Comment comment7 = Comment.builder()
                    .id(null)
                    .postId(optionalPost7.get().getId())
                    .authorId(user8.getId())
                    .content("Ansiosa pra ouvir!")
                    .createdAt(new Date())
                    .build();

            Comment comment8 = Comment.builder()
                    .id(null)
                    .postId(optionalPost8.get().getId())
                    .authorId(user9.getId())
                    .content("Que viagem top!")
                    .createdAt(new Date())
                    .build();

            Comment comment9 = Comment.builder()
                    .id(null)
                    .postId(optionalPost9.get().getId())
                    .authorId(user10.getId())
                    .content("Adoro esse filme também!")
                    .createdAt(new Date())
                    .build();

            Comment comment10 = Comment.builder()
                    .id(null)
                    .postId(optionalPost10.get().getId())
                    .authorId(user1.getId())
                    .content("A coleção tá linda!")
                    .createdAt(new Date())
                    .build();


            commentRepository.saveAll(
                    Arrays.asList(comment1, comment2, comment3, comment4, comment5,
                            comment6, comment7, comment8, comment9, comment10)
            );

            Follow follow1 = Follow.builder()
                    .followerId(user2.getId())
                    .followedId(user1.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow2 = Follow.builder()
                    .followerId(user3.getId())
                    .followedId(user1.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow3 = Follow.builder()
                    .followerId(user4.getId())
                    .followedId(user2.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow4 = Follow.builder()
                    .followerId(user5.getId())
                    .followedId(user3.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow5 = Follow.builder()
                    .followerId(user6.getId())
                    .followedId(user4.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow6 = Follow.builder()
                    .followerId(user7.getId())
                    .followedId(user5.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow7 = Follow.builder()
                    .followerId(user8.getId())
                    .followedId(user6.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow8 = Follow.builder()
                    .followerId(user9.getId())
                    .followedId(user7.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow9 = Follow.builder()
                    .followerId(user10.getId())
                    .followedId(user8.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            Follow follow10 = Follow.builder()
                    .followerId(user1.getId())
                    .followedId(user9.getId())
                    .createdAt(new Date())
                    .notified(false)
                    .status(FollowStatus.FOLLOWING)
                    .build();

            followRepository.saveAll(Arrays.asList(follow1, follow2, follow3, follow4, follow5, follow6,
                    follow7, follow8, follow9, follow10));

            followRepository.deleteAll();
        };
    }
}
