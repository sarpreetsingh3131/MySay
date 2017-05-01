export class Post {

    date: Date;
    text: string;
    email: string;

    constructor(email: string, text: string) {
        this.email = email;
        this.text = text;
        this.date = new Date();
    }
}