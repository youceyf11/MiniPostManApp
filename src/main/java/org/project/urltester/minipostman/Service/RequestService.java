package org.project.urltester.minipostman.Service;

import lombok.RequiredArgsConstructor;
import org.project.urltester.minipostman.Requests.Request;
import org.project.urltester.minipostman.Response.Response;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class RequestService  {

    private final RestClient restClient ;

    public Response sendRequest(Request request) {
        switch (request.requestType()){
            case GET:
                return sendGetRequest(request);
            case POST:
                return sendPostRequest(request);
            case PUT:
                return sendPutRequest(request);
            case DELETE:
                return sendDeleteRequest(request);
            default:
                throw new IllegalArgumentException("Invalid request type");
        }
    }


    private Response sendGetRequest(Request request) {
        HttpHeaders headers= new HttpHeaders();
        if(request.headers() != null){
            request.headers().forEach(headers::set);
        }
        HttpEntity<String> entity= new HttpEntity<>(request.body(), headers);
        ResponseEntity<String> response = restClient.get()
                .uri(request.url())
                .retrieve()
                .toEntity(String.class);
        return new Response(response.getStatusCode().toString(),response.getBody());
    }

    private Response sendDeleteRequest(Request request){
        HttpHeaders headers= new HttpHeaders();
        if(request.headers() != null){
            request.headers().forEach(headers::set);
        }
        HttpEntity<String> entity= new HttpEntity<>(request.body(), headers);
        ResponseEntity<String> response= restClient.delete()
                .uri(request.url())
                .retrieve()
                .toEntity(String.class);
        return new Response(response.getStatusCode().toString(),response.getBody());
    }

    private Response sendPostRequest(Request request){
        HttpHeaders headers= new HttpHeaders();
        if(request.headers() != null){
            request.headers().forEach(headers::set);
        }
        HttpEntity<String> entity= new HttpEntity<>(request.body(), headers);
        ResponseEntity<String> response=restClient.post()
                .uri(request.url())
                .body(entity)
                .retrieve()
                .toEntity(String.class);
        return new Response(response.getStatusCode().toString(),response.getBody());
    }

    private Response sendPutRequest(Request request){
        HttpHeaders headers= new HttpHeaders();
        if(request.headers() != null){
            request.headers().forEach(headers::set);
        }
        HttpEntity<String> entity= new HttpEntity<>(request.body(), headers);
        ResponseEntity<String> response= restClient.put()
                .uri(request.url())
                .body(entity)
                .retrieve()
                .toEntity(String.class);
        return new Response(response.getStatusCode().toString(),response.getBody());
    }

}
