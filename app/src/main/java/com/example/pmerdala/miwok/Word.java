package com.example.pmerdala.miwok;

/**
 * Created by PMerd_000 on 2017-10-27.
 */

public class Word {
    private final String miwokTranslation;
    private final String defaultTranslation;

    private final int imageResourceId;
    private final int soundResourceId;

    public static final int NO_IMAGE_PROVIDE = 0;
    public static final int NO_SOUND_PROVIDE = 0;

    public Word(String englishTranslation, String miwokTranslation){
        this(englishTranslation,miwokTranslation,NO_IMAGE_PROVIDE,NO_SOUND_PROVIDE);
    }
    public Word(String englishTranslation, String miwokTranslation,int soundResourceId){
        this(englishTranslation,miwokTranslation,NO_IMAGE_PROVIDE,soundResourceId);
    }

    public Word( String englishTranslation, String miwokTranslation,int imageResourceId,int soundResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = englishTranslation;
        this.imageResourceId = imageResourceId;
        this.soundResourceId = soundResourceId;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }


    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getSoundResourceId() {return soundResourceId;}

    public boolean hasImage(){
        return imageResourceId!=NO_IMAGE_PROVIDE;
    }

    public boolean hasSound() {return soundResourceId!=NO_SOUND_PROVIDE;}

    @Override
    public String toString() {
        return miwokTranslation +" - " + defaultTranslation;
    }
}
