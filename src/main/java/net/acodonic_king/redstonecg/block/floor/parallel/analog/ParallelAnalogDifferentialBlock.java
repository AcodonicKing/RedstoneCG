
package net.acodonic_king.redstonecg.block.floor.parallel.analog;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultParallelAnalogAGate;

public class ParallelAnalogDifferentialBlock extends DefaultParallelAnalogAGate {
	public ParallelAnalogDifferentialBlock() {
		super();
	}
	@Override
	public int redstonePowerOperation(int LinePower, int BackPower){
		return Math.abs(LinePower - BackPower);
	}
}
