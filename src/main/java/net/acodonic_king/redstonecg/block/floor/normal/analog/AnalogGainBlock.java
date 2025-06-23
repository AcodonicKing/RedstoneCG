
package net.acodonic_king.redstonecg.block.floor.normal.analog;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultAnalogInteractible2Gate;

public class AnalogGainBlock extends DefaultAnalogInteractible2Gate {
	public AnalogGainBlock() {
		super();
	}
	@Override
	public int redstonePowerOperation(int SideAPower, int SideBPower){
		return Math.min(15, SideAPower * SideBPower);
	}
}
