package com.blitz.board.web;

import com.blitz.board.service.UserService;
import com.blitz.board.service.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> join(@RequestBody UserDto.Request dto) {
        userService.saveUser(dto);
        log.info("join = {}", dto);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    /*
    @PostMapping("/{userId}/update")
    public ResponseEntity<String> modifyUser(@RequestBody UserDto.Request dto, @PathVariable("userId") Long userId) {
        log.info("requestJoin = {}, pathVa = {}", dto, userId);
        userService.modifyUser(userId, dto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    */

    /*
    @GetMapping("/user/{userId}")
    public ResponseEntity<String> findUser(@RequestParam Long id, @ResponseBody UserDto.Response response) {
        log.info("Response = {}", response);
        userService.findUser(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    */
}
