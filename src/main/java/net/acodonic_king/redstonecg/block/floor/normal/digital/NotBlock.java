
package net.acodonic_king.redstonecg.block.floor.normal.digital;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultDigitalInteractable1Gate;

public class NotBlock extends DefaultDigitalInteractable1Gate {
	public NotBlock() {
		super();
	}
	@Override
	public boolean redstoneOutputOperation(int SidePower){
		return SidePower == 0;
	}
}
