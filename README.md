# Minecraft 2D

A Java project I had written in my final year of secondary school. Code might be (probably is) messy...haven't looked at
this code for a while. Here are some features that I had worked on.

## Random map generation

Each time a world is loaded, the map is generated from scratch, having hills, ores that depend on the level and depth of
the terrain (diamonds spawn at the bottom) and pigs/cows which move freely/randomly.

![default](https://github.com/DylanZammit/Minecraft2D/blob/master/img/default.png)
![terrain](https://github.com/DylanZammit/Minecraft2D/blob/master/img/terrain.png)

## Food system

The animals can be clicked on (hitting) until we obtain their meat. When our hunger depletes over time, we can right
click on the meat and replenish our hunger, which in turn can replenish our health.

## Mining

Each box type (dirt, stone, coal etc) has a number tied to it corresponding to the number of clicks required to break it
and obtain the underlying material. This number also depends on which tool is currently equipped. The shovel is more
ideal for dirt, while the pickaxe is more ideal for stone and ores. 

## Crafting

After obtaining the materials, we can open the inventory and craft some basic tools! Here we see that we can craft a
pickaxe and even a furnace.

![crafting](https://github.com/DylanZammit/Minecraft2D/blob/master/img/crafting.png)
![crafting2](https://github.com/DylanZammit/Minecraft2D/blob/master/img/crafting2.png)

## Save feature

Want to close the game without losing all your progress? I had even implemented a save feature, allowing us to load it
by referring to its name.

![save](https://github.com/DylanZammit/Minecraft2D/blob/master/img/save.png)

