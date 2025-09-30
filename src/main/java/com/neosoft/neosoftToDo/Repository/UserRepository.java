package com.neosoft.neosoftToDo.Repository;

import com.neosoft.neosoftToDo.Entity.Userss;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Userss, Long> {
    Optional<Userss> findByUsername(String username);
}
