export interface PostWebSocketDto {
  id: number;
  dateTime: Date;
  title: string;
  link: string;
  wordCountMap: Map<String, number>;
}
