
# 🧭 Documento de Especificação — Sistema **“Meu Dia”**

## 📘 1. Visão Geral

**Nome do sistema:** Meu Dia  
**Tipo:** Aplicação web (Website)  
**Objetivo:** Permitir que usuários anônimos compartilhem como foi o seu dia, selecionando um humor e escrevendo um texto curto.  
**Público-alvo:** Qualquer pessoa que deseje expressar sentimentos ou desabafar anonimamente.  

O sistema se diferencia por permitir **postagens anônimas**, **sem necessidade de login**, e **interações limitadas** (curtidas apenas para usuários cadastrados).

---

## 💡 2. Funcionalidades Principais

### 🧍‍♂️ Usuário Visitante (Anônimo)
- Pode acessar o site sem login.
- Pode **visualizar todas as postagens** públicas.
- Pode **criar uma postagem anônima**, informando:
  - Humor do dia (ex: feliz, triste, cansado, ansioso etc.)
  - Texto da postagem (descrição do dia)
- Não pode curtir postagens.
- Não pode comentar.

### 🔐 Usuário Cadastrado
- Pode realizar **login** e **logout**.
- Pode **visualizar postagens** (como visitante).
- Pode **curtir** postagens (apenas uma vez por postagem).
- Não pode comentar.
- Pode **editar informações da conta** (opcional futuro).

---

## 🧱 3. Requisitos Funcionais (RF)

| Código | Requisito | Descrição |
|--------|------------|-----------|
| RF01 | Cadastro de Usuário | O sistema deve permitir o cadastro com nome, e-mail e senha. |
| RF02 | Login | O sistema deve autenticar o usuário para permitir curtidas. |
| RF03 | Postagem Anônima | O sistema deve permitir criar uma postagem sem exigir login. |
| RF04 | Seleção de Humor | Antes de postar, o usuário deve escolher o humor correspondente. |
| RF05 | Listagem de Postagens | O sistema deve exibir todas as postagens ordenadas por data (mais recente primeiro). |
| RF06 | Curtidas | Somente usuários logados podem curtir postagens. |
| RF07 | Visualização Pública | Todas as postagens devem ser visíveis a qualquer visitante. |
| RF08 | Sem Comentários | O sistema não deve permitir comentários nas postagens. |

---

## ⚙️ 4. Requisitos Não Funcionais (RNF)

| Código | Requisito | Descrição |
|--------|------------|-----------|
| RNF01 | Linguagem | O sistema deve ser desenvolvido em HTML, CSS, JavaScript e backend em Java (Spring Boot) ou PHP. |
| RNF02 | Banco de Dados | O banco pode ser MySQL, PostgreSQL ou SQLite. |
| RNF03 | Desempenho | O site deve carregar em menos de 3 segundos em rede comum. |
| RNF04 | Segurança | As senhas devem ser armazenadas de forma criptografada (bcrypt). |
| RNF05 | Privacidade | Postagens anônimas não devem registrar IP público visível. |
| RNF06 | Acessibilidade | Interface deve ser simples, legível e adaptável a mobile. |

---

## 🗃️ 5. Estrutura de Dados (Modelo Conceitual Simplificado)

### Tabela: `postagens`
| Campo | Tipo | Descrição |
|--------|------|-----------|
| id | INT | Identificador único |
| humor | VARCHAR(30) | Humor selecionado |
| texto | TEXT | Texto da postagem |
| data_postagem | DATETIME | Data/hora da criação |
| curtidas | INT | Contador de curtidas |

### Tabela: `usuarios`
| Campo | Tipo | Descrição |
|--------|------|-----------|
| id | INT | Identificador único |
| nome | VARCHAR(100) | Nome do usuário |
| email | VARCHAR(100) | E-mail |
| senha | VARCHAR(255) | Senha criptografada |

### Tabela: `curtidas`
| Campo | Tipo | Descrição |
|--------|------|-----------|
| id | INT | Identificador único |
| usuario_id | INT | ID do usuário que curtiu |
| postagem_id | INT | ID da postagem curtida |

---

## 🔄 6. Fluxo do Sistema (Descrição)

1. **Visitante acessa o site**  
   → Página inicial exibe postagens mais recentes com humor e texto.

2. **Visitante deseja postar**  
   → Clica em “Compartilhe seu dia”.  
   → Seleciona um humor.  
   → Digita o texto.  
   → Envia.  
   → Sistema salva a postagem como “anônima”.

3. **Visitante deseja curtir uma postagem**  
   → Ao clicar em “Curtir”, sistema pede login.  
   → Caso já logado, incrementa curtidas.  
   → Caso não, redireciona para tela de login.

4. **Usuário cadastrado faz login**  
   → Informa e-mail e senha.  
   → Acessa novamente a lista de postagens.  
   → Agora pode curtir qualquer postagem.

5. **Usuário curte postagem**  
   → O sistema registra a curtida e impede curtir duas vezes.

---

## 🧭 7. Fluxograma Textual

```
[Início]
   ↓
[Usuário acessa site]
   ↓
[Ver postagens existentes]
   ↓
 ┌──────────────────────────────────────┐
 │ Deseja postar como foi o dia?        │
 └──────────────────────────────────────┘
           ↓ Sim                      ↓ Não
  [Seleciona humor e digita texto]     [Continua navegando]
           ↓
  [Envia postagem]
           ↓
  [Sistema salva postagem anônima]
           ↓
[Postagem aparece na lista pública]
           ↓
 ┌──────────────────────────────────────┐
 │ Deseja curtir uma postagem?          │
 └──────────────────────────────────────┘
           ↓ Sim                      ↓ Não
     [Está logado?]                   [Encerrar]
       ↓ Sim        ↓ Não
 [Registrar curtida]   [Redirecionar para login]
       ↓
 [Salvar curtida no banco]
       ↓
 [Atualizar contador de curtidas]
       ↓
 [Fim]
```

---

## 🧩 8. Interface Esperada (prototipagem conceitual)

**Página Inicial:**
- Cabeçalho com o nome “Meu Dia 😊”.
- Botão “Compartilhar meu dia”.
- Lista de postagens em cartões, com:
  - Emoji de humor
  - Texto
  - Data
  - Botão “Curtir” + contador

**Tela de Postagem:**
- Selecionar humor (menu suspenso ou ícones)
- Caixa de texto
- Botão “Publicar”

**Tela de Login/Cadastro:**
- Campos simples (email, senha)
- Botão “Entrar” ou “Cadastrar”

---

## 🛠️ 9. Considerações Técnicas

- Backend: Java Spring Boot / PHP (Laravel ou puro)
- Frontend: HTML5, CSS3, JavaScript (ou framework leve como Vue.js)
- Banco: MySQL
- API endpoints sugeridos:
  - `GET /postagens` → listar postagens
  - `POST /postagens` → criar nova postagem anônima
  - `POST /usuarios` → criar usuário
  - `POST /login` → autenticar
  - `POST /curtidas/{idPostagem}` → curtir postagem

---

## 📄 10. Possíveis Extensões Futuras

- Filtros de humor (mostrar apenas postagens felizes, tristes, etc.)
- Página “Top do Dia” (mais curtidas)
- Dark Mode
- Exportar humor do mês (estatísticas pessoais)
