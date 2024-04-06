import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  // styleUrls: ['./video-player.component.css']
})
export class VideoPlayerComponent implements OnInit {
  videoUrl: string = '';

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchVideoUrl().subscribe(blob => {
      this.videoUrl = URL.createObjectURL(blob);
    });
  }

  fetchVideoUrl(): Observable<Blob> {
    return this.http.get('http://localhost:8082/video/5.mkv', { responseType: 'blob' });
  }
}
