package org.project.urltester.minipostman.Controller;

import org.project.urltester.minipostman.Requests.Request;
import org.project.urltester.minipostman.Response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class RequestController {
    public ResponseEntity<Response> sendRequest(@RequestBody Request request){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

