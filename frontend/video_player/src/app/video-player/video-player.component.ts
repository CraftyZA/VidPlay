import { Component, OnInit, ElementRef } from '@angular/core';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
})
export class VideoPlayerComponent implements OnInit {
  videoUrl: string = '';

  constructor(private elementRef: ElementRef) { }

  ngOnInit(): void {
    this.videoUrl = this.fetchVideoUrl();
  }

  fetchVideoUrl(): string {
    //Placeholder. This will eventually be handled by the backend.
    return 'https://www.zwarries96.co.za/sample.mp4';
  }

  toggleFullscreen(): void {
    const videoContainer = this.elementRef.nativeElement.querySelector('#videoContainer');
    const videoPlayer = this.elementRef.nativeElement.querySelector('#videoPlayer');

    if (videoContainer.requestFullscreen) {
      videoContainer.requestFullscreen();
    } else if (videoContainer.mozRequestFullScreen) { /* Firefox */
      videoContainer.mozRequestFullScreen();
    } else if (videoContainer.webkitRequestFullscreen) { /* Chrome, Safari & Opera */
      videoContainer.webkitRequestFullscreen();
    } else if (videoContainer.msRequestFullscreen) { /* IE/Edge */
      videoContainer.msRequestFullscreen();
    }

    if (videoPlayer.requestFullscreen) {
      videoPlayer.requestFullscreen();
    } else if (videoPlayer.mozRequestFullScreen) { /* Firefox */
      videoPlayer.mozRequestFullScreen();
    } else if (videoPlayer.webkitRequestFullscreen) { /* Chrome, Safari & Opera */
      videoPlayer.webkitRequestFullscreen();
    } else if (videoPlayer.msRequestFullscreen) { /* IE/Edge */
      videoPlayer.msRequestFullscreen();
    }
  }
}

