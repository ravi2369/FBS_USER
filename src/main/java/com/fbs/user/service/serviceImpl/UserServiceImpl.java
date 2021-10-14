package com.fbs.user.service.serviceImpl;

import com.fbs.user.exceptions.FBSException;
import com.fbs.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static String adminUrl;
    @Autowired
    private WebClient webClient;

    public UserServiceImpl(@Value("${admin.url}") String adminUrl) {
        this.adminUrl = adminUrl;
    }

    @Override
    public ResponseEntity search(String fromLocation, String toLocation) throws FBSException {
        ResponseEntity webClientResponse = null;
        try {
            webClientResponse = (ResponseEntity) webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(adminUrl + "/schedule/search")
                            .queryParam("fromLocation", fromLocation)
                            .queryParam("toLocation", toLocation)
                            .build()).exchangeToMono(rs -> rs.toEntity(String.class))
                    .block();

            String httpStatus = webClientResponse.getStatusCode().toString();
            int statusCode = webClientResponse.getStatusCodeValue();
            if (statusCode == 200 || statusCode == 202) {
                log.info("sucess");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return webClientResponse;
    }
}
