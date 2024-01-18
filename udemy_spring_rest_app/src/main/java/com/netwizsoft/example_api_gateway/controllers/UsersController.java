package com.netwizsoft.example_api_gateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netwizsoft.example_api_gateway.model.PatchUserRequest;
import com.netwizsoft.example_api_gateway.model.User;
import com.netwizsoft.example_api_gateway.service.UserService;

import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UsersController{
	
	@Autowired
	private UserService userService;

//    @GetMapping("/test")
//    public void test(@RequestHeader Map<String, String> headers) {
//        headers.forEach((key, value) -> {
//            System.out.println(key + " - " + value);
//        });
//    }
//    
//    @GetMapping("/test/{id}")
//    public ResponseEntity<String> findUserName(@RequestHeader(value = "number1", defaultValue = "100") int num) {
//    	HttpHeaders headers = new HttpHeaders();
//    	headers.add("own-property", "This uis the value");
//    	return new ResponseEntity<>("This is a string " + num, HttpStatus.OK);
//    }
//    
//    @GetMapping("/test2")
//    public User userDetails() {
//    	User user = User.builder()
//    			.firstName("kalana")
//    			.lastName("last")
//    			.email("test123@test.com")
//    			.regDate(new Date())
//    			.build();
//    	return user;
//    }
    
    @GetMapping("/users")
    public List<User> getUsers() {
    	return this.userService.getUsers();
    }
    
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") String userId) {
    	User user = Optional.ofNullable(userId).map(u -> Long.valueOf(userId)).map(this.userService::getUser).orElseThrow();
    	
    	this.userService.delete(user.getUserId());
    }
    
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody User user) {
    	this.userService.create(user);
    }
    
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") String userId) {
    	var user = Optional.ofNullable(userId).map(u -> Long.valueOf(userId)).map(this.userService::getUser).orElseThrow();
    	return user;
    }
    
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") String userId, @RequestBody PatchUserRequest request) {
    	var user = Optional.ofNullable(userId).map(u -> Long.valueOf(userId)).map(this.userService::getUser).orElseThrow();
    	this.userService.update(user, request);
    }
    
    
//    public void calculationsRelatedToStream() {
//    	Stream<String> namesStream = Files.lines(Paths.get(path));
//    	
//    	List<String> words = Array.asList("Adam", "Ana", "Daniel");
//    	words.stream().map(String::length).collect(Collectors.toList()).forEach(System.out::println);
//    	
//    	List<Integer> nums = Arrays.asList(1,2,3,4);
//    	nums.stream().map(x -> x * x).collect(Collectors.toList()).forEach(System.out::println);
//    	
//    	String[] array = {"hello", "shell"};
//    	List<String> unique = Arrays.stream(array).map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
//    	
//    	unique.stream().forEach(System.out::println);
//    	
//    	List<Integer> num1 = Array.asList(1, 2, 3);
//    	List<Integer> num2 = Array.asList(4, 5);
//    	
//    	List<List<Integer>> pairs = num1.stream().flatMap(i -> nums2.stream().map(j -> Arrays.asList(i, j))).collect(Collectors.toList);
//    	
//    	Optional<Integer> result = nums.stream().reduce(Integer::max).ifPresent(System.out::println);
//    	
//    	// OptionalInt result = books.stream().mapToInt(Book::getPages).max();
//    	
//    	// OptionalDouble result = books.stream().mapToDouble(Book::getPages).max();
//    	// OptionalLong result2 = books.stream().mapToLong(Book::getPages);
//    	
//    	books.stream().filter(b -> b.getType() == Type.History)
//    }
    
    
    
}
