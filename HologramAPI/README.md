## Introduction
Quick Hologram utility class made for Minecraft Java 1.17.1 

## IMPORTANT
I am currently using LOMBOK for getters and setters, if you are too bored to install it just manually create getters and setters.

##USAGE
```java

NewHologramAPI api = new NewHologramAPI(loc, "world", 0.60);

api.setLine(0, "Test line");
api.setLine(1, "&dColored &bline&7!");

api.showHologram(player);

api.removeLine(0, player);

api.addLine("&c&lWOOPSIE! &rExtra line -> " + getCount(), player);

api.destroyHologram(player);

```
