
package net.acodonic_king.redstonecg.block.floor.normal.analog;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultAnalogInteractible2ABGate;

public class AnalogSubtractorBlock extends DefaultAnalogInteractible2ABGate {
	public AnalogSubtractorBlock() {
		super();
	}
	@Override
	public int redstonePowerOperation(int SideAPower, int SideBPower){
		return Math.max(SideAPower - SideBPower, 0);
	}
}
