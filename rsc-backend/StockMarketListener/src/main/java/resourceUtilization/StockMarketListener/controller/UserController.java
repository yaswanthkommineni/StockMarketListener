package resourceUtilization.StockMarketListener.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resourceUtilization.StockMarketListener.service.*;
import resourceUtilization.StockMarketListener.model.*;
import resourceUtilization.StockMarketListener.model.Record;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;

import resourceUtilization.StockMarketListener.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ResponseBody> getAllUsers() {
    	try {
    		List <User> results = userService.getAllUsers();
    		return ResponseEntity.status(HttpStatus.FOUND).body(
    			new ResponseBody("success", new ArrayList<Record>(results))
    		);
    	}
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseBody> getUserById(@PathVariable("userId") String userId) {
    	try {
    		User result = userService.getUserById(userId);
    		return ResponseEntity.status(HttpStatus.FOUND).body(
    			new ResponseBody("success", new ArrayList<Record>(Arrays.asList(result)))
    		);
    	}
    	catch (ResourceNotFoundException  e) {
            // If the ticker already exists, send a custom error message
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        		new ResponseBody("success", new ArrayList <Record> ())
        	);
        }
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }

    @GetMapping("/teleId/{teleId}")
    public ResponseEntity<ResponseBody> getUserByTeleId(@PathVariable("teleId") String teleId) {
    	try {
    		User result = userService.getUserByTeleId(teleId);
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
        			new ResponseBody("success", new ArrayList<Record>(Arrays.asList(result)))
        		);
    	}
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }

    @PostMapping
    public ResponseEntity<ResponseBody> createUser(@RequestBody User user) {
    	try {
    		User createdUser = userService.createUser(user);
    		return ResponseEntity.status(HttpStatus.CREATED).body(
    			new ResponseBody("success", new ArrayList<Record>(Arrays.asList(createdUser)))
    		);
    	}
        catch (DuplicateKeyException e) {
            // If the ticker already exists, send a custom error message
        	return ResponseEntity.status(HttpStatus.CONFLICT).body(
        		new ResponseBody("duplicateKey", new ArrayList <Record> ())
        	);
        }
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseBody> updateUser(@PathVariable("userId") String userId, @RequestBody User userDetails) {
    	try {
    		User result = userService.updateUser(userId, userDetails);
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
    			new ResponseBody("success", new ArrayList<Record>(Arrays.asList(result)))
    		);
    	}
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseBody> deleteUser(@PathVariable("userId") String userId) {
    	try {
    		userService.deleteUser(userId);
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
    			new ResponseBody("success", new ArrayList<Record>())
    		);
    	}
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }
}