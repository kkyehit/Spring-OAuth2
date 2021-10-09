package com.practise.oauth.model.imple;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

/**
 * UserDetails 인터페이스 : Security에서 사용자의 정보를 담는 인터페이스 
 */

@Getter
@Setter
@Entity(name = "user_tb")
public class UserDetailsImpl implements UserDetails{
    /* UserDetails */
    private String Username;
    private String Password;
    private String Authority;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    /* *********** */

    /* Custom */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;
    /* ****** */

    @Override
    public java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities(){
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(Authority));
        return auth;
    }
}
