package com.example.md4minitest170622.codegym.service.post;

import com.example.md4minitest170622.codegym.model.Post;
import com.example.md4minitest170622.codegym.service.IGeneralService;


public interface IPostService extends IGeneralService<Post> {

    Iterable<Post> findAllByTitle(String title);

    Iterable<Post> showListByLikeAsc();

    Iterable<Post> showFourNewPost();
}