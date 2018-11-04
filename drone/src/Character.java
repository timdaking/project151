
public class Character {
	double x;
	double y;
	double characterMaxSpeed;
        private double characterSpeed;
        private final double acceleration;
        private double deltaA;
        private static final double GRAVITY = 0.00025;
	
	public Character(double x,double y, double characterMaxSpeed, double acceleration) {
		this.x = x;
		this.y = y;
		this.characterMaxSpeed = characterMaxSpeed;
                this.acceleration = acceleration;
                deltaA = acceleration;
                characterSpeed = 0;
	}
        
        void setX(int x){
            this.x = x;
        }
        
        void setY(int y){
            this.y = y;
        }
        
        double getY(){
            return y;
        }
        
        double getX(){
            return x;
        }
        
        void onKeyAction(){
            deltaA = acceleration;
        }
                
        void offKeyAction(){
            deltaA = 0;
        }
        
        double getSpeed(){
            return characterSpeed;
        }
        
        double accelerate(){
            if (characterSpeed < characterMaxSpeed){
                characterSpeed += deltaA;
            }
            return characterSpeed;
        }
        
        double decelerate(){
            if (characterSpeed > -characterMaxSpeed){
                characterSpeed -= deltaA;
            }
            return characterSpeed;
        }
        
        double decelerateGravity(){
            characterSpeed -= GRAVITY;
            return characterSpeed;
        }
        
        double getCharacterSpeed(){
            return characterSpeed;
        }
}
