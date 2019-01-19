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

public class SetReinicio extends CommandBase {
    @Override
    public String getName() {
        return "setreinicio";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return getName() + " <minutos>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();
        if (!world.isRemote) {

            if (args.length != 1) {
                throw new WrongUsageException(getUsage(sender), new Object[0]);
            }
            int minutos;
            try {
                minutos = Integer.valueOf(args[0]);
            } catch (NumberFormatException ex) {
                throw new WrongUsageException(getUsage(sender), new Object[0]);
            }
            if (minutos <= 0) {
                throw new WrongUsageException(getUsage(sender), new Object[0]);
            }
            AutoReinicioMod.tempoReiniciar = 20L * 60L * minutos;
            TextComponentString msg = new TextComponentString("Reinicio setado para acontecer em " + minutos + " minutos!");
            msg.getStyle().setColor(TextFormatting.AQUA);
            sender.sendMessage(msg);
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
}
