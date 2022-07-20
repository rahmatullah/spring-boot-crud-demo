package com.crud.demo.user;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo.user.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * get all users list
     * 
     * @return
     */
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> ListUsers() {

        HashMap<String, Object> resMap = new HashMap();
        resMap.put("success", "true");
        List<User> users = userService.getAllUsers();
        resMap.put("message", "Users list.");
        resMap.put("data", users);
        return ResponseEntity.ok(resMap);
    }

    /**
     * get one user details
     * 
     * @param id
     * @return
     */
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> getOneUser(@PathVariable long id) {

        HashMap<String, Object> resMap = new HashMap();
        resMap.put("success", "true");
        Optional<User> user = userService.getOneUser(id);
        resMap.put("message", "User details.");
        resMap.put("data", user);
        return ResponseEntity.ok(resMap);
    }

    /**
     * Create user
     * 
     * @param user
     * @return
     */
    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveUser(@Valid @RequestBody User user)
            throws IOException, InterruptedException, URISyntaxException {

        HashMap<String, Object> resMap = new HashMap();
        try {
            Date serverCurrentDateTime = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTimeStr = dateFormat.format(serverCurrentDateTime);
            String incomingDateStr = dateFormat.format(user.getDob());

            if (currentDateTimeStr.compareTo(incomingDateStr) > 0) {
                resMap.put("success", false);
                resMap.put("message", "Date of birth can not be before current date");
            } else {
                resMap.put("success", "true");
                User userIns = userService.saveUser(user);
                resMap.put("message", "User inserted successfully.");
                resMap.put("data", userIns);
            }
            return ResponseEntity.ok(resMap);
        } catch (Exception e) {
            resMap.put("success", "false");
            resMap.put("message", e);
            // }
            return ResponseEntity.ok(resMap);
        }
    }

    /**
     * Update user
     * 
     * @param user
     * @param id
     * @return
     */
    @PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateUser(@Valid @RequestBody User user, @PathVariable long id) {

        HashMap<String, Object> resMap = new HashMap();
        resMap.put("success", "true");
        User userUpd = userService.updateUser(id, user);
        resMap.put("message", "User updated successfully.");
        resMap.put("data", userUpd);
        return ResponseEntity.ok(resMap);
    }

    /**
     * Delete user
     * 
     * @param id
     * @return
     */
    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> deleteUser(@PathVariable long id) {

        HashMap<String, Object> resMap = new HashMap();
        resMap.put("success", "true");
        userService.deleteUser(id);
        resMap.put("message", "User deleted successfully.");
        return ResponseEntity.ok(resMap);
    }
}
