
package net.acodonic_king.redstonecg.block.floor.normal.analog;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultAnalogInteractible2Gate;

public class AnalogDifferentialBlock extends DefaultAnalogInteractible2Gate {
	public AnalogDifferentialBlock() {
		super();
	}
	@Override
	public int redstonePowerOperation(int SideAPower, int SideBPower){
		return Math.abs(SideAPower - SideBPower);
	}
}
