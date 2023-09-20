package com.ipfs.kdc.service;

import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

	
	public double roundDouble (double value) {
		return Math.round(value * 100.0) / 100.0;
	}
	public double roundOnce (double value) {
		return Math.round(value * 10.0) / 10.0;
	}
}
