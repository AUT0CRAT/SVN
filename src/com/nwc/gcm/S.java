package com.nwc.gcm;

public class S
{
	public static final String API_KEY = "AIzaSyALkgzzF60unO4Hw5mbX3raW-lNKQ1L-hE";

	public static class Parameter
	{
		public static final String PARAMETER_REG_ID = "regId";
		public static final String MESSAGE_DATA_KEY = "data";
		public static final String MESSAGE_VEHICLE_KEY = "key";
	}

	public static final class Status{
		public static final int FAIL = 0;
		public static final int SUCCESS = 1;
		public static final int EXISTS = 2; 
	}
}
