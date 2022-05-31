package com.lab4.demo.user;

import com.lab4.demo.user.dto.UserListDTO;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.ID;
import static com.lab4.demo.UrlMapping.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public UserListDTO create(@RequestBody UserListDTO userListDTO){return userService.create(userListDTO);}

    @PatchMapping(ID)
    public UserListDTO edit(@PathVariable Long id, @RequestBody UserListDTO userListDTO){return userService.update(id,userListDTO);}

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id) {userService.delete(id);}
}
