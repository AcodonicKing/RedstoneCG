
package net.acodonic_king.redstonecg.block.floor.parallel.digital;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultParallelDigitalAGate;

public class ParallelNorBlock extends DefaultParallelDigitalAGate {
	public ParallelNorBlock() {
		super();
	}
	@Override
	public boolean redstoneOutputOperation(int LinePower, int BackPower){
		return (LinePower == 0) && (BackPower == 0);
	}
}
