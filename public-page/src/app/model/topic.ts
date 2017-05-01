import { Post } from 'app/model/post';

export class Topic {

    title: string;
    text: string;
    email: string
    date: Date;
    posts: Post[];

    constructor(title: string, text: string, email: string) {
        this.title = title;
        this.text = text;
        this.email = email;
        this.date = new Date();
        this.posts = [];
    }
}