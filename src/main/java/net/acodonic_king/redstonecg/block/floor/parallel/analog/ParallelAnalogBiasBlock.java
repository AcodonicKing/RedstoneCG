
package net.acodonic_king.redstonecg.block.floor.parallel.analog;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultParallelAnalogAGate;

public class ParallelAnalogBiasBlock extends DefaultParallelAnalogAGate {
	public ParallelAnalogBiasBlock() {
		super();
	}
	@Override
	public int redstonePowerOperation(int LinePower, int BackPower){
		return Math.min(LinePower + BackPower, 15);
	}
}
