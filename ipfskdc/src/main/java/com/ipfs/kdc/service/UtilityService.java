package com.ipfs.kdc.service;

import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

	
	public double roundDouble (double value) {
		 DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(value));
	}
	public double roundOnce (double value) {
		 DecimalFormat df = new DecimalFormat("#.#");
		return Double.parseDouble(df.format(value));
	}
}
