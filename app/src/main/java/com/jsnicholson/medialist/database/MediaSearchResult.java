package com.jsnicholson.medialist.database;

public class MediaSearchResult implements Comparable<MediaSearchResult> {

    public String type;
    public long idExternal;
    public String title;
    public int released;
    public float popularity;
    public boolean isAnime;

    public MediaSearchResult(String type, int idExternal, String title, int released, int popularity, boolean isAnime) {
        this.type = type;
        this.idExternal = idExternal;
        this.title = title;
        this.released = released;
        this.popularity = popularity;
        this.isAnime = isAnime;
    }

    public Float GetPopularity() { return popularity; }

    @Override
    public int compareTo(MediaSearchResult x) {
        return this.GetPopularity().compareTo(x.GetPopularity());
    }
}
