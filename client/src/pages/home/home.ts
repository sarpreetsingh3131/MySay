import { Component, OnInit } from '@angular/core';
import { NavController } from 'ionic-angular';
import { ApiService } from './api';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage implements OnInit {

  private comments: any = [];

  constructor(public navCtrl: NavController, private api: ApiService) {
  }

  ngOnInit() {
    this.api.getData("http://localhost:9090/").subscribe(result => { this.comments = result; });
  }

  doRefresh(refresher) {
    setTimeout(() => {
      this.api.getData("http://localhost:9090/?before=" + this.comments[0].id).subscribe(result => {
        result.forEach(element => {
          this.comments.splice(0, 0, element);
        });
      });
      refresher.complete();
    }, 500);
  }

  loadMore(infiniteScroll) {
    setTimeout(() => {
      this.api.getData("http://localhost:9090/?after=" + this.comments[this.comments.length - 1].id).subscribe(result => {
        result.forEach(element => {
          this.comments.push(element);
        });
      });
      infiniteScroll.complete();
    }, 500);
  }
}