import {User} from "./User";

export class Job{
    id: number;
    jobName: string;
    jobState: string;
    jobDescribe: string;
    jobSalary: string;
    user: User;
    constructor(arg?:{
        id: number,
        jobName: string,
        jobState: string,
        jobDescribe: string,
        jobSalary: string,
        user: User,
    }){
        if(!arg){
            return;
        }

        this.id = arg.id;
        this.jobDescribe = arg.jobDescribe;
        this.jobName = arg.jobName;
        this.jobSalary = arg.jobSalary;
        this.jobState = arg.jobState;
        this.user = arg.user;
    }

}
// private Long id;
// private String jobName;
// private String jobState;
// // 更新时间
// private String updateTime;
// private String jobDescribe;
// private String jobSalary;
// @ManyToOne
// private User user;