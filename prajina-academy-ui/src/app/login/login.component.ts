import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required )
  });
  submitted: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() { }

  onSubmit() {
    console.log("submitted : " + this.loginForm.valid);
    this.submitted = true;
    if(this.loginForm.invalid) {
      return;
    }

    if (this.loginForm.controls['username'].value === 'philonoist' && this.loginForm.controls['password'].value === 'philonoist') {
      this.router.navigate(['/home'], { queryParams: { username: this.loginForm.controls['username'].value} });
    }
  }

}
