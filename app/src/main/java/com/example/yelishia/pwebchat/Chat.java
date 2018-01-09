package com.example.yelishia.pwebchat;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Yelishia on 1/2/2018.
 */

public class Chat implements Parcelable {
    private User sender;
    private String pesan;
    private Long tanggal;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference chatRef = database.getReference("chats");

    void send(){ chatRef.push().setValue(this);     }

    public void Chat(){

    }


    //getter dan setter
    public User getSender() {return sender; }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public Long getTanggal() {
        return tanggal;
    }

    public void setTanggal(Long tanggal) {
        this.tanggal = tanggal;
    }



    //parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.sender, flags);
        dest.writeString(this.pesan);
        dest.writeValue(this.tanggal);
    }

    public Chat() {
    }

    protected Chat(Parcel in) {
        this.sender = in.readParcelable(User.class.getClassLoader());
        this.pesan = in.readString();
        this.tanggal = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Chat> CREATOR = new Parcelable.Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel source) {
            return new Chat(source);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };
}
