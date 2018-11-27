package support.test;

import nextstep.domain.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest extends BaseTest {
    private static final String DEFAULT_LOGIN_USER = "javajigi";
    private static final String OTHER_LOGIN_USER = "sanjigi";
    private static final long DEFAULT_QUESTION_ID = 1L;
    private static final long DEFAULT_ANSWER_ID = 1L;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;


    public TestRestTemplate template() {
        return template;
    }

    public TestRestTemplate basicAuthTemplate() {
        return basicAuthTemplate(defaultUser());
    }

    public TestRestTemplate basicAuthTemplate(User loginUser) {
        return template.withBasicAuth(loginUser.getUserId(), loginUser.getPassword());
    }

    protected User defaultUser() {
        return findByUserId(DEFAULT_LOGIN_USER);
    }
    protected User otherUser() {
        return findByUserId(OTHER_LOGIN_USER);
    }

    protected User findByUserId(String userId) {
        return userRepository.findByUserId(userId).get();
    }

    protected Question defaultQuestion() {
        return findById(DEFAULT_QUESTION_ID);
    }

    protected Question findById(long id) {
        return questionRepository.findById(id).get();
    }

    protected Answer defaultAnswer() {
        return findByAnswerId(DEFAULT_ANSWER_ID);
    }

    protected Answer findByAnswerId(long id) {
        return answerRepository.findById(id).get();
    }
}
