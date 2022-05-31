package com.lab4.demo.user;

import com.lab4.demo.user.dto.UserListDTO;
import com.lab4.demo.user.dto.UserMinimalDTO;
import com.lab4.demo.user.mapper.UserMapper;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public UserListDTO create(UserListDTO userListDTO){
        return userMapper.toDTO(userRepository.save(userMapper.fromDTO(userListDTO)));
    }

    public UserListDTO update(Long id, UserListDTO userListDTO){
        User user = findById(id);
        user.setEmail(userListDTO.getEmail());
        user.setUsername(userListDTO.getName());
        user.setPassword(encoder.encode(userListDTO.getPassword()));
        user.setRoles(userListDTO.getRoles());
        return userMapper.toDTO(userRepository.save(user));

    }


    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }
}
