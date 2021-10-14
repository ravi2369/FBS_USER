package com.fbs.user.service.serviceImpl;

import com.fbs.user.exceptions.FBSException;
import com.fbs.user.model.BookingRequest;
import com.fbs.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static String adminUrl;
    @Autowired
    private WebClient webClient;

    public UserServiceImpl(@Value("${admin.url}") String adminUrl) {
        log.info("url-->" + adminUrl);
        this.adminUrl = adminUrl;
    }

    @Override
    public String search(String fromLocation, String toLocation) throws FBSException {
        String responseInStringFormat = null;
        ResponseEntity<String> webClientResponse = null;
        Map<String, Object> responseMap = new HashMap<>();
        try {
            webClientResponse = (ResponseEntity) webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/schedule/search")
                            .queryParam("fromLocation", fromLocation)
                            .queryParam("toLocation", toLocation)
                            .build()).exchangeToMono(rs -> rs.toEntity(String.class))
                    .block();

            String httpStatus = webClientResponse.getStatusCode().toString();
            int statusCode = webClientResponse.getStatusCodeValue();
            if (statusCode == 200 || statusCode == 202) {
                responseInStringFormat = webClientResponse.getBody();
            }
        } catch (Exception e) {
            throw new FBSException(e.getMessage());
        }
        return responseInStringFormat;
    }

    @Override
    public String book(String flightNumber, BookingRequest request) {
        String responseInStringFormat = null;
        ResponseEntity<String> webClientResponse = null;
        Map<String, Object> responseMap = new HashMap<>();
        try {
            webClientResponse = (ResponseEntity) webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/book/ticket" + "{" + flightNumber + "}")
                            .build()).body(BodyInserters.fromValue(request)).exchangeToMono(rs -> rs.toEntity(String.class))
                    .block();

            String httpStatus = webClientResponse.getStatusCode().toString();
            int statusCode = webClientResponse.getStatusCodeValue();
            if (statusCode == 200 || statusCode == 202) {
                responseInStringFormat = webClientResponse.getBody();
            }
        } catch (Exception e) {
            throw new FBSException(e.getMessage());
        }
        return responseInStringFormat;
    }
}
