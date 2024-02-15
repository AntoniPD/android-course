package com.example.android_lesson_7.model;

import java.util.List;

public class SWResponse {
    private int count;
    private List<SWCharacter> results;

    public SWResponse(int count, List<SWCharacter> results) {
        this.count = count;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SWCharacter> getResults() {
        return results;
    }

    public void setResults(List<SWCharacter> results) {
        this.results = results;
    }
}
