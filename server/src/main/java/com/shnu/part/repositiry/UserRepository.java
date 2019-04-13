package com.shnu.part.repositiry;

import com.shnu.part.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findUserByEmail(String email);


}
