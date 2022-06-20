package com.example.md4minitest170622.codegym.repository;

import com.example.md4minitest170622.codegym.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IPostRepository extends PagingAndSortingRepository<Post, Long> {

    Iterable<Post> findAllByTitle(String title);

    @Query(value = "select * from posts p\n" +
            "order by p.likes asc", nativeQuery = true)
    Iterable<Post> showListByLikeAsc();

    @Query(value = "select * from posts p\n" +
            "order by p.id desc limit 4 order by p.create_at asc", nativeQuery = true)
    Iterable<Post> showFourNewPost();

    @Query(value = "select * from posts p where p.title like ? and YEAR(p.create_at) = ? order by p.create_at asc", nativeQuery = true)
    Iterable<Post> searchByTitleAndYear(String title, int year);

    @Query(value = "select * from posts p where p.title like ? order by p.create_at asc", nativeQuery = true)
    Iterable<Post> searchByTitle(String title);

    @Query(value = "select * from posts p where YEAR(p.create_at) = ?", nativeQuery = true)
    Iterable<Post> searchByYear(int year);

    @Query(value = "select * from posts p\n" +
            "where YEAR(p.create_at) between ? and ?\n" +
            "order by p.create_at asc ", nativeQuery = true)
    Iterable<Post> findAllByCreateAtBetween(int from, int to);

    Iterable<Post> findAllByLikesBetween(int from,int to);

    Iterable<Post> findAllByTitleAndCreateAtContaining(String title, Date createAt);
}
