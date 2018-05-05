package br.com.pomodoro.modal;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import br.com.pomodoro.util.StringUtil;

@Entity(tableName = "historic")
public class Historic {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "timer")
    private String timer;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "hour")
    private long hour;

    public Historic() {
    }

    @Ignore
    public Historic(String timer) {
        this.timer = timer;
        this.date = StringUtil.convertToDate(new Date());
        this.hour = new Date().getTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }
}

