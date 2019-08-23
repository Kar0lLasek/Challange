package elaskar.example.challange;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Person implements Serializable, Parcelable {

    String firstName;
    String number;
    String page;
    String city;
    String address;
    String color;

    public Person(String firstName, String number, String page, String city, String address, String color) {
        this.firstName = firstName;
        this.number = number;
        this.page = page;
        this.city = city;
        this.address = address;
        this.color = color;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
