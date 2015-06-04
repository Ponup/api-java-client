package com.ponup.api;

import java.util.Date;

public class Score {

    private String gameName;
    private String playerName;
    private int value;
    private Date updateTime;
    private int gameLevelNumber;

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getGameLevelNumber() {
        return gameLevelNumber;
    }

    public void setGameLevelNumber(int gameLevelNumber) {
        this.gameLevelNumber = gameLevelNumber;
    }
}
