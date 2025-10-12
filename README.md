# 🏥 ConectaCare - Sistema de Gestão de Saúde

## 📋 Sobre o Projeto

O ConectaCare é um sistema completo de gestão para clínicas e hospitais, desenvolvido em Java seguindo os princípios do Domain Driven Design (DDD). O sistema permite o gerenciamento integrado de pacientes, cuidadores e profissionais de saúde, com agendamento automático de consultas baseado em patologias.

## 🎯 Objetivo

Fornecer uma solução robusta para o acompanhamento clínico integrado, facilitando o vínculo entre pacientes, cuidadores e especialistas médicos através de um sistema automatizado e eficiente.

## ⚡ Funcionalidades

### 👥 Gestão de Pacientes

- Cadastro completo com dados pessoais e patologia
- Vinculação automática com especialistas adequados
- Agendamento automático de consulta pós-cadastro
- Operações CRUD completas (listar, atualizar, excluir)

### 👨‍⚕️ Gestão de Cuidadores

- Cadastro avulso ou vinculado a paciente
- Gestão de vínculos familiares/profissionais
- Busca por CPF do paciente associado
- Controle de múltiplos pacientes por cuidador

### 🏥 Sistema de Consultas

- Agendamento automático baseado na patologia
- Links de telemedicina integrados
- Status de consulta (agendada, realizada, cancelada)
- Listagem inteligente de próximas consultas

## 🏗️ Arquitetura

challengTDSPX/
├── src/
│ ├── controller/ # Controladores
│ ├── model/
│ │ ├── bo/ # Business Objects
│ │ ├── dao/ # Data Access Objects
│ │ └── vo/ # Value Objects
│ └── view/ # Interface do usuário

## 💻 Tecnologias

- Java JDK 21+
- Oracle Database
- JDBC (ojdbc17.jar)
- Padrão MVC com DDD

## 🚀 Como Executar

### Pré-requisitos

- Java JDK 21 ou superior
- Oracle Database
- Driver ojdbc17.jar

## 📊 Estrutura de Classes Principais

### Camada Model (VO)

- `Pessoa` (classe abstrata)
- `Paciente`, `Cuidador`, `Medico` (herdam de Pessoa)
- `Patologia`, `Consulta`, `Especialidades`

### Camada DAO

- `Conexao` (gerencia conexões com o banco)
- `PacienteDAO`, `CuidadorDAO`, `ConsultaDAO`, `PatologiaDAO`

### Camada BO

- `PacienteBO`, `CuidadorBO`, `ConsultaBO` (lógica de negócio)

### Camada Controller

- `PacienteController`, `CuidadorController`, `ConsultaController`

### Camada View

- `MenuPrincipal`, `MenuPaciente`, `MenuCuidador`

## 🎯 Características Técnicas

- Transações ACID com commit/rollback
- Tratamento de erros robusto
- Validações de integridade de dados
- Arquitetura em camadas bem definidas
- Padrões de projeto MVC e DAO

