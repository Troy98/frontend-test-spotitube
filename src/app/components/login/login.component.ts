import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login/login.service';
import {Settings} from '../../models/settings/settings.interface.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user: string;
  public password: string;
  public serverUrl: string;

  constructor(private loginService: LoginService) {
  }


  ngOnInit() {
    this.loginService.getSettings()
      .then(settings => this.serverUrl = settings.server)
      .catch(any => this.serverUrl = '');
    this.loginService.settingsChanged$.subscribe(settings => this.setServer(settings));
    this.checkAccessToken();
  }

  public login(): void {
    this.loginService.login(this.user, this.password, this.serverUrl)
  }

  public hanLogin(): void {
    return this.loginService.hanLogin();
  }

  checkAccessToken() {
    console.log("checkAccessToken()");
    // Check if accessToken is present in the URL
    const urlParams = new URLSearchParams(window.location.search);
    const accessToken = urlParams.get('accessToken');
    if (accessToken) {
      // Use loginWithToken() function if this if statement is true
      console.log("accessToken is present");
      this.loginWithToken();
    }
  }

  public loginWithToken(): void {
    return this.loginService.loginWithToken();
  }

  private setServer(settings: Settings): void {
    if (settings) {
      this.serverUrl = settings.server;
    }
  }
}
