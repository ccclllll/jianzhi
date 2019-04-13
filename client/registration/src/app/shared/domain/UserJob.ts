import {Job} from "./Job";
import {User} from "./User";

export class UserJob{
    id: number;
    job: Job;
    user: User;
    state: number;
    constructor(arg?:{
        id: number,
        job: Job,
        user: User,
        state: number,
    }){
        this.id = arg.id;
        this.job = arg.job;
        this.user = arg.user;
        this.state = arg.state;
    }
}
// private Long id;
// @ManyToOne
// private Job job;
// @ManyToOne
// User user;
// private Integer state; // 0 未开始 1进行中 2已结束
//
// private Double score;