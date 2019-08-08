import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  ParkingDetails: any = [];
  check: boolean;
  ParkingForm: FormGroup;
  amountCal: number;
  AmountCal: number;
  Parking: any;
  SameLot: boolean;
  PostData: boolean;
  submit: boolean;
  constructor(private httpClient: HttpClient, private formBuilder: FormBuilder) {

  }
  ngOnInit() {
    this.ParkingForm = this.formBuilder.group({
      lot: new FormControl('', Validators.required),
      vehicle_number: new FormControl('', Validators.required),
      duration: new FormControl('', Validators.required),
      amount: new FormControl('')
    })

  }
  GetParkingDetails() {
    this.submit = true;
    this.SameLot = false;
    //var du=this.duration;
    if (this.ParkingForm.value.duration <= 60 && this.ParkingForm.value.duration != 0) {
      this.ParkingForm.value.amount = '20';
    }
    else {
      this.amountCal = this.ParkingForm.value.duration * 0.33;
      this.AmountCal = Math.round(this.amountCal);
      this.ParkingForm.value.amount = this.AmountCal;
    }
    console.log(this.ParkingForm.value.amount);
    if (this.ParkingForm.value.lot != 0 && this.ParkingForm.value.vehicle_number != 0 && this.ParkingForm.value.duration != 0) {
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
  }
  details(): Observable<any> {
    let url = "http://localhost:8080/api/parkings";
    return this.httpClient.get(url);
  }
  postDetails(): Observable<any> {
    let url = "http://localhost:8080/api/parkings";
    this.Parking = {
      'lot': this.ParkingForm.value.lot,
      'vehicle_number': this.ParkingForm.value.vehicle_number,
      'parking_duration': this.ParkingForm.value.duration,
      'parking_amount': this.ParkingForm.value.amount
    }

    return this.httpClient.post(url, this.Parking)
  }
}
