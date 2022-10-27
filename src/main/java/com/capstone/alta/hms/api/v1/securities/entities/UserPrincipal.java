package com.capstone.alta.hms.api.v1.securities.entities;

import com.capstone.alta.hms.api.v1.accounts.entities.Account;
import com.capstone.alta.hms.api.v1.accounts.utils.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
public class UserPrincipal implements UserDetails {
    private Integer id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String username;

    private String password;

    private String email;

    private Collection<? extends GrantedAuthority> authorities;

    @JsonProperty("id_card")
    private String idCard;

    private String others;

    @JsonProperty("phone_number")
    private String phoneNumber;


    public UserPrincipal(
        Integer id,
        String firstName,
        String lastName,
        String username,
        String password,
        String email,
        Collection<? extends GrantedAuthority> authorities,
        String idCard,
        String others,
        String phoneNumber
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.idCard = idCard;
        this.others = others;
        this.phoneNumber = phoneNumber;
    }

    public static UserPrincipal create(Account account) {
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(
                account.getRole().name()));

        return new UserPrincipal(
            account.getId(),
            account.getFirstName(),
            account.getLastName(),
            account.getUsername(),
            account.getPassword(),
            account.getEmail(),
            grantedAuthorities,
            account.getIdCard(),
            account.getOthers(),
            account.getPhoneNumber()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
