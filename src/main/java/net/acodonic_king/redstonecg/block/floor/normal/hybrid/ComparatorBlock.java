
package net.acodonic_king.redstonecg.block.floor.normal.hybrid;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultDigitalInteractable2ABGate;

public class ComparatorBlock extends DefaultDigitalInteractable2ABGate {
	public ComparatorBlock() {
		super();
	}
	@Override
	public boolean redstoneOutputOperation(int SideAPower, int SideBPower){
		return SideAPower > SideBPower;
	}
}
