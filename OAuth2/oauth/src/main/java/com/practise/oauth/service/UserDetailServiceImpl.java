package com.practise.oauth.service;

import com.practise.oauth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 유저 정보를 가져오는 역할
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{
    
    @Autowired
    UserRepository userRepository;

    /**
     * userRepository에서 username에 맞는 사용자 정보를 가져와 반환한다.
     * 사용자가 없으면 UsernameNotFoundException을 발생시킨다.
     * @param username
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username+"은 존재하지 않습니다."));
    }

}
