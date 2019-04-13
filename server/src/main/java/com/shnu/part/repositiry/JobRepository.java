package com.shnu.part.repositiry;

import com.shnu.part.domain.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobRepository extends CrudRepository<Job,Long>{
   List<Job> findAllByUserId(Long id);

}
