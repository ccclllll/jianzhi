// private Long id;
//
// private String username;
//
// private String password;
//
// private String sex;
//
// private String school;
//
// private String bornDate;
//
//
//
// private String email;
//
// private Integer state;
//
// private Long img;

export class User{
    id: number;
    username: string;
    password: string;
    sex: string;
    school: string;
    bornDate: string;
    email: string;
    state: number;
    img: number;
    constructor (arg?: {
        id: number,
        username: string,
        password: string,
        sex: string,
        school: string,
        bornDate: string,
        email: string,
        state: number,
        img: number
    }){

        if(!arg){
            return;
        }
        this.id = arg.id;
        this.bornDate = arg.bornDate;
        this.username = arg.username;
        this.email = arg.email;
        this.password = arg.password;
        this.school = arg.school;
        this.img = arg.img;
        this.state = arg.state;
        this.sex = arg.sex;
    }
}
