import { Component, OnInit } from '@angular/core';
import { Post } from 'app/model/post';
import { Topic } from 'app/model/topic';

@Component({
    selector: 'home',
    templateUrl: './home.html',
})

export class HomeComponent implements OnInit {

    topics: Topic[] = [];

    ngOnInit() {
        this.createDummyData();
    }

    addTopic(title: string, text: string, email: string): void {
        if (title.trim().length == 0 || text.trim().length == 0 || email.trim().length == 0) {
            alert("Please fill all the details");
            return;
        }
        this.topics.splice(0, 0, new Topic(title, text, email));
    }

    getPosts(topic: Topic): Post[] {
        return topic.posts;
    }

    addPost(topic: Topic, email: string, text: string): void {
        if (text.trim().length == 0 || email.trim().length == 0) {
            alert("Please fill all the details");
            return;
        }
        topic.posts.push(new Post(email, text));
    }

    getNumberOfPosts(topic: Topic): number {
        return topic.posts.length;
    }

    private createDummyData(): void {
        for (var i = 0; i < 4; i++) {
            var topic: Topic = new Topic("Title " + (i + 1), "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "singh" + (i + 1) + "@gmail.com");

            for (var j = i; j < i + 3; j++) {
                topic.posts.push(new Post("user" + (j + 1) + "@gmail.com", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
            }
            this.topics.push(topic);
        }
    }
}