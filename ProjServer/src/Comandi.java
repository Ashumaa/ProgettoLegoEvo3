import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
public class Comandi {
	
	RegulatedMotor EngineC = new EV3LargeRegulatedMotor(MotorPort.C);
	RegulatedMotor EngineB = new EV3LargeRegulatedMotor(MotorPort.B);
	
	private int maxSpeed = 900;
	private int baseSpeed = 400;
	private int Speed;
	private int i = 0;
	private int midSpeed;
	private boolean axl = false;
    private int tempC;
    private int tempB;

	public void goForward() 
	{
		if(EngineC.getSpeed()>EngineB.getSpeed())
		{
			EngineB.setSpeed(EngineC.getSpeed());
		}
		else
			if(EngineC.getSpeed()<EngineB.getSpeed())
			{
				EngineC.setSpeed(EngineB.getSpeed());
			}
			else
				
		if(!axl) {
			EngineC.setSpeed(maxSpeed);
			EngineB.setSpeed(maxSpeed);
		}
		EngineC.forward();
	EngineB.forward();
	
	if (getMidSpeed() < maxSpeed) {
	i = i+1;
	IncreaseSpeed(i);
	}
	Speed = EngineC.getSpeed();
	
	}
	
	public void stop()
	{
	EngineC.stop(true);
	EngineB.stop(true);
	Speed = 0;
	
	}
	
	public void goBackward() 
	{
	setSpeed();	
	EngineC.backward();
	EngineB.backward();
	if (getMidSpeed() < maxSpeed) {
	i = i+1;
	IncreaseSpeed(i);
	}
	Speed = EngineC.getSpeed();
	}
	
	public void IncreaseSpeed(int aum)
	{
	EngineC.setSpeed(EngineC.getSpeed()+aum);
	EngineB.setSpeed(EngineB.getSpeed()+aum);
	Speed = getMidSpeed();
	}
	
	public void changeSpeed(int Speed)
	{
	EngineC.setSpeed(Speed);
	EngineB.setSpeed(Speed);
	}
	
	public void rotateLeft() 
	{
	//EngineB.setSpeed(EngineB.getSpeed()/2);
	//EngineB.forward();
	//EngineC.forward();
	EngineC.setSpeed(900);
	EngineB.setSpeed(450);
	
	/*tempC= EngineC.getSpeed();
    tempB= EngineB.getSpeed();
    if(EngineB.getSpeed() == maxSpeed && EngineC.getSpeed()==maxSpeed)
    {
        EngineB.setSpeed(EngineC.getSpeed()/2);
        EngineC.setSpeed(EngineC.getSpeed()/2);
    }
    EngineB.rotate(1);
    EngineB.setSpeed(tempB);
    EngineC.setSpeed(tempC);
	*/
	}
	
	public void rotateRight() {
	
		EngineB.setSpeed(900);
		EngineC.setSpeed(450);
	//EngineC.setSpeed(EngineC.getSpeed()/2);
	//EngineB.forward();
	//EngineC.forward();
	
		/*
    tempC= EngineC.getSpeed();
    tempB= EngineB.getSpeed();
    if(EngineB.getSpeed() == maxSpeed && EngineC.getSpeed()==maxSpeed)
    {
        EngineB.setSpeed(EngineC.getSpeed()/2);
        EngineC.setSpeed(EngineC.getSpeed()/2);
    }
	EngineC.rotate(1);
    EngineB.setSpeed(tempB);
    EngineC.setSpeed(tempC);
	*/
	}
	
	public void reset() {
	
	EngineC.setSpeed(baseSpeed);
	EngineB.setSpeed(Speed);
	i = 0;
	}
	
	public void setSpeed() {
	
	EngineC.setSpeed(baseSpeed);
	EngineB.setSpeed(baseSpeed);
	}
	
	public void slow() {
	
	//if (EngineC.getSpeed() > 0 && EngineB.getSpeed() > 0) {
	
	EngineC.setSpeed(EngineC.getSpeed() - 50);
	EngineB.setSpeed(EngineB.getSpeed() - 50);
	//}
	
	//Speed = getMidSpeed();
	}
	
	public void shutDown() {
	
	EngineC.stop(true);
	EngineB.stop(true);
	
	EngineC.close();
	EngineB.close();
	}
	
	public void setMaxSpeed()
	{
		EngineC.setSpeed(maxSpeed);
		EngineB.setSpeed(maxSpeed);
		if(axl)
			axl = false;
		else
			axl=true;
		
	}
	
	public int getMidSpeed() {
	
	midSpeed = (EngineC.getSpeed() + EngineB.getSpeed())/2;
	
	return midSpeed;
	}
	
}
