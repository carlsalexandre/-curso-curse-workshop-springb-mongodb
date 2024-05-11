package com.cursoudemy.workshopmongo.config;

import com.cursoudemy.workshopmongo.domain.Post;
import com.cursoudemy.workshopmongo.domain.User;
import com.cursoudemy.workshopmongo.dto.AuthorDTO;
import com.cursoudemy.workshopmongo.repository.PostRepository;
import com.cursoudemy.workshopmongo.repository.UserRepository;
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

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2024"), "Indo viajar", "E levar meus cachorros junto.", new AuthorDTO(alex));
        Post post2 = new Post(null, sdf.parse("24/03/2024"), "Voltando da viagem", "Os cachorros aprontaram muito.", new AuthorDTO(alex));
        
        postRepository.saveAll(Arrays.asList(post1, post2));

        alex.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(alex);
    }
}
