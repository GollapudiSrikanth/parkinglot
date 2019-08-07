import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  ParkingDetails: any = [];
  check: boolean;
  lot: number;
  vehicle_number: string;
  duration: number;
  amountCal: number;
  amount: number;
  Parking: any;
  SameLot: boolean;
  PostData: boolean;
  constructor(private httpClient: HttpClient) {

  }
  ngOnInit() {

  }
  GetParkingDetails() {
    this.SameLot = false;
    if (this.duration < 60) {
      this.amount = 20;
    }
    else {
      this.amountCal = this.duration * 0.33;
      this.amount = Math.round(this.amountCal);
    }
    console.log(this.amount)
    this.postDetails().subscribe((data) => {
      // console.log("Data")
      console.log(data);
      if (data.data == "alreday data exits") {
        this.SameLot = true;
      }
      else {
        this.SameLot = false;
      }
      this.PostData = true;
      this.details().subscribe((data) => {
        console.log(data);
        this.ParkingDetails = data;
        this.check = true;
      })
    },
      (error) => {
        console.log(error);
        this.PostData = true;
        this.details().subscribe((data) => {
          console.log(data);
          this.ParkingDetails = data;
          this.check = true;

        })
      })

  }
  details(): Observable<any> {
    let url = "http://localhost:8080/api/parkings";
    return this.httpClient.get(url);
  }
  postDetails(): Observable<any> {
    let url = "http://localhost:8080/api/parkings";
    this.Parking = {
      'lot': this.lot,
      'vehicle_number': this.vehicle_number,
      'parking_duration': this.duration,
      'parking_amount': this.amount
    }

    return this.httpClient.post(url, this.Parking)
  }
}
