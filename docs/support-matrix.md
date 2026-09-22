# Support-Matrix: 1.21 bis 26.3 (Vollmatrix, Owner-Vorgabe)

Strategie: **ein Branch pro MC-Linie**, `main` folgt der neuesten stabilen Linie.
Geteilte Quellen in `/shared` (reines `net.minecraft`-API), Loader-Anbindung
dünn in `fabric/`, `neoforge/`, `forge/`. Quilt braucht kein eigenes Modul:
der Fabric-Build enthält `quilt.mod.json` und läuft mit QFAPI.

## Loader-Wahrheiten

- **Fabric:** 1.21.x via Yarn (alle 12 Linien verifiziert). 26.x versucht via
  offizielle Mojang-Mappings (`loom.officialMojangMappings()`, Schalter
  `-Puse_official_mappings=true` in `fabric/build.gradle.kts`), scheitert aber
  hart: Loom-1.18.2 meldet `Failed to find official mojang mappings for 26.x`
  (Run 35748824609, alle 5 Fabric-26.x-Jobs). Yarn/Intermediary enthalten NULL
  26.x. Darum **kein Fabric-26.x** in der Matrix.
- **NeoForge:** pro MC-Linie eigene Major. Ohne stabile Linie -> neueste Beta
  (21.2.1-beta, 21.6.20-beta, 21.7.25-beta, 21.9.16-beta, 26.1.0.19-beta,
  26.1.1.15-beta, 26.3.0.8-beta). 26.x braucht NeoForge-26.x.
- **Forge:** 1.21.x UND 26.x (Owner-Vorgabe, explizit gewuenscht).
  26.x-Pins aus Forge-Maven (26.1-62.0.9 bis 26.3-66.0.2).
  Einzige Luecke: **1.21.2 hat KEIN Forge-Artefakt** (siehe unten).
- **Quilt:** läuft über den Fabric-Jar (kein separates Build).

## Versionen (NEUESTE Koordinate je Linie, per Invoke-WebRequest gegen Maven verifiziert, Stand 2026-09-22, CI-Matrix in build.yml)

| MC | Yarn | Fabric API | NeoForge | Forge (kurz, voll = MC-kurz) | Java | Loader in Matrix |
|----|------|------------|----------|------------------------------|------|------------------|
| 1.21 | 1.21+build.9 | 0.99.5+1.21 | 21.0.167 | 51.0.33 (1.21-51.0.33) | 21 | fabric, neoforge, forge |
| 1.21.1 | 1.21.1+build.3 | 0.116.9+1.21.1 | 21.1.251 | 52.1.16 (1.21.1-52.1.16) | 21 | fabric, neoforge, forge |
| 1.21.2 | 1.21.2+build.1 | 0.106.1+1.21.2 | 21.2.1-beta (kein stabil) | — (kein Artefakt) | 21 | fabric, neoforge |
| 1.21.3 | 1.21.3+build.2 | 0.114.1+1.21.3 | 21.3.97 | 53.1.12 (1.21.3-53.1.12) | 21 | fabric, neoforge, forge |
| 1.21.4 | 1.21.4+build.8 | 0.119.4+1.21.4 | 21.4.157 | 54.1.18 (1.21.4-54.1.18) | 21 | fabric, neoforge, forge |
| 1.21.5 | 1.21.5+build.1 | 0.128.2+1.21.5 | 21.5.98 | 55.1.13 (1.21.5-55.1.13) | 21 | fabric, neoforge, forge |
| 1.21.6 | 1.21.6+build.1 | 0.128.2+1.21.6 | 21.6.20-beta (kein stabil) | 56.0.9 (1.21.6-56.0.9) | 21 | fabric, neoforge, forge |
| 1.21.7 | 1.21.7+build.8 | 0.129.0+1.21.7 | 21.7.25-beta (kein stabil) | 57.0.3 (1.21.7-57.0.3) | 21 | fabric, neoforge, forge |
| 1.21.8 | 1.21.8+build.1 | 0.136.1+1.21.8 | 21.8.54 | 58.1.22 (1.21.8-58.1.22) | 21 | fabric, neoforge, forge |
| 1.21.9 | 1.21.9+build.1 | 0.134.1+1.21.9 | 21.9.16-beta (kein stabil) | 59.0.5 (1.21.9-59.0.5) | 21 | fabric, neoforge, forge |
| 1.21.10 | 1.21.10+build.3 | 0.138.4+1.21.10 | 21.10.64 | 60.1.15 (1.21.10-60.1.15) | 21 | fabric, neoforge, forge |
| 1.21.11 | 1.21.11+build.6 | 0.141.6+1.21.11 | 21.11.45 | 61.2.1 (1.21.11-61.2.1) | 21 | fabric, neoforge, forge |
| 26.1 | — (kein Yarn, kein Fabric-26.x) | 0.145.1+26.1 | 26.1.0.19-beta (kein stabil) | 62.0.9 (26.1-62.0.9) | 25 | neoforge, forge |
| 26.1.1 | — (kein Yarn, kein Fabric-26.x) | 0.145.4+26.1.1 | 26.1.1.15-beta (kein stabil) | 63.0.2 (26.1.1-63.0.2) | 25 | neoforge, forge |
| 26.1.2 | — (kein Yarn, kein Fabric-26.x) | 0.155.3+26.1.2 | 26.1.2.109 | 64.1.3 (26.1.2-64.1.3) | 25 | neoforge, forge |
| 26.2 | — (kein Yarn, kein Fabric-26.x) | 0.161.0+26.2 | 26.2.0.88 | 65.1.3 (26.2-65.1.3) | 25 | neoforge, forge |
| 26.3 | — (kein Yarn, kein Fabric-26.x) | 0.161.0+26.3 | 26.3.0.8-beta (kein stabil) | 66.0.2 (26.3-66.0.2) | 25 | neoforge, forge |

