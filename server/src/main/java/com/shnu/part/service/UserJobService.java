package com.shnu.part.service;

import com.shnu.part.domain.UserJob;
import com.shnu.part.repositiry.UserJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserJobService {
    @Autowired
    private UserJobRepository userJobRepository;
    public UserJob saveUserJob(UserJob userJob){
        return userJobRepository.save(userJob);
    }


    public List<UserJob> jobUsers(Long jobId){
        return userJobRepository.findAllByJobId(jobId);
    }

    public List<UserJob> userJobs(Long userId){
        return userJobRepository.findAllByUserId(userId);
    }
}
