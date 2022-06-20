package com.example.md4minitest170622.codegym.controller;

import com.example.md4minitest170622.codegym.model.Post;
import com.example.md4minitest170622.codegym.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<Iterable<Post>> findAllPost() {
        List<Post> posts = (List<Post>) postService.findAll();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/search/title")
    public ResponseEntity<Iterable<Post>> findAllByTitle(@RequestParam("title") String title) {
        List<Post> posts = (List<Post>) postService.findAllByTitle(title);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/search/many")
    public ResponseEntity<Iterable<Post>> searchByTitleAndYear(@RequestParam("title") String title, @RequestParam("year") int year) {
        List<Post> posts = null;
        if (title.isEmpty()) {
            posts = (List<Post>) postService.searchByYear(year);
        } else if (year == 0) {
            posts = (List<Post>) postService.searchByTitle(title);
        }else if (!title.isEmpty() && year != 0) {
            posts = (List<Post>) postService.searchByTitleAndYear(title, year);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/search/year")
    public ResponseEntity<Iterable<Post>> findAllByCreateAtBetween(@RequestParam("from") int from, @RequestParam("to") int to) {
        List<Post> posts = (List<Post>) postService.findAllByCreateAtBetween(from, to);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/show/like")
    public ResponseEntity<Iterable<Post>> showListByLike() {
        List<Post> posts = (List<Post>) postService.showListByLikeAsc();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/show/new")
    public ResponseEntity<Iterable<Post>> showNewList() {
        List<Post> posts = (List<Post>) postService.showFourNewPost();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.remove(id);
        return new ResponseEntity<>(postOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updateProduct(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setId(postOptional.get().getId());
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }


}