Summe: 45 Matrix-Zeilen (17x3 minus forge-1.21.2 minus 5x fabric-26.x).

Ausgelassen (je mit hartem Beleg, kein Vermutung):

1. **forge-1.21.2**.
`https://maven.minecraftforge.net/net/minecraftforge/forge/maven-metadata.xml`
enthaelt NULL `<version>` mit `1.21.2` (per Invoke-WebRequest geprueft:
`[regex]::Matches($forge,"<version>([^<]*1\.21\.2[^<]*)</version>")` = leer,
Count 0; alle anderen Linien 1.21-51.0.33 bis 61.2.1 + 26.x vorhanden).

2. **fabric-26.x (26.1, 26.1.1, 26.1.2, 26.2, 26.3)** – doppelt belegt:
   a) Metadaten: `https://maven.fabricmc.net/net/fabricmc/yarn/maven-metadata.xml`
      enthaelt NULL `<version>(26\.[^<]*)</version>` (Count 0); ebenso
      `https://maven.fabricmc.net/net/fabricmc/intermediary/maven-metadata.xml`
      (NULL 26.x). Geprueft per Invoke-WebRequest 2026-09-22.
   b) Log: Run https://github.com/Teufel2211/vanilla-workstations/actions/runs/35748824609,
      alle 5 Fabric-26.x-Jobs X mit `A problem occurred configuring project ':fabric'
      > Failed to setup Minecraft, java.lang.RuntimeException:
      Failed to find official mojang mappings for 26.x`
      (Job-IDs 106817271763/106817271788/106817271878/106817271925/106817272000,
      Logs via `gh api .../actions/jobs/<id>/logs`).
   Der `-Puse_official_mappings`-Schalter existiert (fabric/build.gradle.kts),
   Loom findet aber keine Mojang-Mappings fuer 26.x. Technisch unmoeglich
   mit Loom 1.18.2. NeoForge/Forge-26.x bleiben drin (Beta explizit gewuenscht,
   Forge-userdev per HEAD 200 verifiziert).
Alle anderen Forge-Linien existieren (1.21-51.0.33 bis 1.21.11-61.2.1,
26.1-62.0.9, 26.1.1-63.0.2, 26.1.2-64.1.3, 26.2-65.1.3, 26.3-66.0.2).
Fabric-1.21.2/NeoForge-1.21.2-Zeilen nutzen forge-Platzhalter 53.1.12
(naechste reale Linie, wird nie gebaut, nur fuer :forge-Config).

Belege 26.x-Fabric:
`https://maven.fabricmc.net/net/fabricmc/yarn/maven-metadata.xml` enthaelt NULL
`<version>(26\.[^<]*)</version>`; ebenso
`https://maven.fabricmc.net/net/fabricmc/intermediary/maven-metadata.xml`
(NULL 26.x). Darum Fabric-26.x mit `officialMojangMappings()` statt Yarn.

## Toolchain (verifiziert)

- Fabric Loom 1.18.2, NeoForge ModDev 2.0.147, ForgeGradle 7.0.40, Gradle 9.7.1.
- CI-Runtime: JVM 25 (Loom-1.18.2-Pflicht: JVM >= 25 + Gradle-Plugin-API 9.7.0);
  Code-Target per -Pjava_version (21 fuer 1.21.x, 25 fuer 26.x).
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
Negativ-Beleg Forge-1.21.2:
```powershell
$forge=(Invoke-WebRequest -UseBasicParsing -Uri "https://maven.minecraftforge.net/net/minecraftforge/forge/maven-metadata.xml").Content
[regex]::Matches($forge,"<version>([^<]*1\.21\.2[^<]*)</version>").Count  # = 0
```
Negativ-Beleg Yarn-26.x:
```powershell
$yarn=(Invoke-WebRequest -UseBasicParsing -Uri "https://maven.fabricmc.net/net/fabricmc/yarn/maven-metadata.xml").Content
[regex]::Matches($yarn,"<version>(26\.[^<]*)</version>").Count  # = 0
```

## Release-Naming (nur GitHub Releases)

`vanilla-workstations-<mc>-<loader>-<mod>.jar`, z. B.
`vanilla-workstations-1.21.1-fabric-0.1.0.jar`.
Kein Modrinth-/CurseForge-Publish in der CI.
