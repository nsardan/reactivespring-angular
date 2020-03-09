import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http:HttpClient){}

   private baseURL:string = 'http://localhost:8080';
   private reservationURL:string = this.baseURL + '/room/v1/reservation';

   getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.reservationURL);
   }
   createReservation(body: ReservationRequest): Observable<Reservation>{
    let httpOptions= {
      headers: new HttpHeaders({'Content-Type' : 'application/json'})
    };
    return this.http.post<Reservation>(this.reservationURL, body, httpOptions);
   }
}

export class ReservationRequest{
  roomNumber: number;
  checkIn: string;
  checkOut: string;
  price: number;

  constructor(roomNumber: number, checkIn: string, checkOut:string, price: number){
  this.roomNumber=roomNumber;
  this.checkIn=checkIn;
  this.checkOut=checkOut;
  this.price=price;
  }
}

export interface Reservation{
  id: string;
  roomNumber: number;
  checkIn: Date;
  checkOut: Date;
  price: number;

}
