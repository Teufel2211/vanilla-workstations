# Vanilla Workstations

Nutzlose Blöcke sinnvoll machen: **Fletching Table, Hopper-Filter, Composter-Fix.**
Vanilla-Fit, keine neuen Erze, keine neuen Mobs.

> Vertrieb nur über **GitHub Releases** in diesem Repo. Kein Modrinth, kein CurseForge.

## Download & Installation

1. Rechts unter **Releases** die passende Datei laden:
   `vanilla-workstations-<MC-Version>-<loader>-<mod-version>.jar`
   Beispiel: `vanilla-workstations-1.21.1-fabric-0.1.0.jar`
2. Datei in den `mods`-Ordner legen (Fabric braucht zusätzlich Fabric API + Fabric Loader).
3. Versionen beachten (siehe `docs/support-matrix.md`).

## Support-Matrix (Kurzfassung)

| MC | Fabric | NeoForge | Forge | Quilt |
|----|--------|----------|-------|-------|
| 1.21.1 (main) | ✅ | ✅ | ✅ | ✅ via Fabric-Jar |
| 1.21.4 / 26.x | Branches `mc/<version>` | dito | nur 1.21.x (Forge kann kein 26.x) | via Fabric-Jar |

Details: `docs/support-matrix.md`. Quilt braucht kein eigenes Jar:
der Fabric-Build enthält `quilt.mod.json`.

## Features (MVP)

- **Fletching Table:** GUI mit 3 Slots, JSON-Rezepte (`data/...`), Basis: Pfeile,
  Spektral-/Getränkte Pfeile, neu: Gehärteter Pfeil (Quarz, pierct), Tauch-Pfeil (Prismarin).
- **Hopper:** Slots einzeln an/aus (wie Crafter), Sneak+Rechtsklick.
- **Composter:** Füllstand lesbar, Hopper oben rein / unten nur Knochenmehl bei voll.
- Alles einzeln an/aus: `config/vanilla-workstations.json`.

## Bauen

```bash
gradle :fabric:build
```

Java 21 für 1.21.x, Java 25 für 26.x. Alle Dependency-Versionen in
`gradle.properties` sind gegen Maven verifiziert.

## Lizenz

MIT, siehe `LICENSE`.
