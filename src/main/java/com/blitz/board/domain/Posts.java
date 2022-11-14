package com.blitz.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Posts {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private int view;

    private User user;

    private List<Comment> comments;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;


}
