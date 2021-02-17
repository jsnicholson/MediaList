package com.jsnicholson.medialist.database;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {MediaEntry.class}, version = 1, exportSchema = false) // drop and recreate
public abstract class DatabaseMedia extends RoomDatabase {

    public abstract DaoMediaEntry daoMediaEntry();

    private static volatile DatabaseMedia INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    private static Context m_context;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DatabaseMedia getDatabase(final Context context) {
        m_context = context;
        if (INSTANCE == null) {
            synchronized (DatabaseMedia.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseMedia.class, "database_media")
                            .fallbackToDestructiveMigration()
                            .addCallback(createCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback createCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Log.d("g53mdp", "dboncreate");
            databaseExecutor.execute(() -> {
                // add some default tags

            });
        }
    };
}