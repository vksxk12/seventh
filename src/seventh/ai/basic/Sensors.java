/*
 * see license.txt 
 */
package seventh.ai.basic;

import seventh.shared.TimeStep;

/**
 * @author Tony
 *
 */
public class Sensors {

	private SightSensor sightSensor;
	private SoundSensor soundSensor;
	private FeelSensor feelSensor;
	
	public Sensors(Brain brain) {
		this.sightSensor = new SightSensor(brain, 200, 400);
		this.soundSensor = new SoundSensor(brain, 900, 900);
		this.feelSensor = new FeelSensor(brain);
	}
	
	public void reset(Brain brain) {
		this.sightSensor.reset(brain);
		this.soundSensor.reset(brain);
		this.feelSensor.reset(brain);
	}
	
	/**
	 * @return the feelSensor
	 */
	public FeelSensor getFeelSensor() {
		return feelSensor;
	}
	
	/**
	 * @return the sightSensor
	 */
	public SightSensor getSightSensor() {
		return sightSensor;
	}
	
	/**
	 * @return the soundSensor
	 */
	public SoundSensor getSoundSensor() {
		return soundSensor;
	}
	
	/**
	 * Poll each sensor
	 * @param timeStep
	 */
	public void update(TimeStep timeStep) {
		this.sightSensor.update(timeStep);
		this.soundSensor.update(timeStep);
		this.feelSensor.update(timeStep);
		
		this.sightSensor.see();
		this.soundSensor.listen();
		this.feelSensor.feel();
	}
}
