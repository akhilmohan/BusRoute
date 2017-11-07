package com.tripplanner.busroute.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by akhilmohan on 07/11/17.
 */

public class Stop implements Parcelable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    }

    public static final Parcelable.Creator<Stop> CREATOR
            = new Parcelable.Creator<Stop>() {
        public Stop createFromParcel(Parcel in) {
            return new Stop(in);
        }

        public Stop[] newArray(int size) {
            return new Stop[size];
        }
    };

    private Stop(Parcel in) {
        name = in.readString();
    }
}
