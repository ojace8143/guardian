# Guardian - Fabric 1.21.1

> **Status: PLANNED - not yet implemented.**
>
> Guardian currently only has a NeoForge build. This page describes the
> intended Fabric port and will be filled in once the Fabric version
> exists.

## Planned behavior

The Fabric port will match the NeoForge build: a server-side mod that
protects the area around the world spawn from PvP and explosions.

- **PvP** - Players cannot attack other players inside the protected area.
- **Explosions** - Blocks and entities affected by an explosion inside the
  protected area are spared.

## Expected configuration

Fabric ports typically use the Fabric API `ConfigResource`/cloth-config or
a simple TOML/JSON file. Exact options will mirror the NeoForge config:

| Option | Default | Description |
|--------|---------|-------------|
| `protectionRadius` | `100` | Radius (in blocks) around world spawn where protection applies. |
| `protectPvP` | `true` | Prevent players from attacking other players inside the protected area. |
| `protectExplosions` | `true` | Prevent explosions from affecting the protected area. |

## Requirements (planned)

- Minecraft 1.21.1
- Fabric Loader + Fabric API for 1.21.1
- Java 21