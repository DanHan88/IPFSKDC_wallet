package com.ipfs.kdc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NanoDCService {

    public List<String> getPrometheusData(String endpointUrl) throws IOException {
        URL url = new URL(endpointUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        
        int responseCode = connection.getResponseCode();
        List<String> fullData = new ArrayList<String>();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	fullData.add(inputLine);
            }
            in.close();
            return fullData;
        } else {
            // Handle error cases
            throw new IOException("Failed to fetch data from Prometheus endpoint. Response code: " + responseCode);
        }
    }
    
    public double processPrometheusData(List<String> data) {

    	double totalPower = 0;
        for(int i=0;i<data.size();i++) {
        	String line = data.get(i);
        	
        	if(line.indexOf("lotus_miner_sector_qa_power")>-1) {
        		String value =line.split(" ")[1];
        		if (value.matches("-?\\d+(\\.\\d+)?([eE][-+]?\\d+)?")) {
        			//String[] splitNum = value.split("e+");
        			value= value.replace("e+", "E");
        			//double power = Double.parseDouble(splitNum[0])*Math.pow(10, Double.parseDouble(splitNum[1]));
        			totalPower += Double.parseDouble(value);
        		}
        	}else if(line.indexOf("lotus_miner_sector_state")>-1) {
        		String[] value =line.split("[,\\}\\{]");
        		System.out.println("Total Power: " + totalPower);
        	}
        }
        System.out.println("Total Power: " + totalPower);
        return totalPower;
    }
}
