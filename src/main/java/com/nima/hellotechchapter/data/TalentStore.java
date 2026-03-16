package com.nima.hellotechchapter.data;

import com.nima.hellotechchapter.model.Document;
import com.nima.hellotechchapter.model.Talent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TalentStore {

    private static final String NIMA_ID    = UUID.randomUUID().toString();
    private static final String ANDREAS_ID = UUID.randomUUID().toString();
    private static final String DOC_NIMA_1 = UUID.randomUUID().toString();
    private static final String DOC_NIMA_2 = UUID.randomUUID().toString();
    private static final String DOC_NIMA_3 = UUID.randomUUID().toString();
    private static final String DOC_NIMA_4 = UUID.randomUUID().toString();

    private final List<Talent> talents = List.of(
            new Talent(
                    NIMA_ID,
                    "Nima Salami",
                    "Fra drift til DevOps | Datamatikerstuderende på 4. semester",
                    "Fra køkken til kommunal drift til infrastruktur. Fælles for det hele er at jeg altid har forstået hvordan arbejde faktisk udføres, hvor friktionen er og hvordan den opstår. Det er den sans der naturligt har ført mig mod DevOps. Jeg drifter egne produktionskørende servere i fritiden og drives af en nysgerrighed der ikke stopper ved at få noget til at virke. Jeg vil forstå hvorfor det virker, og hvad der sker når det ikke gør.",
                    "nima@nimasalami.dk",
                    "+45 22 98 50 77",
                    "København NV",
                    "Danmark",
                    "https://github.com/hajisan",
                    "https://www.linkedin.com/in/hello-nima/"
            ),
            new Talent(
                    ANDREAS_ID,
                    "Andreas Gabel",
                    "Datamatiker-studerende | DevOps & Backend",
                    "Min vej hertil er lidt anderledes. " +
                            "Jeg har navigeret skibe i søværnet, studeret teologi, latin og filosofi på KU " +
                            "og undervist elever med autisme. " +
                            "Den erfaring har givet mig noget de fleste IT-studerende ikke har: " +
                            "at fungere under ansvar i situationer der ikke følger en opskrift. " +
                            "I søværnet lærte jeg at én mand er ingen mand " +
                            "og hos Tech Chapter kalder de det at løfte i flok. " +
                            "Det er den samme overbevisning jeg tager med ind i DevOps.",
                    "andreassgabel@hotmail.com",
                    "+45 60 77 66 13",
                    "København",
                    "Danmark",
                    "https://github.com/Gabel1998",
                    "https://www.linkedin.com/in/andreas-søgaard-gabel-758991133"
            )
    );

    private final Map<String, List<Document>> documents = Map.of(
            NIMA_ID, List.of(
                    new Document(
                            DOC_NIMA_1,
                            "Projekt: nimasalami.dk",
                            "LINK: https://www.nimasalami.dk | API: https://api.nimasalami.dk. " +
                                    "REPO: Privat monorepo. " +
                                    "TYPE: Eget projekt – live i produktion. " +
                                    "BESKRIVELSE: Monorepo med Astro-frontend (TypeScript, Tailwind CSS v3, React-komponenter) " +
                                    "hosted på Vercel med auto-deploy fra main. " +
                                    "FastAPI-backend (Python 3.12, 2 Uvicorn workers) bag nginx med SSL via Let's Encrypt, " +
                                    "hosted på DigitalOcean Droplet (Ubuntu 24.04). " +
                                    "Docker Compose med tre services: API, Redis (TTL 300s) og Watchtower (auto-pull fra GHCR hvert 5. minut). " +
                                    "CI/CD via GitHub Actions: multi-stage Dockerfile henter ML-model (veg.tflite) " +
                                    "fra offentligt repo via git-lfs (MODEL_REPO_TOKEN bruges kun i build-stage, aldrig i det endelige image). " +
                                    "Live ML-demo: 15-klasses vegetableklassifikator (ResNet50/TFLite) med thread-safe " +
                                    "inference via asyncio.Lock(), HEIC/HEIF-understøttelse, 5 MB upload-cap og rate limiting (20/min). " +
                                    "Sikkerhed: UFW, Fail2ban, SSH key-only, CORS ikke wildcard, " +
                                    "ALLOWED_REPOS-whitelist og rate limiting på publikt eksponerede endpoints (/contact og /scan). " +
                                    "STACK: Astro, TypeScript, Tailwind CSS, React, FastAPI, Python, Redis, " +
                                    "Docker, GitHub Actions, nginx, Let's Encrypt, Brevo, TFLite, Vercel, DigitalOcean. " +
                                    "HVAD JEG LÆRTE: Sikkerhed som et arkitekturvalg frem for en eftertanke: " +
                                    "multi-stage builds der holder secrets ude af images, " +
                                    "input-whitelisting, thread-safe inference og automatiserede deploys " +
                                    "der ikke kræver manuel indgriben."
                    ),
                    new Document(
                            DOC_NIMA_2,
                            "Projekt: MonkKnows (DevOps Legacy)",
                            "LINK: http://4.225.161.111 (ingen domæne, direkte IP på Azure). " +
                                    "REPO: https://github.com/nasOps/MonkKnows (offentligt, gruppe). " +
                                    "TYPE: Gruppeprojekt (3 personer), DevOps valgfag. " +
                                    "BESKRIVELSE: Søgemaskine fra 2009, projektet startede som Python 2, " +
                                    "blev migreret til Python 3 (Flask), og er derefter rewritet i Ruby 3 + Sinatra. " +
                                    "Monorepo med legacy-Flask og aktiv Ruby/Sinatra applikation side om side under migration. " +
                                    "Multi-stage Dockerfile: build-stage installerer gems og kompilerer afhængigheder, " +
                                    "runtime-stage er lean (ruby:3.2-slim, non-root user, kun nødvendige runtime deps). " +
                                    "To Compose-filer: dev med hot-reload via Guard og mounted kildekode, " +
                                    "prod med nginx reverse proxy og image fra GHCR (ghcr.io/nasops/monkknows:latest). " +
                                    "CI/CD: GitHub Actions med RuboCop (linting) og RSpec (tests) som gates på alle PRs. " +
                                    "Branching: customized GitHub Flow med development-lag, " +
                                    "main kræver 1 approval og grøn CI, squash merges, branch navne auto-genereret fra GitHub issues. " +
                                    "API dokumenteret med OpenAPI Spec efter instruktors reference-spec med bevidste afvigelser dokumenteret eksplicit. " +
                                    "3rd party: OpenWeather API via backend service-lag med in-memory caching (10 min TTL). " +
                                    "Test-miljø: in-memory SQLite i RSpec, kører identisk lokalt og i CI. " +
                                    "STACK: Ruby 3.2, Sinatra, ActiveRecord, SQLite, RuboCop, RSpec, " +
                                    "Python 3, Flask, Docker, nginx, GitHub Actions, GHCR, Azure. " +
                                    "HVAD JEG LÆRTE: At port fra et framework til et andet kopierer fejl hvis man ikke forstår kontrakten. " +
                                    "OpenAPI spec skal være source of truth inden implementering, ikke efter. " +
                                    "CI afslører miljøafhængigheder der er usynlige under lokal udvikling. " +
                                    "Multi-stage builds holder build-værktøjer ude af produktionsimaget."
                    ),
                    new Document(
                            DOC_NIMA_3,
                            "Projekt: n8n Server",
                            "REPO: https://github.com/hajisan/n8n-server (offentligt). " +
                                    "TYPE: Eget projekt, selvhostet, live i produktion. " +
                                    "BESKRIVELSE: Selvhostet n8n automationsserver på DigitalOcean Droplet (Ubuntu 24.04, Frankfurt). " +
                                    "n8n kører som Docker container bundet til Tailscale-interfacet, ikke 0.0.0.0, " +
                                    "da Docker bypasser iptables fuldstændigt og binding er den eneste pålidelige måde at isolere adgang. " +
                                    "Admin-adgang udelukkende via Tailscale VPN. " +
                                    "Webhooks eksponeret via Cloudflare Quick Tunnel (HTTPS uden åbne porte eller domæne). " +
                                    "Sikkerhed: SSH key-only, Fail2ban (max 3 forsøg, 1 times ban), ingen password-login. " +
                                    "Auto-opdatering via to cron-scripts der kører hver søndag: " +
                                    "system-opdatering kl. 02:00 (apt upgrade + reboot ved kernel-update), " +
                                    "n8n-opdatering kl. 03:00 (docker pull + compose restart med version-logging). " +
                                    "Log rotation: månedligt, 6 måneders retention. " +
                                    "STACK: n8n, Docker, Docker Compose, Tailscale, Cloudflare Tunnel, Fail2ban, Ubuntu 24.04, DigitalOcean. " +
                                    "HVAD JEG LÆRTE: Docker bypasser iptables og UFW, port binding til en specifik IP " +
                                    "er den eneste pålidelige måde at begrænse adgang på. " +
                                    "Tailscale og Cloudflare Tunnel kan kombineres på samme port: " +
                                    "VPN til admin, tunnel til webhooks."
                    ),
                    new Document(
                            DOC_NIMA_4,
                            "Projekt: GymRat",
                            "LINK: https://gym.nimasalami.dk/demo. (demo)" +
                                    "REPO: https://github.com/hajisan/gymrat (offentligt). " +
                                    "TYPE: Eget projekt, live i produktion, brugt dagligt. " +
                                    "BESKRIVELSE: Personlig træningstracker bygget som PWA til iPhone, ligger på homescreen og bruges hver træningsdag. " +
                                    "REST API i Spring Boot 3.5 + Java 21 med MySQL i produktion og H2 in-memory til tests. " +
                                    "Domænemodel: Exercise, PerformedExercise, TrainingSession og PerformedSet, " +
                                    "med enums for ExerciseType (rep/duration/begge) og SideOfBody. " +
                                    "Spring Security 6: form-based auth, BCrypt password hashing, remember-me cookie (90 dage), " +
                                    "credentials via environment variables. " +
                                    "Tre Spring-profiler: dev (MySQL, ddl-auto=update), prod (MySQL, ddl-auto=validate, fejlbeskeder skjult), " +
                                    "test (H2 create-drop, ingen eksterne afhængigheder). " +
                                    "Multi-stage Dockerfile: eclipse-temurin:21-jre-alpine, non-root user (gymrat, UID 1001), " +
                                    "JVM tunet til 1 GB RAM (-Xms256m -Xmx512m -XX:+UseG1GC). " +
                                    "CI/CD: to GitHub Actions workflows, java.yaml kører tests ved push/PR, " +
                                    "deploy.yml bygger image, pusher til GHCR og SSH-deployer til DigitalOcean. " +
                                    "STACK: Java 21, Spring Boot 3.5, Spring Security 6, Spring Data JPA, Hibernate, " +
                                    "MySQL 8, H2, Thymeleaf, Docker, GitHub Actions, GHCR, DigitalOcean, PWA. " +
                                    "HVAD JEG LÆRTE: At designe en lagdelt arkitektur (controller, service interface, impl, repository) " +
                                    "og holde forretningslogik ude af controllers. " +
                                    "Spring-profiler som mekanisme til at adskille dev/prod/test-konfiguration uden kodeændringer. " +
                                    "JVM-tuning i containeriserede miljøer med begrænset RAM. " +
                                    "At lave en sikkerhedsanalyse af sin egen kode afslører blinde vinkler, " +
                                    "CSRF, token-håndtering og fallback-credentials er ikke synlige fejl når man bygger, " +
                                    "men oplagte angrebspunkter udefra."
                    )
            )
    );

    public List<Talent> getAllTalents() {
        return talents;
    }

    public Optional<Talent> getTalentById(String id) {
        return talents.stream()
                .filter(talent -> talent.getId().equals(id))
                .findFirst();
    }

    public List<Document> getDocumentsByTalentId(String id) {
        return documents.getOrDefault(id, List.of());
    }

    public Optional<Document> getDocumentById(String talentId, String documentId) {
        return getDocumentsByTalentId(talentId).stream()
                .filter(d -> d.getId().equals(documentId))
                .findFirst();
    }
}
