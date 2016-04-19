package gamedev.zeldaclone.utils;



public enum Direction {
	//finals
	NORTH(0,-1),
	EAST(1,0),
	SOUTH(0,1),
	WEST(-1,0),
	IDLE(0,0);
	//fields for finals
	private int directionX , directionY;
	
	Direction(int directionX , int directionY){
		this.directionX = directionX;
		this.directionY = directionY;
	}
	//methods
	public int dX(){ return directionX;}
	public int dY(){ return directionY;}
	

}
