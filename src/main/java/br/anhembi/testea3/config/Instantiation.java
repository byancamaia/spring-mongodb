package br.anhembi.testea3.config;

import br.anhembi.testea3.domain.Post;
import br.anhembi.testea3.domain.User;
import br.anhembi.testea3.dto.AuthorDTO;
import br.anhembi.testea3.dto.CommentDTO;
import br.anhembi.testea3.repository.PostRepository;
import br.anhembi.testea3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "maria eoinw", "amria@fjisj.com", "sajsi");
        User alex = new User(null, "alex", "alex@fjisj.com", "ksapks");
        User bob = new User(null, "bobw", "bob@fjisj.com", "assji");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2022"), "Partiu Viagem!", "Vou viajar para SP. Abraços!",new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("01/06/2022"), "Bom dia!", "Acordei Feliz Hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa Viagem!", sdf.parse("21/03/2022"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveita!", sdf.parse("22/03/2022"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("01/06/2022"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1,post2));
        userRepository.save(maria);
    }
}
