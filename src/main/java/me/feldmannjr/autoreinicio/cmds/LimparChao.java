package me.feldmannjr.autoreinicio.cmds;

import me.feldmannjr.autoreinicio.AutoReinicioMod;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class LimparChao extends CommandBase {
    @Override
    public String getName() {
        return "limparchao";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "limparchao";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        AutoReinicioMod.clearGroundItems();
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
}
