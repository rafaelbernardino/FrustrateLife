package com.bernardino.frustratelife.persistence.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rafael on 14/03/2017.
 */

public class Frustration implements Parcelable {
    private Integer idFrustration;
    private String title;
    private String description;
    private String whatToDo;

    public Frustration() {

    }

    protected Frustration(Parcel in) {
        idFrustration = in.readInt();
        title = in.readString();
        description = in.readString();
        whatToDo = in.readString();
    }

    public static final Creator<Frustration> CREATOR = new Creator<Frustration>() {
        @Override
        public Frustration createFromParcel(Parcel in) {
            return new Frustration(in);
        }

        @Override
        public Frustration[] newArray(int size) {
            return new Frustration[size];
        }
    };

    public Integer getIdFrustration() {
        return idFrustration;
    }

    public void setIdFrustration(Integer idFrustration) {
        this.idFrustration = idFrustration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhatToDo() {
        return whatToDo;
    }

    public void setWhatToDo(String whatToDo) {
        this.whatToDo = whatToDo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idFrustration);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(whatToDo);
    }
}
