# Good Looser

NeoForge mod for Minecraft **1.21.1** (NeoForge **21.1.229**).

## Features

- **Armor set** (chestplate, leggings, boots): leather-level protection; leggings and boots are unbreakable; chestplate uses leather durability (80).
- **Set bonus**: with all three pieces equipped, lethal damage is prevented — you stay at 1 HP, are immobilized for 30 seconds, then restored to full health.
- **Launch Nail** (default key **G**, 0.5s cooldown): fast, gravity-free projectile with no direct damage.
  - On hit: target falls to the ground quickly, then is pinned at the landed position for 20 seconds.
  - Each hit chips **5%** durability from one random damageable armor piece (unbreakable armor is skipped).
  - **4+ nails within 5 seconds** disables attacking and shield use for 20 seconds.
- **Rage bar** (fills to **60** when you take or deal damage): when full, the next nail is **empowered** (stronger pin/stack pressure only — no weakness/resistance) and rage resets.

## Build & run

```bash
./gradlew build
./gradlew runClient
```

Requires **Java 21**.

Built JAR: `build/libs/good_looser-<version>.jar`

## Asset folders (add your PNGs / Blockbench exports here)

| Path | Purpose |
|------|---------|
| `assets/good_looser/textures/item/` | Inventory icons: `good_looser_chestplate.png`, `good_looser_leggings.png`, `good_looser_boots.png` (names must match model JSON `layer0` paths) |
| `assets/good_looser/models/item/` | Already has `item/generated` stubs — replace with custom JSON if you use Blockbench item models |
| `assets/good_looser/textures/models/armor/` | Worn armor: `good_looser_layer_1.png` (chest/boots), `good_looser_layer_2.png` (leggings) |
| `assets/good_looser/textures/entity/projectile/` | `nail.png`, `nail_empowered.png` for thrown nail entity renderer |
| `assets/good_looser/models/` | Optional Blockbench **block/entity** JSON for custom 3D nail or armor display (wire paths in renderer/model JSON yourself) |
| `assets/good_looser/textures/gui/` | `rage_bar.png` (optional; bar is drawn in code by default) |

**Your texture plan is correct:** item PNGs for inventory, armor layer PNGs for worn 3D armor, and a projectile texture (or full entity model JSON) for the nail.

## Controls

- **G** — Launch nail (common, or empowered when rage bar is full)

Rebind under *Controls → Good Looser*.

## Package

`org.github.kasuroskie`

## GitHub

After `gh auth login`, create or update the remote:

```bash
gh repo create Kasuroskie/good_looser_mod --public --source=. --remote=origin --push
```

If the repository already exists:

```bash
git remote add origin https://github.com/Kasuroskie/good_looser_mod.git
git branch -M main
git push -u origin main
```
