
package net.acodonic_king.redstonecg.block.floor.normal.analog;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultAnalogInteractible2Gate;

public class AnalogPassBlock extends DefaultAnalogInteractible2Gate {
	public AnalogPassBlock() {
		super();
	}
	@Override
	public int redstonePowerOperation(int SideAPower, int SideBPower){
		return ((SideAPower * SideBPower) / 15);
	}
}
