package me.feldmannjr.autoreinicio.cmds;

import me.feldmannjr.autoreinicio.AutoReinicioMod;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class Adiar extends CommandBase {
    @Override
    public String getName() {
        return "adiar";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "adiar [minutos]";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();
        if (!world.isRemote) {
            int minutos = 60;
            if (args.length == 1) {
                try {
                    minutos = Integer.valueOf(args[0]);
                } catch (NumberFormatException ex) {
                    throw new WrongUsageException(getUsage(sender), new Object[0]);
                }
            }
            AutoReinicioMod.tempoReiniciar += 20L * 60L * minutos;
            TextComponentString msg = new TextComponentString("Reinicio adiado em " + minutos + " minutos!");
            msg.getStyle().setColor(TextFormatting.AQUA);
            sender.sendMessage(msg);


        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}
