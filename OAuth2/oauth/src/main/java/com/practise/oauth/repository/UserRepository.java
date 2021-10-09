package com.practise.oauth.repository;

import java.util.List;
import java.util.Optional;

import com.practise.oauth.model.imple.UserDetailsImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetailsImpl, Long>{
    public Optional<UserDetailsImpl> findById(String id);
    public Optional<UserDetailsImpl> findByUsername(String username);
}
