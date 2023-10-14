package com.example.backend.service;

import com.example.backend.exception.UserException;
import com.example.backend.model.CustomUserDetail;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null) throw new UsernameNotFoundException(username);
        return new CustomUserDetail(user);
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get();

        // Thay thế UserDetails(user) bằng UserDetails đúng cách
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getFullname())
                .authorities("ROLE_USER") // Thay thế bằng danh sách vai trò thích hợp của người dùng
                .build();
        return userDetails;
    }
}
