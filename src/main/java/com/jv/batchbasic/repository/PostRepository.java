package com.jv.batchbasic.repository;

import com.jv.batchbasic.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
