package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.User;
import com.turkuazgame.btlig.exception.NotFoundException;
import com.turkuazgame.btlig.repository.UserRepository;
import com.turkuazgame.btlig.request.UserRequest;
import com.turkuazgame.btlig.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;

//    PasswordEncoder passwordEncoder;

//    FileService fileService;

    public UserService(UserRepository userRepository/*, PasswordEncoder passwordEncoder, FileService fileService*/) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.fileService = fileService;
    }

    public List<UserResponse> getAllUsers(){
        List<UserResponse> responseList;
        List<User> users = userRepository.findAll();
        responseList = users.stream().map(usr -> new UserResponse(usr)).collect(Collectors.toList());
        return responseList;
    }

    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        UserResponse response = new UserResponse(user);
        return response;
    }

    public UserResponse createUser(@Valid UserRequest userRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse(newUser);
        return userResponse;
    }

    public UserResponse updateUser(Long userId, @Valid UserRequest userRequest) {
        User newUser = new User(userRequest);

        Optional<User> findUser = userRepository.findById(userId);
        if(findUser.isPresent()) {
            User user = findUser.get();
            user.setFromOther(newUser);
            userRepository.save(user);
            UserResponse userResponse = new UserResponse(newUser);
            return  userResponse;
        }
        else
            return null;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    
    public void save(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Page<User> getUsers(Pageable page, User user) {
        if(user != null) {
            return userRepository.findByUsernameNot(user.getUsername(), page);
        }
        return userRepository.findAll(page);
    }

    public User getByUserName(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new NotFoundException();
        }
        return user;
    }

//    public User updateUser(String username, UserUpdateVM updatedUser) {
//        User inDB = getByUsername(username);
//        inDB.setDisplayName(updatedUser.getDisplayName());
//        if(updatedUser.getImage() != null) {
//            String oldImageName = inDB.getImage();
//            try {
//                String storedFileName = fileService.writeBase64EncodedStringToFile(updatedUser.getImage());
//                inDB.setImage(storedFileName);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            fileService.deleteProfileImage(oldImageName);
//        }
//        return userRepository.save(inDB);
//    }
//
//    public void deleteUser(String username) {
//        User inDB = userRepository.findByUsername(username);
//        fileService.deleteAllStoredFilesForUser(inDB);
//        userRepository.delete(inDB);
//    }



}