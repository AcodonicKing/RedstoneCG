
package net.acodonic_king.redstonecg.block.floor.parallel.analog;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultParallelAnalogInteractableABGate;

public class ParallelAnalogSubtractorBlock extends DefaultParallelAnalogInteractableABGate {
	public ParallelAnalogSubtractorBlock() {
		super();
	}
	@Override
	public int redstonePowerOperation(int PrimaryPower, int SecondaryPower){
		return Math.max(PrimaryPower - SecondaryPower, 0);
	}
}
