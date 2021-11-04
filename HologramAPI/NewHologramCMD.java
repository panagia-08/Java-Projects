package me.zoonomy.packetstudy.holograms;

import lombok.Getter;
import lombok.Setter;
import me.zoonomy.packetstudy.PacketStudy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class NewHologramCMD implements CommandExecutor {

    @Getter @Setter private double count;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        setCount(15);

        player.sendMessage("[" + NewHologramCMD.class.getSimpleName() + "] " + "Executed.");

        Location loc = new Location(Bukkit.getWorld("world"), 0.5, 102, 0.5);

        NewHologramAPI api = new NewHologramAPI(loc, "world", 0.60);

        new BukkitRunnable() {

            @Override
            public void run() {

                if (getCount() == 14) {
                    api.setLine(0, "Test line for " + player.getDisplayName());
                    api.setLine(1, "&dColored &bline&7!");
                }

                if (getCount() == 13) {
                    player.sendMessage("Showing holograms...");
                    api.showHologram(player);
                }

                if (getCount() == 11) {
                    player.sendMessage("Removed line -> " + api.getLines().get(0));
                    api.removeLine(0, player);
                }

                if (getCount() >= 1 && getCount() <= 9) {
                    api.addLine("&c&lWOOPSIE! &rExtra line -> " + getCount(), player);
                }

                if (getCount() == 1) {
                    player.sendMessage(ChatColor.GRAY + "Destroying all hologram for player " + player.getDisplayName() + "...");
                }

                if (getCount() <= 0) {
                    player.sendMessage("All holograms have been destroyed!");
                    api.destroyHologram(player);
                    cancel();
                }

                count--;
            }
        }.runTaskTimer(PacketStudy.getINSTANCE(), 0, 5);

        return true;
    }
}
