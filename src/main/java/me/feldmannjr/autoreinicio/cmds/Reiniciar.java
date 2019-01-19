package me.feldmannjr.autoreinicio.cmds;

import me.feldmannjr.autoreinicio.AutoReinicioMod;
import me.feldmannjr.autoreinicio.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import static me.feldmannjr.autoreinicio.AutoReinicioMod.tempoReiniciar;

public class Reiniciar extends CommandBase {
    @Override
    public String getName() {
        return "reiniciar";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "reiniciar";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();
        if (!world.isRemote) {
            sender.sendMessage(new TextComponentString("Reiniciando..."));
            tempoReiniciar = -1;
            FMLCommonHandler.instance().getMinecraftServerInstance().initiateShutdown();

        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
}
