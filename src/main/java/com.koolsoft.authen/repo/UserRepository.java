package com.koolsoft.authen.repo;


import com.koolsoft.authen.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    long save(User user ) throws Exception;
}
