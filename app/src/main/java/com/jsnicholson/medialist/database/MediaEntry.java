package com.jsnicholson.medialist.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = DatabaseConstants.TABLE_MEDIA_ENTRY)
public class MediaEntry {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseConstants.MEDIA_ENTRY_ID)
    public int id;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.MEDIA_ENTRY_ID_EXTERNAL)
    public int idExternal;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.MEDIA_ENTRY_STATUS)
    public int status;

    @ColumnInfo(name = DatabaseConstants.MEDIA_ENTRY_RATING)
    public int rating;

    @Embedded
    @NonNull
    public MediaDetails details = new MediaDetails();
}
