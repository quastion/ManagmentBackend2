package com.opencode.managment.app;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Response {
    private String answer;
    private String status;

    public Response(String status) {
        this.status = status;
    }
}
