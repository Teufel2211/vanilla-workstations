# Support-Matrix: 1.21 bis 26.3

Strategie: **ein Branch pro MC-Linie**, `main` folgt der neuesten stabilen Linie.
Geteilte Quellen in `/shared` (reines `net.minecraft`-API), Loader-Anbindung
dünn in `fabric/`, `neoforge/`, `forge/`. Quilt braucht kein eigenes Modul:
der Fabric-Build enthält `quilt.mod.json` und läuft mit QFAPI.

## Loader-Wahrheiten

- **Fabric:** stabil auf allen Linien (Loader ist versionsagnostisch).
- **NeoForge:** pro MC-Linie eigene Major (21.x = 1.21.1). 26.x braucht NeoForge-26.x.
- **Forge:** nur 1.21.x (1.21.1 = Forge 52.x). **Kein 26.x CalVer-Support.**
- **Quilt:** läuft über den Fabric-Jar (kein separates Build).

## Versionen (verifiziert Sep 2026)

| Linie | Branch | MC | Yarn | Fabric Loader/API | NeoForge | Forge | Java |
|-------|--------|----|------|-------------------|----------|-------|------|
| 1.21.1 | `main` | 1.21.1 | 1.21.1+build.3 | 0.16.14 / 0.109.0+1.21.1 | 21.1.251 | 1.21.1-52.1.9 | 21 |
| 1.21.4 | `mc/1.21.4` | TODO | TODO | TODO | TODO | 1.21.4-5x | 21 |
| 26.1 | `mc/26.1` | TODO | TODO | TODO | NeoForge-26.x | — | 25 |
| 26.2/26.3 | `mc/26.x` | TODO | TODO | TODO | NeoForge-26.x | — | 25 |

TODO = beim Anlegen gegen Maven verifizieren (Muster siehe Verifizierung unten),
nicht blind übernehmen.

## Toolchain (verifiziert)

- Fabric Loom 1.7.4, NeoForge ModDev 2.0.147, ForgeGradle 7.0.40, Gradle 8.11.1.
- CI: `:fabric:build` ist Pflicht (grün). NeoForge/Forge laufen als
  `loaders-experimental` mit `continue-on-error` bis lokal verifiziert.

## Verifizierung (PowerShell-Muster)

```powershell
(Invoke-WebRequest -UseBasicParsing -Uri "https://maven.fabricmc.net/net/fabricmc/yarn/maven-metadata.xml").Content |
  Select-String -Pattern '<version>(1\.21\.4\+build\.[0-9]+)</version>' -AllMatches |
  ForEach-Object { $_.Matches } | Select-Object -Last 2 -ExpandProperty Value
```

Analog für `fabric-loader`, `fabric-api`, NeoForge
(`https://maven.neoforged.net/releases/net/neoforged/neoforge/maven-metadata.xml`),
Forge (`https://maven.minecraftforge.net/net/minecraftforge/forge/maven-metadata.xml`).

## Release-Naming (nur GitHub Releases)

`vanilla-workstations-<mc>-<loader>-<mod>.jar`, z. B.
`vanilla-workstations-1.21.1-fabric-0.1.0.jar`.
Kein Modrinth-/CurseForge-Publish in der CI.
