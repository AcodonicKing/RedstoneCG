
package net.acodonic_king.redstonecg.block.floor.parallel.analog;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultParallelAnalogAGate;

public class ParallelAnalogMaxBlock extends DefaultParallelAnalogAGate {
	public ParallelAnalogMaxBlock() {
		super();
	}
	@Override
	public int redstonePowerOperation(int LinePower, int BackPower){
		return Math.max(LinePower, BackPower);
	}
}
