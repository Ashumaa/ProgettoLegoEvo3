import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Comandi {
	
	RegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
	RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
	
	private int maxVel = 900;
	private int baseVel = 400;
	private int vel;
	private int i = 0;
	private int midVel;
	private boolean axl = false;

	public void vaiAvanti() 
	{
		if(!axl)
			setVel();
	motorC.forward();
	motorB.forward();
	
	if (getMidVel() < maxVel) {
	i = i+1;
	aumVel(i);
	}
	vel = motorC.getSpeed();
	
	}
	
	public void stop()
	{
	motorC.stop(true);
	motorB.stop(true);
	vel = 0;
	
	}
	
	public void vaiIndietro() 
	{
	setVel();	
	motorC.backward();
	motorB.backward();
	if (getMidVel() < maxVel) {
	i = i+1;
	aumVel(i);
	}
	vel = motorC.getSpeed();
	}
	
	public void aumVel(int aum)
	{
	motorC.setSpeed(motorC.getSpeed()+aum);
	motorB.setSpeed(motorB.getSpeed()+aum);
	vel = getMidVel();
	}
	
	public void impVel(int vel)
	{
	motorC.setSpeed(vel);
	motorB.setSpeed(vel);
	}
	
	public void curvaSx() 
	{
	//motorB.setSpeed(motorB.getSpeed()/2);
	//motorB.forward();
	//motorC.forward();
	motorB.rotate(10);
	}
	
	public void curvaDx() {
	
	
	//motorC.setSpeed(motorC.getSpeed()/2);
	//motorB.forward();
	//motorC.forward();
	motorC.rotate(10);
	}
	
	public void reset() {
	
	motorC.setSpeed(baseVel);
	motorB.setSpeed(vel);
	i = 0;
	}
	
	public void setVel() {
	
	motorC.setSpeed(baseVel);
	motorB.setSpeed(baseVel);
	}
	
	public void rallenta() {
	
	//if (motorC.getSpeed() > 0 && motorB.getSpeed() > 0) {
	
	motorC.setSpeed(motorC.getSpeed() - 50);
	motorB.setSpeed(motorB.getSpeed() - 50);
	//}
	
	//vel = getMidVel();
	}
	
	public void shutDown() {
	
	motorC.stop(true);
	motorB.stop(true);
	
	motorC.close();
	motorB.close();
	}
	
	public void setMaxVel()
	{
		motorC.setSpeed(maxVel);
		motorB.setSpeed(maxVel);
		if(axl)
			axl = false;
		else
			axl=true;
		
	}
	
	public int getMidVel() {
	
	midVel = (motorC.getSpeed() + motorB.getSpeed())/2;
	
	return midVel;
	}
	
}