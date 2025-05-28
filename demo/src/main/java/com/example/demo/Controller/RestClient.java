package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RestController
public class RestClient {
    public static String GET_BY_ID = "http://localhost:8080/getbyid/{id}";
    public static String GET_All = "http://localhost:8080/getall";
    public static String Add_User = "http://localhost:8080/adduser";
    public static String Update_User = "http://localhost:8080/update/{id}";
    public static String Delete_User = "http://localhost:8080/deleteuser/{id}";

    @Autowired
    private  RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;
    @GetMapping("callget")
    public  ResponseEntity<?> callAllUsers() {
        HttpEntity<User> entity = new HttpEntity<>(headers);
        return new ResponseEntity<>(
                restTemplate.exchange(GET_All, HttpMethod.GET, entity, List.class).getBody(),
                HttpStatus.OK);
    }

    @GetMapping("callbyid/{id}")
    public  ResponseEntity<?> callByid(@PathVariable int id) {
        try{
            String url = GET_BY_ID.replace("{id}", String.valueOf(id));
            HttpEntity<User> entity = new HttpEntity<>(headers);
            return ResponseEntity.ok(restTemplate.exchange(url, HttpMethod.GET, entity, User.class).getBody());
        }catch (Exception e){
            throw new UserNotFound("UserNotFound");
        }
    }

    @PostMapping("calladduser")
    public  ResponseEntity<?> callAddUser(@RequestBody User user) {
        HttpEntity<User> entity = new HttpEntity<>(user,headers);
        return new ResponseEntity<>(
                restTemplate.exchange(Add_User,HttpMethod.POST,entity,User.class).getBody(),
                HttpStatus.CREATED);
    }

    @DeleteMapping("calldeleteuser/{id}")
    public  ResponseEntity<?> callDelete(@PathVariable int id) {
        try{
            String url=Delete_User.replace("{id}", String.valueOf(id));
            HttpEntity<User> entity = new HttpEntity<>(headers);


            return new ResponseEntity<>(
                    restTemplate.exchange(url,HttpMethod.DELETE,entity,User.class).getBody(),
                    HttpStatus.OK);
        }catch (Exception e){
            throw new UserNotFound("UserNotFound");
        }
    }

    @PutMapping("callupdate/{id}")
    public  ResponseEntity<?> callUpdate(@PathVariable int id, @RequestBody User user) {
        try{
            String url=Update_User.replace("{id}", String.valueOf(id));
            HttpEntity<User> entity = new HttpEntity<>(user,headers);
            return new ResponseEntity<>(restTemplate.exchange(url,HttpMethod.PUT,entity,User.class).getBody(),
                    HttpStatus.OK);
        }catch (Exception e){
            throw new UserNotFound("UserNotFound");
        }
    }
}
