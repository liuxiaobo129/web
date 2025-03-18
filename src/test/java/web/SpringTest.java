package web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringTest {

    @Autowired
    private GitHubCallbackController gitHubCallbackController;


    @Test
    public void dis(){
        gitHubCallbackController.githubCallback("a");
    }
}
