package com.github.arcyfelix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.arcyfelix.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
