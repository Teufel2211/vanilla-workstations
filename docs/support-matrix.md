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

## Versionen (gegen Maven verifiziert, Stand 2026-09-22, CI-Matrix in build.yml)

| MC | Yarn | Fabric API | NeoForge | Forge (kurz) | Java | Loader in Matrix |
|----|------|------------|----------|--------------|------|------------------|
| 1.21.1 | 1.21.1+build.3 | 0.109.0+1.21.1 (Baseline-Pin) | 21.1.251 | 52.1.9 | 21 | fabric, neoforge, forge |
| 1.21.4 | 1.21.4+build.8 | 0.119.4+1.21.4 | 21.4.157 | 54.1.8 | 21 | fabric, neoforge, forge |
| 1.21.8 | 1.21.8+build.1 | 0.136.1+1.21.8 | 21.8.54 | 58.1.9 | 21 | fabric, neoforge, forge |
| 1.21.11 | 1.21.11+build.6 | 0.141.6+1.21.11 | 21.11.45 | 61.2.1 | 21 | fabric, neoforge, forge |
| 26.1.2 | — (kein Yarn) | 0.155.3+26.1.2 | 26.1.2.109 | — | 25 | neoforge |
| 26.2 | — (kein Yarn) | 0.161.0+26.2 | 26.2.0.88 | — | 25 | neoforge |

Ausgelassen (begruendet): **26.3** (Mojang-Release vom 2026-09-15, FAPI
0.161.0+26.3 vorhanden, aber NeoForge nur Beta 26.3.0.0–0.8, kein Yarn/Intermediary
fuer 26.x) sowie **Fabric-26.x** generell (Yarn- und Intermediary-Metadaten enthalten
kein einziges 26.x-Mapping) und **Forge-26.x** (in Maven vorhanden, z. B.
26.3-66.0.2, aber per Vorgabe nur 1.21.x in der Matrix). Zwischen-Patches
(1.21.2/1.21.3/1.21.5–1.21.7/1.21.9/1.21.10, 26.1/26.1.1) teils ohne stabile
NeoForge-Linie (21.2/21.6/21.7/21.9 = 0 stabile) und daher nicht als geschlossener
Satz baubar; abgedeckt ueber frueh/mitte/spaet/latest-Samples der 1.21-Linie.

## Toolchain (verifiziert)

- Fabric Loom 1.18.2, NeoForge ModDev 2.0.147, ForgeGradle 7.0.40, Gradle 9.7.1.
- CI-Runtime: JVM 25 (Loom-1.18.2-Pflicht: JVM >= 25 + Gradle-Plugin-API 9.7.0);
  Code-Target bleibt Java 21 fuer MC 1.21.1.
- CI: `:fabric:build` (Baseline 1.21.1) ist Pflicht (gruen, Release-Gate).
  Zusaetzlich baut der Matrix-Job `build` alle Tabellen-Kombis per -P-Override
  (`fail-fast: false`, Artefakte via upload-artifact, nicht im Release).
- Stand Sep 2026: NeoForge kompiliert, scheitert nur an doppelter
  `META-INF/neoforge.mods.toml` (gecheckt vs. ModDev-generiert) - via
  `duplicatesStrategy = EXCLUDE` abgefangen. Forge braucht noch fehlende
  Compile-Deps (fml-Modklasse, Gson) - als experimental belassen.

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
