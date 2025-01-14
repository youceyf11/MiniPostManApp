package org.project.urltester.minipostman.Requests;


import java.util.Map;

public record Request(
        String url,
        Map<String,String> headers,
        String body,
        ERequestType requestType
) {
}
