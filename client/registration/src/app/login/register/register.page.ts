import { Component, OnInit } from '@angular/core';
import {NavController, NavParams} from "@ionic/angular";
import {AuthService} from "../../shared/services/auth.service";
import {User} from "../../shared/domain/User";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage{

    user: any = new User();

    constructor(public navCtrl: NavController, public auth: AuthService,public router: Router) {
    }

    ionViewDidLoad() {
        console.log('ionViewDidLoad RegisterPage');
    }

    doRegister() {
        if (this.check()) {
            this.auth.register(this.user).subscribe(it=>{
                console.log(it);
                if(it!==null){
                    alert('注册成功');
                    this.gotoLogin();
                }else {
                    alert('注册失败，服务器错误或邮箱重复');
                }
            })
        }
    }

    check(): boolean {
        console.log(this.user)
        let ready = true;
        if (!this.user.email) {
            ready = false;
            alert('请输入昵称');
        } else if (!this.user.email) {
            ready = false;
            alert('请输入邮箱');
        } else if (!this.user.password) {
            ready = false;
            alert('请输入密码');
        }else if(!this.isEmail()){
            ready = false;
            alert('邮箱格式不正确');
        }
        return ready;
    }

    gotoLogin(){
        this.router.navigateByUrl('');
    }

    isEmail():boolean{
        const reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        let isok= reg.test(this.user.email);
        console.log(isok)
        if(!isok){
            return false;
        }
        return true;
    }

}
