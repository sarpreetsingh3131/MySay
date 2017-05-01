import { Component } from '@angular/core';
import { HomeComponent } from './components/home.component';

@Component({
  selector: 'app-root',
  template: `<home></home>`,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app works!';
}
