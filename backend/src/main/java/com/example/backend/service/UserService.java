package com.example.backend.service;

import com.example.backend.model.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public interface UserService {
    public User login(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
    public boolean logout(String email);

    public User addUser(String fullname, String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
    public Boolean activateUser(String activation_code);

    public Boolean updatePassword(String email, String password);
    public Boolean updateEmail(String email, String newEmail);

    public Optional<User> findByEmail(String email);
    public User findById(String id);
}
