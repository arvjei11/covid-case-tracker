package com.arvjei.covidtracker.services;

import com.arvjei.covidtracker.models.LocationStatistics;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {

    private String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStatistics> allStatistics = new ArrayList<>();


    @Scheduled(cron = "* * 1 * * *")
    @PostConstruct
    public void fetchData() throws InterruptedException,IOException {
        List<LocationStatistics> newStatistics = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        //create a http request that fetches the raw covid dataset url
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(COVID_DATA_URL)).build();
        HttpResponse<String> httpResponse= client.send(request,HttpResponse.BodyHandlers.ofString());
        //header auto detection using Apache Commons CSV
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStatistics locationStatistics = new LocationStatistics();
            locationStatistics.setState(record.get("Province/State"));
            locationStatistics.setCountry(record.get("Country/Region"));
            locationStatistics.setTotalCases(Integer.parseInt(record.get(record.size()-1)));
            System.out.println(locationStatistics);
            newStatistics.add(locationStatistics);
        }
        this.allStatistics = newStatistics;

    }

}
