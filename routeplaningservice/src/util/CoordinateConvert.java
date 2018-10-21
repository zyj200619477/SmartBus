package util;

public  class CoordinateConvert {
	
	private double xOrigin = 100;
	private double yOrigin = 100;
	private double xstandard = 100;
	private double ystandard = 100;
	
	public CoordinateConvert(double xO, double yO) {
		this.xOrigin = xO;
		this.yOrigin = yO;
	}
	



	public double getOrigin() {
		return xOrigin;
	}

	public void setOrigin(int origin) {
		this.xOrigin = origin;
	}
	
	public double[] getStandardCoordinate(double x, double y) {
		double[] temp = {x/xOrigin*xstandard,y/yOrigin*ystandard};
		return temp;
	}
	
	public double[] getOriginCoordinate(double x, double y) {
		double[] temp = {x/xstandard*xOrigin, y/ystandard*yOrigin};
		return temp;
	}

	public double getyOrigin() {
		return yOrigin;
	}

	public void setyOrigin(double yOrigin) {
		this.yOrigin = yOrigin;
	}


	public double getXstandard() {
		return xstandard;
	}


	public void setXstandard(double xstandard) {
		this.xstandard = xstandard;
	}




	public double getYstandard() {
		return ystandard;
	}




	public void setYstandard(double ystandard) {
		this.ystandard = ystandard;
	}

}
