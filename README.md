
# 📚 Catálogo de Livros - API Gutendex

Este é um projeto em Java que implementa um catálogo de livros utilizando a API pública [Gutendex](https://gutendex.com/), com persistência em banco de dados PostgreSQL e interação via console.

## ✅ Funcionalidades Implementadas

- 🔍 Buscar livro por título (via API Gutendex)
- 📖 Listar todos os livros buscados durante a execução
- 🌐 Filtrar livros por idioma
- 🧑‍💼 Listar autores dos livros buscados
- 🕰️ Listar autores vivos em determinado ano
- 💾 Salvar livro e autor no banco de dados
- 🗃️ Listar livros salvos no banco
- 📊 Exibir quantidade de livros salvos por idioma
- 📟 Interface textual com menu no console

## 🧰 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Jackson (para JSON)**
- **HttpClient (para requisições HTTP)**
- **Maven**

## 🗃️ Estrutura do Projeto

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br.com
│   │   │       ├── entidade          # Entidades JPA (LivroEntity, AutorEntity)
│   │   │       ├── modelo            # Modelos mapeados da API (Livro, Autor)
│   │   │       ├── repositorio       # Interfaces JPA (LivroRepository, AutoRepository)
│   │   │       ├── servico           # Serviço principal (ConverteLivroExibi)
│   │   │       ├── literaturabookapi # Cliente da API Gutendex (LivroClient)
│   │   │       └── bancodelivros     # Classe principal (Principal.java)
│   │   └── resources
│   │       └── application.properties
├── pom.xml
└── README.md
```

## 🧪 Executando o Projeto

1. **Clone o projeto**:

```bash
git clone https://github.com/seu-usuario/catalogo-livros-gutendex.git
cd catalogo-livros-gutendex
```

2. **Configure o banco de dados PostgreSQL** (crie um banco e atualize o `application.properties`):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. **Rode a aplicação:**

Via terminal ou no IntelliJ:
```bash
mvn spring-boot:run
```

4. **Use o menu no console para interagir com o sistema.**

## 📸 Exemplo de Execução

```
========= Catálogo de Livros =========

1 - Buscar Livro por Título
2 - Listar Livros Buscados
3 - Filtrar por Idioma
4 - Listar Autores
5 - Listar Autores Vivos em um Ano
6 - Listar Livros Salvos no Banco
0 - Sair
Escolha uma opção:
```

## 💡 Possíveis Melhorias Futuras

- Interface gráfica (Swing, JavaFX ou Web)
- Caching de requisições
- Histórico de buscas persistente
- Testes unitários com JUnit
- Suporte para múltiplos autores por livro

## 📄 Licença

Projeto com fins educacionais. Livre para uso e modificação.

---

Desenvolvido por **Aldo da Silveira**
