package com.fwmubarok.muslimkid.Model;

import com.google.gson.annotations.SerializedName;

public class Doa {
    @SerializedName("title")
    private String title;

    @SerializedName("arabic")
    private String inArabic;

    @SerializedName("latin")
    private String inLatin;

    @SerializedName("translation")
    private String translation;

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
}
