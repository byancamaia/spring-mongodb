package br.anhembi.testea3.services;

import br.anhembi.testea3.domain.Post;
import br.anhembi.testea3.domain.User;
import br.anhembi.testea3.dto.UserDTO;
import br.anhembi.testea3.repository.PostRepository;
import br.anhembi.testea3.repository.UserRepository;
import br.anhembi.testea3.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public List<Post> findAll() {
        return repo.findAll();
    }

    public List<Post> findByTitle(String text){
        return repo.findByTitleContaining(text);
    }
}
