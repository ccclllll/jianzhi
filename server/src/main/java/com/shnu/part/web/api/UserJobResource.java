package com.shnu.part.web.api;

import com.shnu.part.domain.UserJob;
import com.shnu.part.service.UserJobService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/api")
@RestController
public class UserJobResource {
    @Autowired
    private UserJobService userJobService;
    @GetMapping("/userjobs")
    @ApiOperation(value = "根据用户id查找用户参与的兼职")
    public ResponseEntity<List<UserJob>> userJobs(@RequestParam Long userId){
        return new ResponseEntity<>(userJobService.userJobs(userId), HttpStatus.OK);
    }

    @GetMapping("jobusers")
    @ApiOperation(value = "根据jobId获取参与该兼职的所有用户")
    public ResponseEntity<List<UserJob>> jobUsers(@RequestParam Long jobId){
        return new ResponseEntity<>(userJobService.jobUsers(jobId),HttpStatus.OK);
    }
}
