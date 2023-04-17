package mocks;

import java.util.ArrayList;

public interface Database {

    void recordScore(String player, int score);

    int getPlayerScore(String name);

    ArrayList<String> getPlayerList();

}
