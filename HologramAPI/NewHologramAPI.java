package me.zoonomy.packetstudy.holograms;

import lombok.Getter;
import me.zoonomy.packetstudy.utils.NMSUtils;
import net.minecraft.network.chat.ChatMessage;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntityLiving;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewHologramAPI {

    private Location location;
    private String world;
    private double lineSpacing;

    /**
     *
     * @param location Hologram spawnpoint
     * @param world Not all worlds are called "world"
     * @param lineSpacing Modify how much air you want into your holograms! (How close or far).
     */
    public NewHologramAPI(Location location, String world, double lineSpacing) {
        this.location = location;
        this.world = world;
        this.lineSpacing = lineSpacing;
    }

    /**
     * @param Integer Used to store Entity Armorstand's id (for future reference)
     */
    @Getter private ArrayList<EntityArmorStand> holograms = new ArrayList<>();

    @Getter private ArrayList<String> lines = new ArrayList<>();

    private EntityArmorStand generateHologram() {
        Location spawnLocation = location;
        spawnLocation.add(0, lineSpacing, 0); // Hologram spacing

        EntityArmorStand entityArmorStand =  new EntityArmorStand(NMSUtils.getGlobalWorld(world), spawnLocation.getX(), spawnLocation.getY(), spawnLocation.getZ());
        entityArmorStand.setCustomNameVisible(true);
        entityArmorStand.setCustomName(IChatBaseComponent.a("Loading..."));
        entityArmorStand.setInvisible(true);
        entityArmorStand.setNoGravity(true);

        holograms.add(entityArmorStand);

        return entityArmorStand;
    }

    public void setLine(int line, String content) {
        lines.add(line, content);
    }

    // Used to initial the "setLine" method from above.
    private void initialiseLines() {
        for (int i = 0; i < lines.size(); i++) {
            generateHologram();
            holograms.get(i).setCustomName(new ChatMessage(ChatColor.translateAlternateColorCodes(
                    '&', lines.get(i))));
        }
    }

    public void showHologram(Player player) {
        initialiseLines();
        for (int i = 0; i < lines.size(); i++) {
            NMSUtils.createPacket(player, new PacketPlayOutSpawnEntityLiving(holograms.get(i)));
            NMSUtils.createPacket(player, new PacketPlayOutEntityMetadata(holograms.get(i).getId(), holograms.get(i).getDataWatcher(), true));
        }
    }

    public void addLine(String content, Player player) {
        final EntityArmorStand entityArmorStand = generateHologram();
        holograms.add(entityArmorStand);
        entityArmorStand.setCustomName(new ChatMessage(
                ChatColor.translateAlternateColorCodes('&',
                        content)
        ));

        NMSUtils.createPacket(player, new PacketPlayOutSpawnEntityLiving(entityArmorStand));
        NMSUtils.createPacket(player, new PacketPlayOutEntityMetadata(entityArmorStand.getId(), entityArmorStand.getDataWatcher(), true));
    }

    public void editLine(int i, String newLine, Player player) {
        holograms.get(i).setCustomName(new ChatMessage(ChatColor.translateAlternateColorCodes('&',
                newLine)));
        NMSUtils.createPacket(player, new PacketPlayOutEntityMetadata(holograms.get(i).getId(), holograms.get(i).getDataWatcher(), true));
    }

    public void removeLine(int i, Player player) {
        holograms.get(i).setRemoved(Entity.RemovalReason.a);
        lines.remove(i);
        NMSUtils.createPacket(player, new PacketPlayOutEntityDestroy(holograms.get(i).getId()));
    }

    public void destroyHologram(Player player) {
        for (int i = 0; i < holograms.size(); i++) {
            holograms.get(i).setRemoved(Entity.RemovalReason.a);
            NMSUtils.createPacket(player, new PacketPlayOutEntityDestroy(holograms.get(i).getId()));
        }
    }

}
