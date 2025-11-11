# ConecteCare - Sistema de Gestão de Saúde

<div align="center">

![Java](https://img.shields.io/badge/Java-17+-blue) ![Quarkus](https://img.shields.io/badge/Quarkus-3.0+-red) ![Oracle](https://img.shields.io/badge/Oracle%20SQL-19c+-red) ![License](https://img.shields.io/badge/License-MIT-green)

---
📋 Sobre o Projeto
O ConecteCare é um sistema completo de gestão de saúde desenvolvido em Java com Quarkus, projetado para conectar pacientes, cuidadores e médicos de forma eficiente e organizada.
---
🎯 Objetivos
Facilitar o cadastro e gerenciamento de pacientes e suas patologias

Vincular cuidadores a pacientes específicos

Agendar e gerenciar consultas médicas

Conectar pacientes com médicos especializados baseado em patologias
---

# 🏗️ Arquitetura do Sistema
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   Frontend      │ →  │   Backend        │ →  │   Banco de      │
│   (Web/Mobile)  │    │   (Quarkus)      │    │   Dados         │
└─────────────────┘    └──────────────────┘    └─────────────────┘
---
Camadas da Aplicação

Controller: Endpoints REST API

Service: Lógica de negócio

Repository: Acesso a dados (Panache)

Entity: Modelo de domínio

DTO: Objetos de transferência de dados

---

</div>

---

## 🚀 Tecnologias Utilizadas

### 🔧 Backend
- **Java 17+** - Linguagem de programação
- **Quarkus 3.0+** - Framework para aplicações cloud-native
- **Hibernate Panache** - ORM simplificado
- **JAX-RS** - API REST
- **CDI** - Injeção de dependência

### 🗃️ Banco de Dados
- **Oracle SQL** - Banco de dados relacional empresarial
- **JPA/Hibernate** - Mapeamento objeto-relacional
- **Sequences** - Para geração de IDs automáticos
- **Discriminator Column** - Herança com tabela única

### 🛠️ Ferramentas de Desenvolvimento
- **Maven** - Gerenciamento de dependências
- **SQL Developer** - Gerenciamento do Oracle
- **Git** - Controle de versão

---

## 📁 Estrutura do Projeto
conectecare/
├── src/

│ └── main/

│ └── java/

│ └── conectecare/

│ ├── controller/ # 🎯 Endpoints REST

│ ├── service/ # ⚙️ Lógica de negócio

│ ├── repository/ # 💾 Acesso a dados

│ ├── model/

│ │ ├── entity/ # 🗂️ Entidades JPA

│ │ └── dto/ # 📤 Data Transfer Objects

│ └── conexao/ # 🔌 Configurações

├── src/main/resources/

│ ├── application.properties # ⚙️ Configurações

│ └── META-INF/resources/ # 📁 Recursos estáticos

└── README.md

---
### 👥 Módulo Paciente
- ✅ **Cadastro completo** com dados pessoais e patologia
- ✅ **Vínculo automático** com especialistas baseado na patologia
- ✅ **Gestão completa** (CRUD) de pacientes
- ✅ **Busca por CPF e email**

### 🛡️ Módulo Cuidador
- ✅ **Cadastro flexível** com vínculo direto ao paciente
- ✅ **Validação de vínculo** (um cuidador por paciente)
- ✅ **Gestão completa** (CRUD) de cuidadores

### 🩺 Módulo Médico
- ✅ **Cadastro especializado** com CRM e especialidade
- ✅ **Associação com patologias** tratadas
- ✅ **Busca por especialidade e patologia**

### 📅 Módulo Consultas
- ✅ **Agendamento inteligente** vinculando paciente e médico
- ✅ **Status automático** (AGENDADA, REALIZADA, CANCELADA)
- ✅ **Listagem por paciente e médico**
- ✅ **Gestão de atualizações e cancelamentos**

### 🔐 Módulo Autenticação
- ✅ **Login unificado** para pacientes e cuidadores
- ✅ **Validação de credenciais**
- ✅ **Sessão de usuário**
---
🗃️ Modelo de Dados
Entidades Principais

Pessoa (classe abstrata) - Dados base para todos os usuários

Paciente - Especialização com patologia e cuidador

Cuidador - Especialização com vínculo a paciente

Medico - Especialização com CRM e especialidade

Consulta - Agendamentos entre pacientes e médicos

Patologia - Catálogo de condições médicas

Especialidade - Catálogo de especialidades médicas
---
🚀 Como Executar

Pré-requisitos

Java 17 ou superior

Maven 3.8+

Oracle SQL

Quarkus CLI (opcional)
---
Configuração do Banco de Dados

Crie o banco de dados:
CREATE DATABASE conectecare;

quarkus.datasource.db-kind=oracle

quarkus.datasource.username=seu_usuario

quarkus.datasource.password=sua_senha

quarkus.datasource.jdbc.url=jdbc:oracle://localhost:5432/conectecare

Execução da Aplicação
Clone o repositório:

git clone https://github.com/seu-usuario/conectecare.git

cd conectecare
---
📚 API Endpoints

Pacientes
Método	Endpoint	Descrição

GET	/pacientes	Lista todos os pacientes

POST	/pacientes	Cria um novo paciente

PUT	/pacientes/{cpf}	Atualiza paciente por CPF

DELETE	/pacientes/{cpf}	Exclui paciente por CPf

Cuidadores

Método	Endpoint	Descrição

GET	/cuidadores	Lista todos os cuidadores

POST	/cuidadores	Cria um novo cuidador

PUT	/cuidadores/{cpf}	Atualiza cuidador por CPF

DELETE	/cuidadores/{cpf}	Exclui cuidador por CPF

Consultas

Método	Endpoint	Descrição

GET	/consultas/paciente/{id}	Consultas por paciente

POST	/consultas	Agenda nova consulta

Autenticação

Método	Endpoint	Descrição

POST	/login	Login no sistema
---
🤝 Contribuição

Fork o projeto

Crie uma branch para sua feature (git checkout -b feature/AmazingFeature)

Commit suas mudanças (git commit -m 'Add some AmazingFeature')

Push para a branch (git push origin feature/AmazingFeature)

Abra um Pull Request
---
👥 Autores
Seu Nome - Scarpin12

🙏 Agradecimentos
Equipe de desenvolvimento

Comunidade Quarkus

Todos os contribuidores












