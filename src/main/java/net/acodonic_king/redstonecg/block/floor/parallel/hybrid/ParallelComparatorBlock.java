
package net.acodonic_king.redstonecg.block.floor.parallel.hybrid;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultParallelDigitalInteractableABGate;

public class ParallelComparatorBlock extends DefaultParallelDigitalInteractableABGate {
	public ParallelComparatorBlock() {
		super();
	}
	@Override
	public boolean redstoneOutputOperation(int Primary, int Secondary){
		return Primary > Secondary;
	}
}
