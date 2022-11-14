package com.blitz.board.service.dto;

import com.blitz.board.domain.Posts;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

public class PostsDto {

    @Data
    public static class Request {

        private Long id;
        private String title;
        private String content;
        private String writer;
        private int view;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public Request() {}

        @Builder
        public Request(Long id, String title, String content, String writer, int view ,LocalDateTime createdDate, LocalDateTime modifiedDate) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.writer = writer;
            this.view = view;
            this.createdDate = createdDate;
            this.modifiedDate = modifiedDate;
        }

        public Posts toEntity() {

            return Posts.builder()
                    .title(title)
                    .content(content)
                    .writer(writer)
                    .view(view)
                    .createdDate(createdDate)
                    .modifiedDate(modifiedDate)
                    .build();
        }
    }

    @Getter
    public static class Response {
        private final Long id;
        private final String title;
        private final String content;
        private final String writer;
        private final int view;
        private final LocalDateTime modifiedDate;

        public Response(Posts posts) {
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.content = posts.getContent();
            this.writer = posts.getWriter();
            this.view = posts.getView();
            this.modifiedDate = posts.getModifiedDate();
        }
    }
}
