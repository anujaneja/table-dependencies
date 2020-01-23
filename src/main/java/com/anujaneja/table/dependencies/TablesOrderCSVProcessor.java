package com.anujaneja.table.dependencies;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class TablesOrderCSVProcessor implements CSVProcessor {
    private HashMap<String, ArrayList<String>> dependenciesList = new HashMap<>();

    public HashMap<String, ArrayList<String>> getDependenciesList() {
        return dependenciesList;
    }

    @Override public void processCSV(String filePath) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

        ) {


            for (CSVRecord csvRecord : csvParser) {
                processRow(csvRecord);
            }
        }

    }

    @Override
    public void processRow(CSVRecord csvRecord) throws IOException {
        String dependent = csvRecord.get(0);
        String dependee = csvRecord.get(1);

        if(dependenciesList.get(dependee)==null) {
            ArrayList<String> dependentList = new ArrayList<>();
            dependentList.add(dependent);

            dependenciesList.put(dependee,dependentList);
        } else {
            ArrayList<String> dependentList = dependenciesList.get(dependee);
            dependentList.add(dependent);
        }
    }
}
