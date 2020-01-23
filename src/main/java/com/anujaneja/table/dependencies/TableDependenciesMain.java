package com.anujaneja.table.dependencies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class TableDependenciesMain {

    final private static String TS_INPUT_FILE="ts-input.csv";
    final private static String TS_OUTPUT_FILE="ts-output.csv";

    public static void main(String[] args) throws Exception {
        //1. Read ts-input file to create dependencies
        TablesOrderCSVProcessor tablesOrderCSVProcessor =  new TablesOrderCSVProcessor();
        tablesOrderCSVProcessor.processCSV(getFileFromResources(TS_INPUT_FILE));
        HashMap<String,ArrayList<String>> dependenciesList = tablesOrderCSVProcessor.getDependenciesList();


        //2. Read ts-output file to read table orders for verification.
        ArrayList<String> tablesOrder = new ArrayList<>();
        File file=new File(getFileFromResources(TS_OUTPUT_FILE));
        BufferedReader reader =  new BufferedReader(new FileReader(file));
        String line = null;

        while((line=reader.readLine())!=null) {
            tablesOrder.add(line);
        }

        boolean areTablesOrdered = TablesOrderVerifier.verifyTablesOrder(tablesOrder,dependenciesList);

        System.out.println("areTablesOrdered >>> "+areTablesOrdered);
    }

    // get file from classpath, resources folder
    private static String getFileFromResources(String fileName) {

        ClassLoader classLoader = TableDependenciesMain.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return resource.getFile();
        }

    }
}
