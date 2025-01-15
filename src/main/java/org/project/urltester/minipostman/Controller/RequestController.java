package org.project.urltester.minipostman.Controller;

import lombok.RequiredArgsConstructor;
import org.project.urltester.minipostman.Requests.Request;
import org.project.urltester.minipostman.Response.Response;
import org.project.urltester.minipostman.Service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request")
@CrossOrigin("*")
public class RequestController {

    private final RequestService requestService;

    @PostMapping()
    public ResponseEntity<Response> sendRequest(@RequestBody Request request){
        return ResponseEntity.ok(requestService.sendRequest(request));
    }
}

