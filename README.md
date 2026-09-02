# Guardian 1.21.1 NeoForge

## Setup
Will be up for download in releases soon, and eventually on my Github pages website.

## Features
* PvP Protection
* Explosion Protection
* Customizable

## Dependencies
None, all you need is a Neoforge modloader

## How to use it
Download the .jar file, and put it in the mods folder for your server. Make sure your server is 1.21.1 Fabric or else it will not work. Once you boot your server for the first time, it should create a .toml config file where you can change your settings. YOU MUST RESTART YOUR SERVER FOR THE SETTINGS TO APPLY. You are free to change what features are enabled, and the radius where spawn protection is enforced. 

## Differences From Original Fabric Mod
Instead of a mixin like in fabric for explosions, it just uses Neoforge native events.
Also, instead of using fabric API events Neoforge uses the event bus so no callbacks anymore

Uses a .toml file for configuration instead of a .json.

## License 
MIT

## Notes
gonna have a master branch with all the very important information soon. right now just using main for development and pushing out to other branches.
