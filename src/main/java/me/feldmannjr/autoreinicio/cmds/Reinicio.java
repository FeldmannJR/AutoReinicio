package me.feldmannjr.autoreinicio.cmds;

import me.feldmannjr.autoreinicio.AutoReinicioMod;
import me.feldmannjr.autoreinicio.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import static me.feldmannjr.autoreinicio.AutoReinicioMod.tempoReiniciar;

public class Reinicio extends CommandBase {
    @Override
    public String getName() {
        return "reinicio";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "reiniciar";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();
        if (!world.isRemote) {
            TextComponentString msg = new TextComponentString("Servidor irá reiniciar em " + Utils.millisToString(tempoReiniciar * 50L) + " !");
            msg.getStyle().setColor(TextFormatting.RED);
            sender.sendMessage(msg);

        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}
