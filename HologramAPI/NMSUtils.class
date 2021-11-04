package me.zoonomy.packetstudy.utils;

import lombok.Getter;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSUtils {

    @Getter private static final NMSUtils instance = new NMSUtils();
    private NMSUtils() {}

    public static PlayerConnection getConnection(Player player) {
        return ((CraftPlayer) player).getHandle().b;
    }

    public static WorldServer getPlayerWorld(Player player) {
        return ((CraftWorld) player.getWorld()).getHandle();
    }

    public static WorldServer getGlobalWorld(String world) {
        return ((CraftWorld)Bukkit.getWorld(world)).getHandle();
    }

    public static void createPacket(Player player, Packet packet) {
        ((CraftPlayer) player).getHandle().b.sendPacket(packet);
    }

    public static MinecraftServer getServer() {
        return ((CraftServer) Bukkit.getServer()).getServer();
    }

}
