package com.dvn.core.constant;

public enum Version {
	V1_0(1.0,"v1"),
	V1_1(1.1,"v1.1");
	
	private double versionId;
	private String desc;
	
	private Version(double versionId, String desc) {
		this.versionId = versionId;
		this.desc = desc;
	}
	
	public static String getDesc(Version vs) {
		return vs.desc;
	}
	
	public static double getVersionId(Version vs) {
		return vs.versionId;
	}
	
	public Version getVersion(double versionId) throws IllegalArgumentException {
		if(versionId == 1.0) {
			return Version.V1_0;
		}else if(versionId == 1.0) {
			return Version.V1_1;
		}else {
			throw new IllegalArgumentException();
		}
	}
}
