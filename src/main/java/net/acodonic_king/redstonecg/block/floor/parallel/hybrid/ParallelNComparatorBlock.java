
package net.acodonic_king.redstonecg.block.floor.parallel.hybrid;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultParallelDigitalInteractableABGate;

public class ParallelNComparatorBlock extends DefaultParallelDigitalInteractableABGate {
	public ParallelNComparatorBlock() {
		super();
	}
	@Override
	public boolean redstoneOutputOperation(int Primary, int Secondary){
		return Primary <= Secondary;
	}
}
