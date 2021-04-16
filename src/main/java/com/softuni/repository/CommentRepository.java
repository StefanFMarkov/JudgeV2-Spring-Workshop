package com.softuni.repository;

import com.softuni.model.entities.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(" select  avg(c.score) from  Comment c ")
    Double findAvgScore();

    @Query(" select  c.author.username from Comment c order by c.score ")
    Set<String> findTop3ByAuthor_OrderByScore();
}

