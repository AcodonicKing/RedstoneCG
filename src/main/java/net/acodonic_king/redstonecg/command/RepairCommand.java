package net.acodonic_king.redstonecg.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.acodonic_king.redstonecg.block.defaults.FlooringInterface;
import net.acodonic_king.redstonecg.block.defaults.OldInterface;
import net.acodonic_king.redstonecg.init.RedstonecgModVersionRides;
import net.acodonic_king.redstonecg.network.RedstonecgModVariables;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod.EventBusSubscriber
public class RepairCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("redstonecg_repair")
                .requires(cs -> cs.hasPermission(1))
                .then(Commands.literal("canSurviveAir")
                    .then(Commands.argument("enable", BoolArgumentType.bool())
                        .executes(ctx -> {
                            CommandSourceStack source = ctx.getSource();
                            Level level = source.getLevel();
                            boolean enabled = BoolArgumentType.getBool(ctx, "enable");
                            RedstonecgModVariables.MapVariables.get(level).canSurviveAnyCase = enabled;
                            RedstonecgModVariables.MapVariables.get(level).syncData(level);
                            if(enabled)
                                RedstonecgModVersionRides.sendCommandSuccess(source,"RedstoneCG blocks will now stay while being suspended in the air.");
                            else
                                RedstonecgModVersionRides.sendCommandSuccess(source,"RedstoneCG blocks will drop once updated if suspended int the air.");
                            return 1;
                        })
                    )
                )
                .then(Commands.literal("flooring")
                    .then(Commands.argument("position", BlockPosArgument.blockPos())
                        .executes(ctx -> {
                            BlockPos pos = BlockPosArgument.getLoadedBlockPos(ctx, "pos");
                            CommandSourceStack source = ctx.getSource();
                            int r = positional(ctx, RepairCommand::floorAGate);
                            if(r == 0){
                                RedstonecgModVersionRides.sendCommandFailure(source,"Block at " + pos + " cannot be floored.");
                            } else {
                                RedstonecgModVersionRides.sendCommandSuccess(source,"Block at " + pos + " has been floored.");
                            }
                            return r;
                        })
                    )
                    .then(Commands.literal("chunk")
                        .executes(ctx -> {
                            CommandSourceStack source = ctx.getSource();
                            int r = chunkwide(ctx, RepairCommand::floorAGate);
                            if(r == 0){
                                RedstonecgModVersionRides.sendCommandFailure(source,"Blocks at this Chunk cannot be floored.");
                            } else {
                                RedstonecgModVersionRides.sendCommandSuccess(source,"Blocks at this Chunk have been floored.");
                            }
                            return r;
                        })
                    )
                )
                .then(Commands.literal("old")
                    .then(Commands.argument("position", BlockPosArgument.blockPos())
                        .executes(ctx -> {
                            BlockPos pos = BlockPosArgument.getLoadedBlockPos(ctx, "pos");
                            CommandSourceStack source = ctx.getSource();
                            int r = positional(ctx, RepairCommand::oldGate);
                            if(r == 0){
                                RedstonecgModVersionRides.sendCommandFailure(source,"Block at " + pos + " cannot be adjusted to match old version.");
                            } else {
                                RedstonecgModVersionRides.sendCommandSuccess(source,"Block at " + pos + " has been adjusted to match block from the old version.");
                            }
                            return r;
                        })
                    )
                    .then(Commands.literal("chunk")
                        .executes(ctx -> {
                            CommandSourceStack source = ctx.getSource();
                            int r = chunkwide(ctx, RepairCommand::oldGate);
                            if(r == 0){
                                RedstonecgModVersionRides.sendCommandFailure(source,"Blocks at this Chunk cannot be adjusted to match old version.");
                            } else {
                                RedstonecgModVersionRides.sendCommandSuccess(source,"Blocks at this Chunk have been adjusted to match blocks from the old version.");
                            }
                            return r;
                        })
                    )
                )
        );
    }
    private static int positional(CommandContext<CommandSourceStack> ctx, funcCon func) throws CommandSyntaxException {
        BlockPos pos = BlockPosArgument.getLoadedBlockPos(ctx, "pos");
        CommandSourceStack source = ctx.getSource();
        Level level = source.getLevel();
        return func.func(level, pos);
    }
    private static int chunkwide(CommandContext<CommandSourceStack> ctx, funcCon func){
        CommandSourceStack source = ctx.getSource();
        ServerLevel level = source.getLevel();
        Vec3 cpos = source.getPosition();
        ChunkAccess chunk = level.getChunk(new BlockPos(new Vec3i((int) cpos.x, (int) cpos.y, (int) cpos.z)));
        ChunkPos chunkPos = chunk.getPos();
        int minY = chunk.getMinBuildHeight();
        int maxY = minY + chunk.getHeight();
        int r = 0;
        RedstonecgModVariables.MapVariables.get(level).canSurviveAnyCase = true;
        RedstonecgModVariables.MapVariables.get(level).syncData(level);
        for(int y = minY; y < maxY; y++){
            for (int z = 0; z < 16; z++) {
                for (int x = 0; x < 16; x++) {
                    BlockPos pos = chunkPos.getBlockAt(x, y, z);
                    r = Math.max(r, func.func(level, pos));
                }
            }
        }
        return r;
    }
    private static int floorAGate(Level level, BlockPos pos){
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        if(block instanceof FlooringInterface df){
            return df.floorIt(level,pos);
        }
        return 0;
    }
    private static int oldGate(Level level, BlockPos pos){
        if(level.getBlockState(pos).getBlock() instanceof OldInterface oi){
            return oi.oldVersion(level, pos);
        }
        return 0;
    }
    private interface funcCon {
        int func(Level level, BlockPos pos);
    }
}
