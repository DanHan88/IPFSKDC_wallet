package com.ipfs.kdc.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ipfs.kdc.mapper.NanoDCMapper;
import com.ipfs.kdc.vo.LotusWalletVO;
import com.ipfs.kdc.vo.NodeInfoVO;

@Component
public class NanoDCBatch {
	@Autowired
	NanoDCMapper nanoDCMapper;
	@Autowired
    private NanoDCService nanoDCService;
	
	@Value("${batch.app.enabled}")
    private String batchEnabled;
	
	@Scheduled(cron = "0 */10 * * * ?") // Runs every 10 minutes
    public void runBatchJob() throws IOException {
		if(batchEnabled.equals("false")) {
			System.out.println("스케줄러 가 꺼져있네요!(로컬이면 정상)");
			return;
		}
		Date info_date = new Date();    
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(info_date);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        info_date = calendar.getTime();
		//nanoDCService.scheduledUpdateNodeInfo("http://58.121.116.101:9101/metrics");
        String miner_id = "f01695888";
        nanoDCService.scheduledUpdateNodeInfo("http://121.178.82.230:9101/metrics",info_date);
		nanoDCService.scheduledUpdateHardWareInfo("http://121.178.82.230:9100/metrics",info_date,miner_id);
		System.out.println("스케줄러 성공! 로컬에서 뜨면 안되요. 스케줄러 로컬테스트 환경에서는 꺼주세요");
    }
}




