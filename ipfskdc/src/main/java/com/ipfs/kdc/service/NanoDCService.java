package com.ipfs.kdc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipfs.kdc.mapper.NanoDCMapper;
import com.ipfs.kdc.vo.SectorInfoVO;

@Service
public class NanoDCService {
	 
	
	@Autowired
	NanoDCMapper nanoDCMapper;
	
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
    	//Date now = new Date(System.currentTimeMillis());
    	List <SectorInfoVO> sectorVOList = new ArrayList<SectorInfoVO>();
        for(int i=0;i<data.size();i++) {
        	System.out.println("Processing line :" + i +"/"+ data.size());
        	String line = data.get(i);
        	SectorInfoVO sectorInfoVO = new SectorInfoVO();
        	Boolean isSectorInfo = false;
        	if(line.indexOf("lotus_miner_sector_qa_power")>-1) {
        		String piece =line.split(" ")[1];
        		if (piece.matches("-?\\d+(\\.\\d+)?([eE][-+]?\\d+)?")) {
        			piece= piece.replace("e+", "E");
        			totalPower += Double.parseDouble(piece);
        			isSectorInfo = true;
        		}
        	}else if(line.indexOf("lotus_miner_sector_state")>-1) {
        		isSectorInfo = true;
        	}else if(line.indexOf("lotus_miner_deadline_active_partition_sector")>-1) {
        		isSectorInfo = true;
        	}else if(line.indexOf("lotus_miner_sector_event")>-1) {
        		isSectorInfo = false;
        	}
        	if(isSectorInfo) {
        		sectorInfoVO = parseInputString(line);
        		if(!sectorInfoVO.isEmpty()) {
        			if(nanoDCMapper.checkSectorExists(sectorInfoVO)) {
        				nanoDCMapper.updateNewSector(sectorInfoVO);
        			}else {
        				nanoDCMapper.addNewSector(sectorInfoVO);
        			}	
        		}
        	}
        }
        return totalPower;
    }
    
    public static SectorInfoVO parseInputString(String input) {
    	SectorInfoVO sectorInfo = new SectorInfoVO();
        
        Pattern pattern = Pattern.compile("(\\w+)=\"(.*?)\"");
        Matcher matcher = pattern.matcher(input);
        
        while (matcher.find()) {
            String fieldName = matcher.group(1);
            String fieldValue = matcher.group(2);
            if ("miner_id".equals(fieldName)) {
                sectorInfo.setMiner_id(fieldValue);
            } else if ("sector_id".equals(fieldName)) {
                sectorInfo.setSector_id(fieldValue);
            } else if ("weight_type".equals(fieldName)) {
                sectorInfo.setWeight_type(fieldValue);
            } else if ("pledged".equals(fieldName)) {
                sectorInfo.setPledged(fieldValue);
            } else if ("deals".equals(fieldName)) {
                sectorInfo.setDeals(fieldValue);
            } else if ("pledged".equals(fieldName)) {
                sectorInfo.setPledged(fieldValue);
            } else if ("state".equals(fieldName)) {
                sectorInfo.setState(fieldValue);
            } else if ("to_upgrade".equals(fieldName)) {
                sectorInfo.setTo_upgrade(fieldValue);
            } else if ("event_type".equals(fieldName)) {
                sectorInfo.setEvent_type(fieldValue);
            } else if ("deadline_id".equals(fieldName)) {
                sectorInfo.setDeadline_id(fieldValue);
            } else if ("partition_id".equals(fieldName)) {
                sectorInfo.setPartition_id(fieldValue);
            } else if ("is_faulty".equals(fieldName)) {
                sectorInfo.setIs_faulty(fieldValue);
            } else if ("is_live".equals(fieldName)) {
                sectorInfo.setIs_live(fieldValue);
            } else if ("is_recovering".equals(fieldName)) {
                sectorInfo.setIs_recovering(fieldValue);
            } 
        }
        return sectorInfo;
    }  
}
