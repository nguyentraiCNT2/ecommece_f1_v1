package com.example.ecommece_f1_v1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ErrorResponse {
	public static ResponseEntity<?> buildErrorResponse(String message, HttpStatus status) {
		return  ResponseEntity.status(status).body (
				Map.of("message",message,
				"status",status.value()));
	}
}
