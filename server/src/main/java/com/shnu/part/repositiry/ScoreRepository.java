package com.shnu.part.repositiry;

import com.shnu.part.domain.Score;
import com.shnu.part.domain.UserJob;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score,Long> {
}
