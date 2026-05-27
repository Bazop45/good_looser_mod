# Good Looser

NeoForge mod for Minecraft **1.21.1** (NeoForge **21.1.229**).

## Features

- **Armor set** (chestplate, leggings, boots): leather-level protection; leggings and boots are unbreakable; chestplate uses leather durability (80).
- **Launch Nail** (default key **G**): fires a fast, gravity-free nail that does not deal damage. On hit, pins the target in place for 20 seconds.
- **Rage bar**: fills when you take or deal damage. When full, the next nail is **empowered** (Weakness III + Resistance I for 20s) and rage resets.

## Build & run

```bash
./gradlew build
./gradlew runClient
```

Requires **Java 21**.

Built JAR: `build/libs/good_looser-<version>.jar`

## Asset folders (add your PNGs here)

| Path | Purpose |
|------|---------|
| `src/main/resources/assets/good_looser/textures/item/` | `chestplate.png`, `leggings.png`, `boots.png` |
| `src/main/resources/assets/good_looser/textures/models/armor/` | `good_looser_layer_1.png` (chest/boots), `good_looser_layer_2.png` (leggings) |
| `src/main/resources/assets/good_looser/textures/entity/projectile/` | `nail.png`, `nail_empowered.png` (optional) |
| `src/main/resources/assets/good_looser/textures/gui/` | `rage_bar.png` (optional; bar is drawn in code by default) |

Item models and equipment JSON are already wired; drop in textures and reload resources.

## Controls

- **G** — Launch nail (common, or empowered when rage bar is full)

Rebind under *Controls → Good Looser*.

## Package

`org.github.kasuroskie`
