package com.ipfs.kdc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipfs.kdc.mapper.NanoDCMapper;
import com.ipfs.kdc.vo.LotusWalletVO;
import com.ipfs.kdc.vo.NodeInfoVO;
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
            	if(inputLine.indexOf("#")==-1) {
            		fullData.add(inputLine);
            	}
            }
            in.close();
            return fullData;
        } else {
            // Handle error cases
            throw new IOException("Failed to fetch data from Prometheus endpoint. Response code: " + responseCode);
        }
    }
    
    public NodeInfoVO processPrometheusData(List<String> data) {
    		double sectorSize =0;
    		double feeDebt =0;
    	 	double initialPledge=0;
    	 	double lockedFunds=0;
    	 	double preCommiDeposits=0;
    		int mpool =0;
    		List<LotusWalletVO> lotuswalletVOList = new ArrayList<LotusWalletVO>();
    	//Date now = new Date(System.currentTimeMillis());
    	 Map<String, SectorInfoVO> sectorVOHashMap = new HashMap<>();
    	 NodeInfoVO nodeInfoVO = new NodeInfoVO();
        for(int i=0;i<data.size();i++) {
        	double qualityPower = 0;
        	
        	String line = data.get(i);
        	SectorInfoVO sectorInfoVO = new SectorInfoVO();
        	
        	Boolean isSectorInfo = false;
        	if(line.indexOf("lotus_miner_sector_qa_power")>-1) {
        		String piece =line.split(" ")[1];
        		if (piece.matches("-?\\d+(\\.\\d+)?([eE][-+]?\\d+)?")) {
        			piece= piece.replace("e+", "E");
        			qualityPower = Double.parseDouble(piece);
        			isSectorInfo = true;
        		}
        	}else if(line.indexOf("lotus_miner_sector_state")>-1) {
        		isSectorInfo = true;
        	}else if(line.indexOf("lotus_miner_deadline_active_partition_sector")>-1) {
        		isSectorInfo = true;
        	}else if(line.indexOf("lotus_miner_sector_event")>-1) {
        			//
        	}else if(line.indexOf("lotus_miner_info_sector_size")>-1) {
        		String piece =line.split("} ")[1];
        		if (piece.matches("-?\\d+(\\.\\d+)?([eE][-+]?\\d+)?")) {
        			piece= piece.replace("e+", "E");
        			sectorSize = Double.parseDouble(piece);
        		}
        	}else if(line.indexOf("lotus_info")>-1) {
        		nodeInfoVO=parseInputStringNodeInfo(line);
        	}else if(line.indexOf("lotus_mpool_local_total")>-1) {
        		String piece =line.split("} ")[1];
        		mpool = Integer.parseInt(piece);
        	}else if(line.indexOf("lotus_wallet_balance")>-1) {
        		lotuswalletVOList.add(parseInputStringLotusWallet(line));
        	}else if(line.indexOf("lotus_wallet_locked_balance")>-1) {
        		String piece =line.split(" ")[1];
        		double filvalue =0;
        		if (piece.matches("-?\\d+(\\.\\d+)?([eE][-+]?\\d+)?")) {
        			piece= piece.replace("e+", "E");
        			filvalue = Double.parseDouble(piece);
        		}
        		if(line.indexOf("locked_type=\"FeeDebt\"")>-1) {
        			feeDebt =filvalue;
            	 	
        		}else if(line.indexOf("locked_type=\"InitialPledge\"")>-1) {
        			initialPledge=filvalue;
            	 
        		}else if(line.indexOf("locked_type=\"LockedFunds\"")>-1) {
        			lockedFunds=filvalue;
            	 	
        		}else if(line.indexOf("locked_type=\"PreCommitDeposits\"")>-1) {
        			preCommiDeposits=filvalue;
        		}
        	}
        	if(isSectorInfo) {
        		sectorInfoVO = parseInputString(line);
        		if(qualityPower>0) {
        			sectorInfoVO.setQualityPower(qualityPower);
        		}
        		SectorInfoVO existingSectorInfoVO = sectorVOHashMap.get(sectorInfoVO.getSector_id());
        		if(!sectorInfoVO.isEmpty()&&existingSectorInfoVO==null) {
        			sectorVOHashMap.put(sectorInfoVO.getSector_id(),sectorInfoVO);		
        		}else if(!sectorInfoVO.isEmpty()) {
        			sectorVOHashMap.put(sectorInfoVO.getSector_id(), existingSectorInfoVO.merge(sectorInfoVO));
        		}
        	}
        }
        double totalQA = 0;
        double superTotal =0;
        double rawByte =0;
        int cc =0;
        int verified =0;
        int nonVerified =0;
        
        
        
        for (Map.Entry<String, SectorInfoVO> entry : sectorVOHashMap.entrySet()) {
            String id = entry.getKey();
            SectorInfoVO sectorInfo = entry.getValue();
            if(sectorInfo.getIs_live()!=null && sectorInfo.getIs_faulty() !=null && sectorInfo.getIs_active() !=null&&sectorInfo.getIs_live().equals("True") &&sectorInfo.getIs_faulty().equals("False")&&sectorInfo.getIs_active().equals("True")) {
            	totalQA +=sectorInfo.getQualityPower();
            	rawByte += sectorSize;	
            	
            	if(sectorInfo.getDeals().equals("0")) {
                	cc++;
                }else if(sectorInfo.getPledged().equals("0")) {
                	nonVerified++;
                }else {
                	verified++;
                }
            }       
            superTotal +=sectorInfo.getQualityPower();
        }
        
        nodeInfoVO.setFeeDebt(feeDebt);
        nodeInfoVO.setInitialPledge(initialPledge);
        nodeInfoVO.setLockedFunds(lockedFunds);
        nodeInfoVO.setPreCommiDeposits(preCommiDeposits);
        nodeInfoVO.setQaPower(convertBytes(totalQA));
        nodeInfoVO.setRawPower(convertBytes(rawByte));
        nodeInfoVO.setCc(cc);
        nodeInfoVO.setVerified(verified);
        nodeInfoVO.setNonVerified(nonVerified);
        nodeInfoVO.setMpool(mpool);
        nodeInfoVO.setLotusWalletVO(lotuswalletVOList);
        
        return nodeInfoVO;
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
            } else if ("is_active".equals(fieldName)) {
                sectorInfo.setIs_active(fieldValue);
            } 
        }
        return sectorInfo;
    }  
    public static NodeInfoVO parseInputStringNodeInfo(String input) {
    	NodeInfoVO nodeInfoVO = new NodeInfoVO();
        
        Pattern pattern = Pattern.compile("(\\w+)=\"(.*?)\"");
        Matcher matcher = pattern.matcher(input);
        
        while (matcher.find()) {
            String fieldName = matcher.group(1);
            String fieldValue = matcher.group(2);
            if ("miner_id".equals(fieldName)) {
            	nodeInfoVO.setMiner_id(fieldValue);
            } else if ("network".equals(fieldName)) {
            	nodeInfoVO.setNetwork(fieldValue);
            } else if ("version".equals(fieldName)) {
            	nodeInfoVO.setVersion(fieldValue);
            }
        }
        return nodeInfoVO;
    }  
    public static LotusWalletVO parseInputStringLotusWallet(String input) {
    	LotusWalletVO lotusWalletVO = new LotusWalletVO();
    	String piece =input.split("} ")[1];
		double fil = Double.parseDouble(piece);
		lotusWalletVO.setFil(fil);
		
        Pattern pattern = Pattern.compile("(\\w+)=\"(.*?)\"");
        Matcher matcher = pattern.matcher(input);
        
        
        while (matcher.find()) {
            String fieldName = matcher.group(1);
            String fieldValue = matcher.group(2);
            if ("miner_id".equals(fieldName)) {
            	lotusWalletVO.setMiner_id(fieldValue);
            } else if ("address".equals(fieldName)) {
            	lotusWalletVO.setAddress(fieldValue);
            } else if ("name".equals(fieldName)) {
            	lotusWalletVO.setName(fieldValue);
            }
        }
        return lotusWalletVO;
    }  
    public String convertBytes(double bytes) {
        if (bytes < 100) {
            return bytes + " B";
        }
        double kilobytes = bytes / 1024.0;
        if (kilobytes < 100) {
            return String.format("%.2f KB", kilobytes);
        }
        double megabytes = kilobytes / 1024.0;
        if (megabytes < 100) {
            return String.format("%.2f MB", megabytes);
        }
        double gigabytes = megabytes / 1024.0;
        if (gigabytes < 100) {
            return String.format("%.2f GB", gigabytes);
        }
        double terabytes = gigabytes / 1024.0;
        if (terabytes < 100) {
            return String.format("%.2f TB", terabytes);
        }
        double petabytes = terabytes / 1024.0;
        return String.format("%.2f PB", petabytes);
    }
    
    
}
