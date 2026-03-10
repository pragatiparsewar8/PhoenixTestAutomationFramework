package com.api.constants;

public enum Problem {
	SMARTPHONE_IS_RUNNING_SLOW(1),
	POOR_BATTERY_LIFE(2),
	PHONE_OR_APP_CRASHES(3),
	SYNC_ISSUES(4),
	OVERHEARTING(5);
	
	int code;
	private Problem(int code) {
		this.code =code;
	}
	
	public int getCode() {
		return code;
	}
	
}
