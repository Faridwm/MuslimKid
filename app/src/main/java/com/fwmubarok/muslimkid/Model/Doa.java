package com.fwmubarok.muslimkid.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Doa implements Parcelable {
    @SerializedName("title")
    private String title;

    @SerializedName("arabic")
    private String inArabic;

    @SerializedName("latin")
    private String inLatin;

    @SerializedName("translation")
    private String translation;

    protected Doa(Parcel in) {
        title = in.readString();
        inArabic = in.readString();
        inLatin = in.readString();
        translation = in.readString();
    }

    public static final Creator<Doa> CREATOR = new Creator<Doa>() {
        @Override
        public Doa createFromParcel(Parcel in) {
            return new Doa(in);
        }

        @Override
        public Doa[] newArray(int size) {
            return new Doa[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInArabic() {
        return inArabic;
    }

    public void setInArabic(String inArabic) {
        this.inArabic = inArabic;
    }

    public String getInLatin() {
        return inLatin;
    }

    public void setInLatin(String inLatin) {
        this.inLatin = inLatin;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(inArabic);
        dest.writeString(inLatin);
        dest.writeString(translation);
    }
}
