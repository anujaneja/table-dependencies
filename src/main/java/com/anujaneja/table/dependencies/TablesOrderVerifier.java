package com.anujaneja.table.dependencies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablesOrderVerifier {

    public static boolean verifyTablesOrder(List<String> tableOrders, HashMap<String,ArrayList<String>> dependenciesList) {
        System.out.println("tableOrders>> "+tableOrders);
        System.out.println("dependenciesList>> "+dependenciesList);

        for(Map.Entry<String,ArrayList<String>> entry:dependenciesList.entrySet()){
            String key = entry.getKey();
            ArrayList<String> childList =  entry.getValue();

            int keyIndex = tableOrders.indexOf(key);

            for(String child:childList) {
                int childIndex = tableOrders.indexOf(child);
                if(keyIndex<childIndex) {
                    System.out.println("Dependency issue found for: "+key+ " for child dependency: "+child);
                    return false;
                }

            }
        }
        return true;
    }
}
