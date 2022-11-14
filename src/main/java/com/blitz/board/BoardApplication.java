package com.blitz.board;

import com.blitz.board.config.JdbcTemplateV1Config;
import com.blitz.board.config.JdbcTemplateV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(JdbcTemplateV1Config.class)
@SpringBootApplication(scanBasePackages = "com.blitz.board.web")
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

}
