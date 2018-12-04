package me.feldmannjr.autoreinicio;

import net.minecraftforge.common.config.Config;

@Config(modid = AutoReinicioMod.MODID)
public class ReinicioConfig {

    @Config.RangeInt(min = 1)
    @Config.Comment("Tempo para reiniciar em minutos")
    public static int tempoReinicio = 4 * 60;
}
