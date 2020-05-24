package com.randikalakmal.springreddit.repository;

import com.randikalakmal.springreddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
