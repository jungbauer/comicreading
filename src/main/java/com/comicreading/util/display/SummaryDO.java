package com.comicreading.util.display;

import com.comicreading.domain.Comic;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SummaryDO {

    private Map<String, List<Comic>> map = new LinkedHashMap<>();

    public SummaryDO() {

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

     /*
        see src/main/resources/static/css/updatedColour.css for colours.
     * */
    public String getUpdatedColour(Comic comic) {
        if (comic.getUpdated() == null) return "background-color: magenta;";
        long diffDays = ChronoUnit.DAYS.between(comic.getUpdated(), ZonedDateTime.now());
        if (diffDays <= 1) return "background-color: dimgrey;";
        else if (diffDays < 7) return "background-color: goldenrod;";
        else if (diffDays < 10) return "background-color: darkgreen;";
        else return "background-color: darkslategrey";
    }
}
