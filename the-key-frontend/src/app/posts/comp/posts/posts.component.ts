import { Component, OnInit } from '@angular/core';
import {RxStompService} from "@stomp/ng2-stompjs";
import {Subscription} from "rxjs";
import {Message} from '@stomp/stompjs';
import {PostsWebSocketDto} from "../../dto/posts-web-socket-dto.model";
import {Post} from "../../model/post.model";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  private topicSubscription: Subscription | undefined;
  posts: Post[] = [];
  initialized = false;

  constructor(private rxStompService: RxStompService) { }

  ngOnInit(): void {
    this.topicSubscription = this.rxStompService.watch('/queue/word-count-maps').subscribe((message: Message) => {
      const incomingPosts: PostsWebSocketDto = JSON.parse(message.body);

      if(this.initialized && !incomingPosts.somethingChanged) return;


      this.posts = incomingPosts.posts.map(iP => ({
        id: iP.id,
        dateTime: iP.dateTime,
        title: iP.title,
        link: iP.link,
        wordCountMap: iP.wordCountMap
      }));

      this.initialized = true;
    });
  }

  ngOnDestroy() {
    this.topicSubscription ? this.topicSubscription.unsubscribe() : null;
  }
}
