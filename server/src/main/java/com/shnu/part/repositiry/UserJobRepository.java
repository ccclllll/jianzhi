package com.shnu.part.repositiry;

import com.shnu.part.domain.UserJob;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserJobRepository extends CrudRepository<UserJob,Long> {
    List<UserJob> findAllByUserId(Long userId);
    List<UserJob> findAllByJobId(Long jobId);
}
