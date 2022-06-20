package com.example.md4minitest170622.codegym.service.post;

import com.example.md4minitest170622.codegym.model.Post;
import com.example.md4minitest170622.codegym.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService{

    @Autowired
    private IPostRepository postRepository;


    public Iterable<Post> findAllByTitle(String title) {
        return postRepository.findAllByTitle(title);
    }

    public Iterable<Post> showListByLikeAsc() {
        return postRepository.showListByLikeAsc();
    }

    public Iterable<Post> showFourNewPost() {
        return postRepository.showFourNewPost();
    }

    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    public Iterable<Post> searchByTitle(String title) {
        return postRepository.searchByTitle(title);
    }

    public Iterable<Post> searchByYear(int year) {
        return postRepository.searchByYear(year);
    }

    public Iterable<Post> searchByTitleAndYear(String title, int year) {
        return postRepository.searchByTitleAndYear(title, year);
    }
}
