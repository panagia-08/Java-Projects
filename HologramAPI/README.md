## Introduction
Quick Hologram utility class made for Minecraft Java 1.17.1 
This is just a demo code, you might need to modify some changes in your classes. GOOD LUCK!

## IMPORTANT
I am currently using LOMBOK for getters and setters, if you are too bored to install it, just manually create getters and setters.

##USAGE
```java

// loc = the hologram spawn location
// "world" = The world you want to spawn the hologram in
// 0.60 = Hologram spacing. Yes you can modify the hologram spacing.


NewHologramAPI api = new NewHologramAPI(loc, "world", 0.60); // Call the HologramAPI class

api.setLine(0, "Test line"); // Set lines
api.setLine(1, "&dColored &bline&7!"); // Set lines

api.showHologram(target); // This shows the hologram ONLY to one target, if you want everybody to see it use forEach or for loop.

api.removeLine(0, player); // This removes a specific line from a hologram FOR ONLY one player.

api.addLine("&c&lWOOPSIE! &rExtra line -> " + getCount(), player); // Adds new lines to an already existing hologram.

api.destroyHologram(player); // Destroys hologram packets for one player

```
