package pe.gob.minsa.microservicio.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ApiResponseEntity {

    public static ResponseEntity<Object> successDelete() {
        HttpStatus status = HttpStatus.valueOf(HttpStatus.OK.value());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("success", true);
        map.put("data", null);
        map.put("empty", true);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> successDeleteAll() {
        return successDelete();
    }

    public static ResponseEntity<Object> successRead(Object object) {
        HttpStatus status = HttpStatus.valueOf(HttpStatus.OK.value());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("success", true);
        map.put("data", object);
        map.put("empty", object == null ? true : false);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> successUpdate(Object object) {
        return successRead(object);
    }

    public static ResponseEntity<Object> successCreate(Object object) {
        HttpStatus status = HttpStatus.valueOf(HttpStatus.CREATED.value());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("success", true);
        map.put("data", object);
        map.put("empty", object == null ? true : false);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> notFound(Class aClass, Long id) {
        HttpStatus status = HttpStatus.valueOf(HttpStatus.NOT_FOUND.value());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("success", true);
        map.put("message", aClass.getSimpleName() + " with id=" + id.toString() + " not found.");
        map.put("data", null);
        map.put("empty", true);
        return new ResponseEntity<>(map, status);
    }

    public static Object secureException(String message) {
        HttpStatus status = HttpStatus.valueOf(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> error = new HashMap<String, Object>();

        map.put("status", status);
        map.put("success", false);
        error.put("status", status);
        error.put("timestamp", getCurrentDateTime());
        error.put("message", message);
        error.put("debug_message", null);
        map.put("error", error);
        return map;
    }

    public static ResponseEntity<Object> notFound() {
        HttpStatus status = HttpStatus.valueOf(HttpStatus.NOT_FOUND.value());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("success", true);
        map.put("data", null);
        map.put("empty", true);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> error(Exception exception) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> error = new HashMap<String, Object>();

        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("success", false);
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        error.put("timestamp", getCurrentDateTime());
        error.put("message", exception.getMessage());
        error.put("debug_message", exception.getCause());
        map.put("error", error);
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public static ResponseEntity<Object> error(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> error = new HashMap<String, Object>();

        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("success", false);
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        error.put("timestamp", getCurrentDateTime());
        error.put("message", message);
        error.put("debug_message", null);
        map.put("error", error);
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Object> error(String message, String cause) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> error = new HashMap<String, Object>();

        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("success", false);
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        error.put("timestamp", getCurrentDateTime());
        error.put("message", message);
        error.put("debug_message", cause);
        map.put("error", error);
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
