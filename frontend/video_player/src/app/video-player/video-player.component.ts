import { Component, OnInit, ElementRef, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import {catchError, Observable, of, Subscription} from 'rxjs';


interface VideoPathRequest {
  path: string;
}

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
})
export class VideoPlayerComponent implements OnInit, OnDestroy {
  videoSource$: Observable<null | ArrayBuffer>;
  subscription: Subscription | undefined;

  constructor(private elementRef: ElementRef, private http: HttpClient) {
    this.videoSource$ = of(null);
    console.log(this.http);
  }

  ngOnInit(): void {
    this.fetchVideo();
  }

  fetchVideo(): void {
    const videoPathRequest: VideoPathRequest = { path: 'd:\\test\\movies\\the jazz singer.avi' };

    this.videoSource$ = this.http.post<ArrayBuffer>('http://localhost:8082/media/stream', videoPathRequest, { responseType: 'json' })
      .pipe(
        catchError(error => {
          console.error('Error fetching video:', error);
          return of(null);
        })
      );
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
