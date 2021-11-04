package me.zoonomy.packetstudy;

import lombok.Getter;
import me.zoonomy.packetstudy.holograms.NewHologramCMD;
import org.bukkit.plugin.java.JavaPlugin;

public final class PacketStudy extends JavaPlugin {

    @Getter private static PacketStudy INSTANCE;

    @Override
    public void onEnable() {
        super.onEnable();

        INSTANCE = this;
        
        this.getCommand("hologram").setExecutor(new NewHologramCMD());


    }

    @Override
    public void onDisable() {
        super.onDisable();

    }
}
