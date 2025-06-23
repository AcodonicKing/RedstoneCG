
package net.acodonic_king.redstonecg.block.floor.parallel.analog;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultParallelAnalogAGate;

public class ParallelAnalogGainBlock extends DefaultParallelAnalogAGate {
	public ParallelAnalogGainBlock() {
		super();
	}
	@Override
	public int redstonePowerOperation(int LinePower, int BackPower){
		return Math.min(LinePower * BackPower, 15);
	}
}
