package com.susmith.ecomapp.Repository;

import com.susmith.ecomapp.Entity.Role;
import com.susmith.ecomapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findAllByRole(Role role);

    Optional<User> findByname(String name);

}
