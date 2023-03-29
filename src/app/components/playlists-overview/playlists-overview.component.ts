import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-playlists-overview',
  templateUrl: './playlists-overview.component.html',
  styleUrls: ['./playlists-overview.component.scss']
})
export class PlaylistOverviewComponent implements OnInit{

  constructor() {
  }

  ngOnInit(): void {
    this.clearAccessTokenFromUrl();
  }

  clearAccessTokenFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    urlParams.delete('accessToken');
    const newUrl = window.location.origin + window.location.pathname + '?' + urlParams.toString();
    window.history.replaceState({}, '', newUrl);
  }


}
