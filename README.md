# ForoHub

ForoHub es una plataforma de foros en línea diseñada para facilitar la discusión y el intercambio de conocimientos entre usuarios. El proyecto está implementado utilizando Spring Boot, Spring Security y una base de datos PostgreSQL. A continuación, se describen los principales componentes y funcionalidades del proyecto:


## Tech Stack

**Backend**: Spring Boot

**Base de Datos**: PostgreSQL.

**Autenticación**: JWT (JSON Web Tokens)

**ORM**: Hibernate (JPA)

**Controladores REST**: Implementados para gestionar usuarios, tópicos, perfiles y cursos.


## API Referencias

### Usuarios
#### Obtener

```http
  GET /usuarios
```

#### Registrar
```http
  POST /usuarios
```
Cuerpo de la solicitud:
| Propiedad | Tipo     | Descripción
| :-------- | :------- | :-------------------------------- |
| `nombre`      | `String` | **Requerido**. Nombre del usuario |
| `email`      | `String` | **Requerido**. Correo electrónico |
| `password`      | `String` | **Requerido**. Contraseña |
| `perfiles`      | `List<Long>` | **Requerido**. Lista de Ids de los perfiles |

### Cursos
#### Obtener

```http
  GET /cursos
```

#### Registrar
```http
  POST /cursos
```
Cuerpo de la solicitud:
| Propiedad | Tipo     | Descripción
| :-------- | :------- | :-------------------------------- |
| `nombre`      | `String` | **Requerido**. Nombre del curso |
| `categoria`      | `String` | **Requerido**. Clasificación del curso |

### Perfiles
#### Obtener

```http
  GET /perfiles
```

#### Registrar
```http
  POST /perfiles
```
Cuerpo de la solicitud:
| Propiedad | Tipo     | Descripción
| :-------- | :------- | :-------------------------------- |
| `nombre`      | `String` | **Requerido**. Nombre del perfil |

### Topicos
#### Obtener

```http
  GET /topicos
```
#### Detalle

```http
  GET /topicos/{id}
```

#### Registrar
```http
  POST /topicos
```
Cuerpo de la solicitud:
| Propiedad | Tipo     | Descripción
| :-------- | :------- | :-------------------------------- |
| `titulo`      | `String` | **Requerido**. Nombre del topico |
| `mensaje`      | `String` | **Requerido**. Clasificación del curso |
| `idAutor`      | `Long` | **Requerido**. id del usuario que genera el topico |
| `nombreCurso`      | `String` | **Requerido**. Nombre del curso al cual se destina el topico |

#### Actualizar
```http
  PUT /topicos/{id}
```
Cuerpo de la solicitud:
| Propiedad | Tipo     | Descripción
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Requerido**. Id del topico a actualizar |
| `titulo`      | `String` | **Requerido**. Nombre del topico |
| `mensaje`      | `String` | **Requerido**. Clasificación del curso |
| `idAutor`      | `Long` | **Requerido**. id del usuario que genera el topico |
| `nombreCurso`      | `String` | **Requerido**. Nombre del curso al cual se destina el topico |

#### Eliminar

```http
  DELETE /topicos/{id}
```
