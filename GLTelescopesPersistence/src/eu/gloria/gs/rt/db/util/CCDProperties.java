package eu.gloria.gs.rt.db.util;

public class CCDProperties {
	
	int ccdid;
	Double pixelSize;
	Double focalLength;
	int binning;
	
	
	public int getCcdid() {
		return ccdid;
	}
	public void setCcdid(int ccdid) {
		this.ccdid = ccdid;
	}
	public Double getPixelSize() {
		return pixelSize;
	}
	public void setPixelSize(Double pixelSize) {
		this.pixelSize = pixelSize;
	}
	public Double getFocalLength() {
		return focalLength;
	}
	public void setFocalLength(Double focalLength) {
		this.focalLength = focalLength;
	}
	public int getBinning() {
		return binning;
	}
	public void setBinning(int binning) {
		this.binning = binning;
	}
	
	

}
