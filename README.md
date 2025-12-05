# 🌱 EcoTrack - Green IT Carbon Tracking Platform

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9.11-blue.svg)](https://maven.apache.org/)
[![Tests](https://img.shields.io/badge/tests-133%20passed-brightgreen.svg)](https://github.com/houssemeddinelassoued/ecotrack)
[![Coverage](https://img.shields.io/badge/coverage-~100%25-brightgreen.svg)](https://github.com/houssemeddinelassoued/ecotrack)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

> **Track, measure, and optimize your IT infrastructure's carbon footprint with precision.**

EcoTrack is a multi-tenant SaaS platform designed to help organizations monitor and reduce their IT asset energy consumption. Built with Domain-Driven Design principles and pure Java, EcoTrack provides granular tracking of daily energy consumption across all IT infrastructure.

---

## 📋 Table of Contents

- [Features](#-features)
- [Architecture](#-architecture)
- [Domain Model](#-domain-model)
- [Getting Started](#-getting-started)
- [Testing](#-testing)
- [Quality Gate](#-quality-gate)
- [Technology Stack](#-technology-stack)
- [Project Structure](#-project-structure)
- [Roadmap](#-roadmap)
- [Contributing](#-contributing)
- [License](#-license)

---

## ✨ Features

### Current (v1.0)
- ✅ **Multi-tenant Architecture**: Complete data isolation per organization
- ✅ **Domain-Driven Design**: Pure Java entities with zero framework coupling
- ✅ **Comprehensive Testing**: 133 unit tests with ~100% code coverage
- ✅ **Energy Tracking**: Daily consumption monitoring with BigDecimal precision
- ✅ **Asset Management**: IT asset lifecycle tracking with employee assignment
- ✅ **Category System**: Flexible asset categorization (global or company-specific)
- ✅ **Data Validation**: Built-in email validation and null-safety checks
- ✅ **Quality Assurance**: Passing Quality Gate with JaCoCo coverage analysis

### Planned (Roadmap)
- 🔜 Persistence layer with JPA/Hibernate
- 🔜 REST API with Spring Boot
- 🔜 Authentication & Authorization (OAuth2/JWT)
- 🔜 Carbon footprint calculation engine
- 🔜 Analytics dashboard & reporting
- 🔜 Real-time monitoring & alerts
- 🔜 Multi-region deployment support

---

## 🏗️ Architecture

EcoTrack follows **Domain-Driven Design (DDD)** principles with a clean hexagonal architecture:

```
┌─────────────────────────────────────────────────┐
│          Presentation Layer (Future)            │
│        REST API / GraphQL / Web UI              │
└─────────────────────────────────────────────────┘
                      ▼
┌─────────────────────────────────────────────────┐
│        Application Services (Future)            │
│      Use Cases / Business Workflows             │
└─────────────────────────────────────────────────┘
                      ▼
┌─────────────────────────────────────────────────┐
│          ✅ DOMAIN LAYER (Current)              │
│     Pure Java Entities / Business Logic         │
│   Entreprise │ Employe │ ActifIT │ etc.        │
└─────────────────────────────────────────────────┘
                      ▼
┌─────────────────────────────────────────────────┐
│        Infrastructure Layer (Future)            │
│  Persistence / External APIs / Messaging        │
└─────────────────────────────────────────────────┘
```

**Current Status:** Domain layer complete with comprehensive testing. Infrastructure and application layers are next priorities.

---

## 📦 Domain Model

### Core Entities

```
Entreprise (Root Aggregate)
    │
    ├── Employe
    │       └── ActifIT (assigned assets)
    │
    ├── ActifIT (all company assets)
    │       ├── CategorieActif (asset type)
    │       └── ConsoJournaliere (daily telemetry)
    │
    └── CategorieActif (company-specific categories)
```

### Entity Descriptions

#### 🏢 **Entreprise** (Company/Tenant)
- **Purpose:** Root aggregate representing a client organization
- **Key Fields:** `id`, `nom`, `siret`, `adresse`, `pays`, `emailContact`, `dateCreation`, `actif`
- **Responsibility:** Multi-tenant boundary and company master data

#### 👤 **Employe** (Employee)
- **Purpose:** Company employee with IT asset assignments
- **Key Fields:** `id`, `entrepriseId`, `nom`, `prenom`, `email`, `poste`, `dateEmbauche`
- **Validation:** Email format validation, null-safety on entrepriseId
- **Responsibility:** User identity and asset ownership tracking

#### 💻 **ActifIT** (IT Asset)
- **Purpose:** Physical or virtual IT infrastructure (servers, laptops, etc.)
- **Key Fields:** `id`, `entrepriseId`, `categorieActifId`, `employeId`, `numeroSerie`, `marque`, `modele`, `statut`, `dateAcquisition`
- **Responsibility:** Asset lifecycle management and energy consumption tracking

#### 📊 **CategorieActif** (Asset Category)
- **Purpose:** Asset type reference data with average consumption metrics
- **Key Fields:** `id`, `entrepriseId`, `code`, `libelle`, `consommationMoyenneKwh`, `estGlobal`
- **Features:** Supports both global (shared) and company-specific categories
- **Responsibility:** Asset classification and baseline consumption data

#### ⚡ **ConsoJournaliere** (Daily Consumption)
- **Purpose:** Time-series telemetry for daily energy consumption
- **Key Fields:** `id`, `actifItId`, `entrepriseId`, `dateConsommation`, `consoKwh`, `source`
- **Precision:** Uses `BigDecimal` for financial-grade accuracy
- **Responsibility:** Energy consumption metrics and carbon footprint calculation basis

---

## 🚀 Getting Started

### Prerequisites

- **Java 21 (LTS)** - [Download OpenJDK](https://openjdk.org/)
- **Maven 3.9.11+** - [Installation Guide](https://maven.apache.org/install.html)
- **Git** - [Download](https://git-scm.com/)

### Installation

```bash
# Clone the repository
git clone https://github.com/houssemeddinelassoued/ecotrack.git
cd ecotrack

# Build the project
mvn clean install

# Run the demo application
mvn exec:java -Dexec.mainClass="com.ecotrack.Main"
```

### Expected Output

```
═══════════════════════════════════════
  ECOTRACK - Demonstration Domaine
═══════════════════════════════════════

[ENTREPRISE CREEE]
  ID: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
  Nom: GreenTech Solutions
  SIRET: 12345678901234
  Pays: France
  Email: contact@greentech.fr
  Actif: Oui

[EMPLOYE CREE]
  ID: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
  Nom: MARTIN Alice
  Email: alice.martin@greentech.fr
  Poste: Developpeur Full-Stack
  
[... additional output ...]
```

---

## 🧪 Testing

### Test Coverage

EcoTrack has **133 comprehensive unit tests** covering:
- ✅ Constructors and initialization
- ✅ Getters and setters
- ✅ Edge cases and null-safety
- ✅ Business scenarios and workflows
- ✅ Data validation logic

### Running Tests

```bash
# Run all tests
mvn test

# Run tests with coverage
mvn clean test jacoco:report

# View coverage report
# Open: target/site/jacoco/index.html
```

### Test Results

| Entity | Tests | Coverage |
|--------|-------|----------|
| Employe | 45 | ~100% |
| Entreprise | 25 | ~100% |
| ActifIT | 23 | ~100% |
| CategorieActif | 17 | ~100% |
| ConsoJournaliere | 23 | ~100% |
| **TOTAL** | **133** | **~100%** |

---

## 🔒 Quality Gate

EcoTrack maintains high code quality standards:

- ✅ **100% Test Success Rate** (133/133 tests passing)
- ✅ **~100% Code Coverage** (JaCoCo analysis)
- ✅ **Zero Compilation Errors**
- ✅ **Zero Critical Vulnerabilities**
- ✅ **Clean Code Principles** (DDD, SOLID)

See [QUALITY_GATE_REPORT.md](QUALITY_GATE_REPORT.md) for detailed quality metrics.

---

## 🛠️ Technology Stack

### Core Technologies
- **Java 21 (LTS)** - Latest long-term support Java version
- **Maven 3.9.11** - Build automation and dependency management
- **JUnit 5.10.1** - Unit testing framework
- **JaCoCo 0.8.11** - Code coverage analysis

### Design Patterns
- **Domain-Driven Design** - Pure domain entities without framework coupling
- **Multi-tenant Architecture** - Data isolation via `entrepriseId`
- **Value Objects** - `BigDecimal` for precision, `UUID` for identifiers
- **Repository Pattern** (Future) - For data persistence abstraction

### Code Quality
- **UTF-8 Encoding** - International character support
- **Null-Safety** - Defensive programming with null checks
- **Input Validation** - Email regex, required field validation
- **Test-Driven Development** - Comprehensive test coverage

---

## 📂 Project Structure

```
ecotrack/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ecotrack/
│   │   │       ├── Main.java                    # Demo application
│   │   │       └── domain/
│   │   │           ├── Entreprise.java          # Root aggregate
│   │   │           ├── Employe.java             # Employee entity
│   │   │           ├── ActifIT.java             # IT asset entity
│   │   │           ├── CategorieActif.java      # Asset category
│   │   │           └── ConsoJournaliere.java    # Daily consumption
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/ecotrack/domain/
│               ├── EmployeTest.java              # 45 tests
│               ├── EntrepriseTest.java           # 25 tests
│               ├── ActifITTest.java              # 23 tests
│               ├── CategorieActifTest.java       # 17 tests
│               └── ConsoJournaliereTest.java     # 23 tests
├── target/
│   ├── classes/                                  # Compiled bytecode
│   ├── test-classes/                             # Test bytecode
│   └── site/jacoco/                              # Coverage reports
├── pom.xml                                       # Maven configuration
├── README.md                                     # This file
├── QUALITY_GATE_REPORT.md                        # Quality metrics
└── quality-gate-report.json                      # Machine-readable report
```

---

## 🗺️ Roadmap

### Phase 1: Foundation ✅ (COMPLETE)
- [x] Java 21 migration
- [x] Domain entity modeling
- [x] Comprehensive unit testing
- [x] Quality Gate establishment

### Phase 2: Persistence (Next)
- [ ] JPA/Hibernate integration
- [ ] PostgreSQL database setup
- [ ] Repository implementations
- [ ] Liquibase migrations
- [ ] Integration tests

### Phase 3: Application Services
- [ ] Use case implementations
- [ ] Business workflow orchestration
- [ ] Transaction management
- [ ] Error handling & logging

### Phase 4: API Layer
- [ ] Spring Boot REST API
- [ ] OpenAPI/Swagger documentation
- [ ] API versioning strategy
- [ ] Request/response DTOs

### Phase 5: Security
- [ ] Authentication (OAuth2/JWT)
- [ ] Authorization (role-based)
- [ ] Multi-tenant security
- [ ] API rate limiting

### Phase 6: Analytics & Reporting
- [ ] Carbon footprint calculation
- [ ] Dashboard & visualizations
- [ ] PDF/Excel report generation
- [ ] Real-time monitoring

### Phase 7: Production
- [ ] Docker containerization
- [ ] Kubernetes orchestration
- [ ] CI/CD pipeline (GitHub Actions)
- [ ] Monitoring & observability

---

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/amazing-feature`)
3. **Commit your changes** (`git commit -m 'feat: add amazing feature'`)
4. **Push to the branch** (`git push origin feature/amazing-feature`)
5. **Open a Pull Request**

### Commit Convention

We follow [Conventional Commits](https://www.conventionalcommits.org/):

- `feat:` - New feature
- `fix:` - Bug fix
- `docs:` - Documentation changes
- `test:` - Adding or updating tests
- `refactor:` - Code refactoring
- `chore:` - Maintenance tasks

### Code Quality Standards

- ✅ All tests must pass (`mvn test`)
- ✅ Maintain >90% code coverage
- ✅ Follow DDD principles (no framework coupling in domain)
- ✅ Add comprehensive tests for new features
- ✅ Update documentation

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Authors

**Houssem Eddine Lassoued**
- GitHub: [@houssemeddinelassoued](https://github.com/houssemeddinelassoued)

---

## 🙏 Acknowledgments

- Inspired by Green IT initiatives and carbon neutrality goals
- Built with Domain-Driven Design principles from Eric Evans
- Leverages modern Java 21 LTS features for performance and maintainability

---

## 📞 Contact & Support

- **Issues:** [GitHub Issues](https://github.com/houssemeddinelassoued/ecotrack/issues)
- **Discussions:** [GitHub Discussions](https://github.com/houssemeddinelassoued/ecotrack/discussions)
- **Email:** contact@ecotrack.io

---

<div align="center">

**⭐ Star this repository if you find it useful!**

Made with ❤️ for a greener IT future 🌍

</div>
