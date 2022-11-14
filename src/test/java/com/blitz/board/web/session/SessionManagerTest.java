package com.blitz.board.web.session;

import com.blitz.board.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    public void sessionTest() {
        // given
        MockHttpServletResponse response = new MockHttpServletResponse();
        User user = new User();
        sessionManager.createSession(user, response);

        // when
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        // then
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(user);

        // 세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }

    @Test
    public void common() {
        // given
        class Solution {
            public int solution(int[] common) {
                int answer = 0;
                for (int i = 0; i < common.length; i++) {
                    int i1 = common[i + 1] - common[i];
                }
                return answer;
            }
        }

        // when


        // then


    }

}
