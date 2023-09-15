package com.ipfs.kdc.service;

import java.text.DecimalFormat;

public class UtilityService {

	
	public double roundDouble (double value) {
		 DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(value));
	}
}
