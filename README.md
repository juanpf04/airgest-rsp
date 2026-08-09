# AirGestRSP - Sistema de Gestión Aeroportuaria

Aplicación de escritorio para la gestión aeroportuaria con funcionalidades CRUD, desarrollada como proyecto académico en la Universidad Complutense de Madrid (UCM) durante las asignaturas de Ingeniería del Software I, Ingeniería del Software II y Modelado Software.

El trabajo abarca todas las fases del ciclo de vida del software:
- Recogida y especificación de requisitos (SRS).
- Análisis y modelado UML (casos de uso, modelo de dominio, diagramas de clases, de secuencia y de despliegue).
- Diseño de la arquitectura y gestión de riesgos.

La implementación se realiza en **Java**, inicialmente con patrón DAO y posteriormente migrando a **JPA**, sobre una base de datos **MySQL**. Se aplican patrones de diseño, capas de persistencia y mecanismos de gestión de concurrencia mediante bloqueos. 

El proyecto incluye documentación técnica y de usuario, así como un plan de aseguramiento de la calidad (SQA) que contempla la planificación de pruebas, pruebas unitarias, de integración y de sistema, implementadas con **JUnit**. Todo el modelado fue elaborado con **IBM Rational Software Architect Designer**.

## Estructura del Monorepo

Inicialmente, este proyecto se desarrolló en dos repositorios distintos: uno para el modelado UML y otro para el código de la aplicación. 

Para facilitar su conservación, ambos repositorios han sido unificados en este único **monorepo**. Para lograrlo conservando el historial de commits original (y la autoría de los mismos), se ha empleado la herramienta `git`: 

```bash
# 1. Añadir los repositorios originales como remotos
git remote add repo <url_del_repositorio> 
# 2. Traer la información de los repositorios remotos
git fetch repo
# 3. Utilizar "subtree" para importar el historial de los repositorios remotos dentro de "folder/"
git subtree add --prefix=folder repo/main
```

La estructura resultante es la siguiente:

```text
airgest-rsp/
├── docs/           # Documentación del proyecto.
├── model/          # Archivos del modelo UML y requisitos.
├── src/            # Código de la aplicación Java.
├── .gitattributes
├── .gitignore
├── LICENSE         # Declaración de licencia MIT.
└── README.md       # Documentación principal
```
