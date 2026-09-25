# Support-Matrix: 1.21 bis 26.3 (Vollmatrix, Owner-Vorgabe)

Strategie: **ein Branch pro MC-Linie**, `main` folgt der neuesten stabilen Linie.
Geteilte Quellen in `/shared` (reines `net.minecraft`-API), Loader-Anbindung
dünn in `fabric/`, `neoforge/`, `forge/`. Quilt braucht kein eigenes Modul:
der Fabric-Build enthält `quilt.mod.json` und läuft mit QFAPI.

## Loader-Wahrheiten

- **Fabric:** 1.21.x via Yarn im Modul `:fabric` (Remap-Loom `fabric-loom`
  legacy-ID, alle 12 Linien verifiziert). 26.1+ ist UNOBFUSZIERT (Mojang
  liefert lesbaren Code mit Parameternamen) und laeuft ueber das zweite Modul
  `:fabric26` mit No-Remap-Loom (`net.fabricmc.fabric-loom`): KEINE
  `mappings(...)`-Dependency (weder Yarn noch officialMojangMappings),
  `implementation` statt `modImplementation`, Artefakt aus `jar`/`build`
  (remapJar existiert dort nicht). Yarn/Intermediary sind tot (enden bei
  1.21.11, Yarn-Repo 2025 deprecated); Mojang-`client_mappings` fuer 26.x
  existieren nicht (Loom-1.18.2 `Failed to find official mojang mappings
  for 26.x`, Run 35748824609) – der alte `-Puse_official_mappings`-Ansatz
  konnte nie funktionieren. Belege: https://docs.fabricmc.net/develop/porting/mappings
  (Abschnitt „Minecraft 26.1 is unobfuscated… no need for any obfuscation
  mappings"), https://fabricmc.net/2025/10/31/obfuscation.html,
  https://github.com/FabricMC/fabric-loom/issues/1585 (exakt unser Fehler),
  Plugin-IDs + Dependency-Configs:
  https://raw.githubusercontent.com/FabricMC/fabric-docs/main/versions/26.1.2/develop/loom/index.md.
  Loader 0.19.5 fuer 26.x (kanonisch per fabric-example-mod/26.1),
  0.16.14 fuer 1.21.x.
- **NeoForge:** pro MC-Linie eigene Major. Ohne stabile Linie -> neueste Beta
  (21.2.1-beta, 21.6.20-beta, 21.7.25-beta, 21.9.16-beta, 26.1.0.19-beta,
  26.1.1.15-beta, 26.3.0.8-beta). 26.x braucht NeoForge-26.x.
- **Forge:** 1.21.x UND 26.x (Owner-Vorgabe, explizit gewuenscht).
  26.x-Pins aus Forge-Maven (26.1-62.0.9 bis 26.3-66.0.2).
  Einzige Luecke: **1.21.2 hat KEIN Forge-Artefakt** (siehe unten).
- **Quilt:** läuft über den Fabric-Jar (kein separates Build).

## Versionen (NEUESTE Koordinate je Linie, per Invoke-WebRequest gegen Maven verifiziert, Stand 2026-09-25, CI-Matrix in build.yml)

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
| 26.1 | — (unobfusziert, No-Remap-Loom, Loader 0.19.5) | 0.145.1+26.1 | 26.1.0.19-beta (kein stabil) | 62.0.9 (26.1-62.0.9) | 25 | fabric (:fabric26), neoforge, forge |
| 26.1.1 | — (unobfusziert, No-Remap-Loom, Loader 0.19.5) | 0.145.4+26.1.1 | 26.1.1.15-beta (kein stabil) | 63.0.2 (26.1.1-63.0.2) | 25 | fabric (:fabric26), neoforge, forge |
| 26.1.2 | — (unobfusziert, No-Remap-Loom, Loader 0.19.5) | 0.155.3+26.1.2 | 26.1.2.109 | 64.1.3 (26.1.2-64.1.3) | 25 | fabric (:fabric26), neoforge, forge |
| 26.2 | — (unobfusziert, No-Remap-Loom, Loader 0.19.5) | 0.161.0+26.2 | 26.2.0.88 | 65.1.3 (26.2-65.1.3) | 25 | fabric (:fabric26), neoforge, forge |
| 26.3 | — (unobfusziert, No-Remap-Loom, Loader 0.19.5) | 0.161.0+26.3 | 26.3.0.8-beta (kein stabil) | 66.0.2 (26.3-66.0.2) | 25 | fabric (:fabric26), neoforge, forge |

Summe: 50 Matrix-Zeilen (17x3 minus forge-1.21.2).

Ausgelassen (mit hartem Beleg, keine Vermutung):

1. **forge-1.21.2**.
`https://maven.minecraftforge.net/net/minecraftforge/forge/maven-metadata.xml`
enthaelt NULL `<version>` mit `1.21.2` (per Invoke-WebRequest geprueft:
`[regex]::Matches($forge,"<version>([^<]*1\.21\.2[^<]*)</version>")` = leer,
Count 0; alle anderen Linien 1.21-51.0.33 bis 61.2.1 + 26.x vorhanden).

Alle anderen Forge-Linien existieren (1.21-51.0.33 bis 1.21.11-61.2.1,
26.1-62.0.9, 26.1.1-63.0.2, 26.1.2-64.1.3, 26.2-65.1.3, 26.3-66.0.2).
Fabric-1.21.2/NeoForge-1.21.2-Zeilen nutzen forge-Platzhalter 53.1.12
(naechste reale Linie, wird nie gebaut, nur fuer :forge-Config).

Belege 26.x-Fabric (No-Remap-Loom statt Mappings):
`https://maven.fabricmc.net/net/fabricmc/yarn/maven-metadata.xml` enthaelt NULL
`<version>(26\.[^<]*)</version>`; ebenso
`https://maven.fabricmc.net/net/fabricmc/intermediary/maven-metadata.xml`
(NULL 26.x). Darum :fabric26 OHNE `mappings(...)` (vgl. kanonisch
https://github.com/FabricMC/fabric-example-mod/tree/26.1:
`id "net.fabricmc.fabric-loom"`, `minecraft "com.mojang:minecraft:..."`,
`implementation` fuer Loader 0.19.5 + FAPI 0.145.1+26.1).
Hinweis Quilt: fabric26s `quilt.mod.json` laesst `intermediate_mappings` weg
(kein Intermediary-26.x vorhanden); Quilt selbst ist nicht in der Matrix.

## Toolchain (verifiziert)

- Fabric Loom 1.18.2 (zwei Plugin-IDs: `fabric-loom` legacy = Remap fuer
  1.21.x in :fabric; `net.fabricmc.fabric-loom` = No-Remap fuer 26.x in
  :fabric26), NeoForge ModDev 2.0.147, ForgeGradle 7.0.40, Gradle 9.7.1.
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
