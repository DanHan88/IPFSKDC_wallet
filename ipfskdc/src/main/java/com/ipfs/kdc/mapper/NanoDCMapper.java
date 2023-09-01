package com.ipfs.kdc.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.ipfs.kdc.vo.SectorInfoVO;
@Mapper
public interface NanoDCMapper {

	public void addNewSector(SectorInfoVO sectorInfoVO);
	public void updateNewSector(SectorInfoVO sectorInfoVO);
	public boolean checkSectorExists(SectorInfoVO sectorInfoVO);
	public SectorInfoVO selectSectorById(SectorInfoVO sectorInfoVO);
	
	
}
