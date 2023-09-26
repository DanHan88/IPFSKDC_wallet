package com.ipfs.kdc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ipfs.kdc.vo.HardWareInfoVO;
import com.ipfs.kdc.vo.LotusWalletVO;
import com.ipfs.kdc.vo.NodeInfoVO;
import com.ipfs.kdc.vo.SectorInfoVO;
@Mapper
public interface NanoDCMapper {

	public void addNewSector(SectorInfoVO sectorInfoVO);
	public void updateNewSector(SectorInfoVO sectorInfoVO);
	public boolean checkSectorExists(SectorInfoVO sectorInfoVO);
	public SectorInfoVO selectSectorById(SectorInfoVO sectorInfoVO);
	public void insertNewNodeInfo(NodeInfoVO nodeInfoVO);
	public void insertNewLotusWalletInfo(LotusWalletVO lotusWalletVO);
	public void insertNewHardWareInfo(HardWareInfoVO hardWareInfoVO);
	public NodeInfoVO selectLatestNodeInfo(NodeInfoVO nodeInfoVO);
	public HardWareInfoVO selectLatestHardWareInfo(HardWareInfoVO nodeInfoVO);
	public List<LotusWalletVO> selectLotusWalletVO(NodeInfoVO nodeInfoVO);
	public boolean checkOverLap(NodeInfoVO nodeInfoVO);
}
