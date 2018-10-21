package huang.bling.smartbus;

public  class CoordinateConvert {
	
	private float xOrigin = 100;
	private float yOrigin = 100;
	private float xstandard = 100;
	private float ystandard = 100;
	
	public CoordinateConvert(float xO, float yO) {
		this.xOrigin = xO;
		this.yOrigin = yO;
	}
	



	public float getOrigin() {
		return xOrigin;
	}

	public void setOrigin(int origin) {
		this.xOrigin = origin;
	}
	
	public float[] getStandardCoordinate(float x, float y) {
		float[] temp = {x/xOrigin*xstandard,y/yOrigin*ystandard};
		return temp;
	}
	
	public float[] getOriginCoordinate(float x, float y) {
		float[] temp = {x/xstandard*xOrigin, y/ystandard*yOrigin};
		return temp;
	}

	public float getyOrigin() {
		return yOrigin;
	}

	public void setyOrigin(float yOrigin) {
		this.yOrigin = yOrigin;
	}


	public float getXstandard() {
		return xstandard;
	}


	public void setXstandard(float xstandard) {
		this.xstandard = xstandard;
	}




	public float getYstandard() {
		return ystandard;
	}




	public void setYstandard(float ystandard) {
		this.ystandard = ystandard;
	}

}
