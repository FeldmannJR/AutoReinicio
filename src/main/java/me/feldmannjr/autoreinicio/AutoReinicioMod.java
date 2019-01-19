package me.feldmannjr.autoreinicio;

import me.feldmannjr.autoreinicio.cmds.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

import static me.feldmannjr.autoreinicio.AutoReinicioMod.MODID;
import static me.feldmannjr.autoreinicio.AutoReinicioMod.NAME;
import static me.feldmannjr.autoreinicio.AutoReinicioMod.VERSION;

@Mod(modid = MODID, name = NAME, version = VERSION, acceptableRemoteVersions = "*")
public class AutoReinicioMod {

    public static final String MODID = "autoreinicio";
    public static final String NAME = "Auto Reinicio";
    public static final String VERSION = "0.3";


    private static List<Long> alertar = Arrays.asList(
            20L,
            20 * 2L,
            20 * 3L,
            20 * 5L,
            20 * 10L,
            20 * 30L,
            20 * 60L,
            20 * 60 * 2L,
            20 * 60 * 5L,
            20 * 60 * 10L,
            20 * 60 * 30L);

    public static long tempoReiniciar = -1;


    private static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        setTempoReiniciar(ReinicioConfig.tempoReinicio);
        // some example code
        logger.info("Auto reinicio habilitado! Servidor irá reiniciar em 4h!");
        MinecraftForge.EVENT_BUS.register(this);
    }


    @Mod.EventHandler
    public void server(FMLServerStartingEvent ev) {
        ev.registerServerCommand(new Adiar());
        ev.registerServerCommand(new Reinicio());
        ev.registerServerCommand(new Reiniciar());
        ev.registerServerCommand(new LimparChao());
        ev.registerServerCommand(new SetReinicio());

    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent ev) {
        if (ev.phase == TickEvent.Phase.START) {
            if (tempoReiniciar == -1) return;
            if (tempoReiniciar == 0) {
                tempoReiniciar = -1;
                FMLCommonHandler.instance().getMinecraftServerInstance().initiateShutdown();
                return;
            }
            if (tempoReiniciar == 20 * 10) {
                clearGroundItems();
            }
            if (alertar.contains(tempoReiniciar)) {
                TextComponentString msg = new TextComponentString("O servidor irá reiniciar em ");
                msg.getStyle().setColor(TextFormatting.DARK_RED);
                TextComponentString minutos = new TextComponentString(Utils.millisToString(tempoReiniciar * 50L));
                minutos.getStyle().setColor(TextFormatting.RED);
                msg.appendSibling(minutos);
                TextComponentString resto = new TextComponentString(" !");
                resto.getStyle().setColor(TextFormatting.DARK_RED);
                msg.appendSibling(resto);
                PlayerList players = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();
                players.sendMessage(msg);
            }
            tempoReiniciar--;
        }
    }


    public static void clearGroundItems() {
        WorldServer[] worlds = FMLCommonHandler.instance().getMinecraftServerInstance().worlds;
        for (WorldServer world : worlds) {
            for (Entity entity : world.loadedEntityList) {
                if (entity instanceof EntityItem) {
                    world.removeEntity(entity);
                }
            }
        }
        TextComponentString msg = new TextComponentString("Limpando itens do chão!!");
        msg.getStyle().setColor(TextFormatting.LIGHT_PURPLE);
        FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendMessage(msg);

    }

    public static void setTempoReiniciar(int minutos) {
        AutoReinicioMod.tempoReiniciar = minutos * 20L * 60L;
    }
}