import {PostWebSocketDto} from "./post-web-socket-dto.model";

export interface PostsWebSocketDto {
  posts: PostWebSocketDto[];
  somethingChanged: boolean;
}
