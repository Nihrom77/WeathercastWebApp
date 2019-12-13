package com.croc.webapp.domain;

import java.time.LocalDate;
import java.util.List;

public class ChartModel {
    private  Integer id;
    private List<LocalDate> dates;
    private List<Integer> metric;
    private Metric metricName;

    public ChartModel(Integer id, List<LocalDate> dates, List<Integer> metrics, Metric metric){
        this.id = id;
        this.dates = dates;
        this.metric = metrics;
        this.metricName = metric;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Metric getMetricName() {
        return metricName;
    }

    public void setMetricName(Metric metricName) {
        this.metricName = metricName;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }

    public List<Integer> getMetric() {
        return metric;
    }

    public void setMetric(List<Integer> metric) {
        this.metric = metric;
    }
}
