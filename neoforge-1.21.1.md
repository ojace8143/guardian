# Guardian - NeoForge 1.21.1

This is the current, working build of Guardian. It runs as a server-side
mod on NeoForge for Minecraft 1.21.1.

## How it works

Guardian protects the area around the world spawn point. A location is
"protected" when it falls within `protectionRadius` blocks of spawn
(euclidean distance, measured from the center of the spawn block).

While inside the protected area:

- **PvP** - Players cannot attack other players.
- **Explosions** - Blocks and entities that would be affected by an
  explosion are removed from the explosion's effect before it detonates.

Protection is checked against the attacker's position for PvP, and against
each affected block/entity position for explosions.

## Configuration

Guardian ships a server config file and supports the following options:

| Option | Default | Description |
|--------|---------|-------------|
| `protectionRadius` | `100` | Radius (in blocks) around world spawn where protection applies. |
| `protectPvP` | `true` | Prevent players from attacking other players inside the protected area. |
| `protectExplosions` | `true` | Prevent explosions from affecting the protected area. |

The config file is generated at
`config/guardian-server.toml` on first run. Changes require a server
restart.

## Installation

1. Download the Guardian `-neoforge-1.21.1` jar for your version.
2. Place it in the `mods/` folder of your NeoForge server.
3. Restart the server.

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1.248+ (the current release of Guardian is build against
  21.1.248)
- Java 21