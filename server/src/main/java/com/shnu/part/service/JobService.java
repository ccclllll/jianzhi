package com.shnu.part.service;

import com.shnu.part.domain.Job;
import com.shnu.part.repositiry.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;
    public List<Job> userPost(Long userId){
        return jobRepository.findAllByUserId(userId);
    }

    public Job saveJob(Job job){
        return jobRepository.save(job);
    }

    public void deleteJob(Long jobId){
        jobRepository.deleteById(jobId);
    }

}
