package com.comicreading.util.display;

import com.comicreading.domain.Comic;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Slf4j
public class SummaryDO {

    private Map<String, List<Comic>> map = new LinkedHashMap<>();

    public SummaryDO() {
        map.put("READING", new ArrayList<>());
        map.put("WAITING", new ArrayList<>());
        map.put("OTHER", new ArrayList<>());
        map.put("DORMANT", new ArrayList<>());
        map.put("COMPLETE", new ArrayList<>());
        map.put("LOST INTEREST", new ArrayList<>());
    }

    public void setReading(List<Comic> reading) {
        map.put("READING", reading);
    }

    public void setWaiting(List<Comic> waiting) {
        map.put("WAITING", waiting);
    }

    public void setOther(List<Comic> other) {
        map.put("OTHER", other);
    }

    public void setDormant(List<Comic> dormant) {
        map.put("DORMANT", dormant);
    }

    public void setComplete(List<Comic> complete) {
        map.put("COMPLETE", complete);
    }

    public void setLostInterest(List<Comic> lostInterest) {
        map.put("LOST INTEREST", lostInterest);
    }

    public Map<String, List<Comic>> getMap() {
        return map;
    }

    public void addToReading(Comic reading) {
        map.get("READING").add(reading);
    }

    public void addToWaiting(Comic waiting) {
        map.get("WAITING").add(waiting);
    }

    public void addToOther(Comic other) {
        map.get("OTHER").add(other);
    }

    public void addToDormant(Comic dormant) {
        map.get("DORMANT").add(dormant);
    }

    public void addToComplete(Comic complete) {
        map.get("COMPLETE").add(complete);
    }

    public void addToLostInterest(Comic lostInterest) {
        map.get("LOST INTEREST").add(lostInterest);
    }

    public String getCategoryColourClass(String mapKey) {
        switch (mapKey) {
            case "READING" -> {return "table-primary";}
            case "WAITING" ->    {return "table-warning";}
            case "OTHER" ->      {return "table-success";}
            case "DORMANT" ->    {return "table-info";}
            case "COMPLETE" ->   {return "table-danger";}
            case "LOST INTEREST" ->  {return "table-dark";}
            default ->  {return "table-secondary";}
        }
    }

    public String getUpdatedColour(Comic comic) {
        // see src/main/resources/static/css/updatedColour.css for colours.
        if (colourBasedOnChapters(comic)) return "background-color: darkgreen;";
        else return colourBasedOnTimestamp(comic);
    }

    private boolean colourBasedOnChapters(Comic comic) {
        try {
            int currChapter = Integer.parseInt(comic.getCurrChapter());
            if (comic.getTotalChapters() > currChapter) return true;
        }
        catch (NumberFormatException nfe) {
            log.warn("colourBasedOnChapters() Number conversion error: " + comic.getCurrChapter());
            return false;
        }

        return false;
    }

    private String colourBasedOnTimestamp(Comic comic) {
        // see src/main/resources/static/css/updatedColour.css for colours.
        if (comic.getChaptersUpdated() == null) return "background-color: magenta;";
        long diffDays = ChronoUnit.DAYS.between(comic.getChaptersUpdated(), ZonedDateTime.now());
        if (diffDays <= 1) return "background-color: dimgrey;";
        else if (diffDays < 7) return "background-color: goldenrod;";
        else if (diffDays < 10) return "background-color: darkgreen;";
        else return "background-color: darkslategrey";
    }
}
