/*
 * see license.txt 
 */
package seventh.ai.basic.actions;

import seventh.ai.basic.Brain;
import seventh.ai.basic.Locomotion;
import seventh.ai.basic.Zone;
import seventh.math.Vector2f;
import seventh.shared.TimeStep;

/**
 * @author Tony
 *
 */
public class DefendZoneAction extends AdapterAction {
	
	private Zone zone;
	
	private final long timeToDefend;
	private long timeSpentDefending;
	
	/**
	 */
	public DefendZoneAction() {
		this(null);
	}
	
	/**
	 * 
	 */
	public DefendZoneAction(Zone zone) {
		this.zone = zone;
		
		timeToDefend = 5_000; /* time spent before we ask what to do next */
		timeSpentDefending = 0;
	}
	
	/* (non-Javadoc)
	 * @see seventh.ai.basic.actions.AdapterAction#start(seventh.ai.basic.Brain)
	 */
	@Override
	public void start(Brain brain) {
		if(zone==null) {
			zone = brain.getWorld().getZone(brain.getEntityOwner().getCenterPos());
		}		
	}
	
	/* (non-Javadoc)
	 * @see seventh.ai.basic.actions.AdapterAction#resume(seventh.ai.basic.Brain)
	 */
	@Override
	public void resume(Brain brain) {
		Vector2f pos = brain.getWorld().getRandomSpot(brain.getEntityOwner(), zone.getBounds());
		
		Locomotion motion = brain.getMotion();
		motion.moveTo(pos);
	}
	
	/* (non-Javadoc)
	 * @see seventh.ai.basic.actions.AdapterAction#update(seventh.ai.basic.Brain, seventh.shared.TimeStep)
	 */
	@Override
	public void update(Brain brain, TimeStep timeStep) {
		Locomotion motion = brain.getMotion();
		if(!motion.isMoving()) {
			Vector2f pos = brain.getWorld().getRandomSpot(brain.getEntityOwner(), zone.getBounds());
			motion.moveTo(pos);
		}
		
		timeSpentDefending += timeStep.getDeltaTime();
	}
	
	/* (non-Javadoc)
	 * @see seventh.ai.basic.actions.AdapterAction#isFinished()
	 */
	@Override
	public boolean isFinished(Brain brain) {
		return timeSpentDefending >= timeToDefend;
		
	}

}
