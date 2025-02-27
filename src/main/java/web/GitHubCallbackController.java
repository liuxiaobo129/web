package web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/github")
public class GitHubCallbackController {

    @Value("${github.client-id}")
    private String clientId;

    @Value("${github.client-secret}")
    private String clientSecret;

    @GetMapping("/callback")
    public ResponseEntity<?> githubCallback(@RequestParam("code") String code) {
        RestTemplate restTemplate = new RestTemplate();

        // 1. 交换 `code` 获取 `access_token`
        String tokenUrl = "https://github.com/login/oauth/access_token";
//        Map<String, String> params = new HashMap<>();
        // 创建表单参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, Map.class);

        if (response.getBody() == null || !response.getBody().containsKey("access_token")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("GitHub 登录失败");
        }

        String accessToken = response.getBody().get("access_token").toString();

        // 2. 获取用户信息
        HttpHeaders userHeaders = new HttpHeaders();
        userHeaders.set("Authorization", "token " + accessToken);

        HttpEntity<String> userRequest = new HttpEntity<>(null, userHeaders);
        ResponseEntity<Map> userResponse = restTemplate.exchange(
                "https://api.github.com/user", HttpMethod.GET, userRequest, Map.class);

        Map<String, Object> userInfo = userResponse.getBody();
        return ResponseEntity.ok(userInfo);
    }
}