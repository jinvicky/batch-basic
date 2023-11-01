package com.jv.batchbasic.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 20, nullable = false)
    private Long idx;

    @Column(length = 20, nullable = false)
    private Integer id;
    @Column(length = 20, nullable = false)
    private int userId;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 1000, nullable = false)
    private String body;

}