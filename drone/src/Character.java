
public class Character {
	int x;
	int y;
	int speed;
	
	public Character(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
        
        void setX(int x){
            this.x = x;
        }
        
        void setY(int y){
            this.y = y;
        }
        
        int getY(){
            return y;
        }
        
        int getX(){
            return x;
        }
        
        int getSpeed(){
            return speed;
        }
}
