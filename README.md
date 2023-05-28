<div align="center">
 <img src="https://user-images.githubusercontent.com/111321384/225424307-c1475755-8810-4fd3-aa1f-64c7f67c6f65.png" />
 </div>

### Disciplina: Projeto e Arquitetura de Software
### Professor: [Glauco Scheffel](https://www.linkedin.com/in/glaucoscheffel/)
### Aluno: [Willian Mikhael da Cunha](https://www.linkedin.com/in/willianmikhael/)

#### 27/05/2023

#

## Para Entender um Pouco Sobre Este Projeto

### Sintese

Neste projeto construi uma API que retorna o calculo do **Fatorial e SuperFatorial** de um número.
No entanto o principal objetivo aqui foi a Utilização do Redis para armazenamento em cache.
Utilizei o framework **SpringBoot** e escrevi o código em **Java**, além disso utilizei o padrão de arquitetura de software **MVC** e utilizei o **Docker** para rodar um server do **Redis**.

### O que é MVC ?

O MVC (Model-View-Controller) é um padrão de arquitetura de software amplamente adotado no desenvolvimento de aplicativos web. Ele separa as responsabilidades do aplicativo em três componentes principais: a Model, responsável pela lógica de negócios e gerenciamento de dados; a View, responsável pela apresentação da interface do usuário; e o Controller, responsável por gerenciar as interações do usuário e coordenar a comunicação entre a Model e a View. Essa separação ajuda a promover a modularidade, reutilização de código e facilita a manutenção do aplicativo, tornando-o mais organizado e escalável.

### O que é SpringBoot ?

O Spring Boot é um framework Java que simplifica o desenvolvimento de aplicativos robustos e prontos para produção, oferecendo configuração automática, servidor embutido e integração com o ecossistema Spring. Ele permite criar aplicativos independentes e facilita o gerenciamento de dependências, além de fornecer recursos avançados para monitoramento e métricas.

### Porque utilizar Redis ?

O Redis é um banco de dados em memória conhecido por sua alta velocidade, baixa latência e simplicidade de uso. Ele oferece recursos avançados, escalabilidade, persistência de dados e é frequentemente utilizado como cache em memória. A escolha do Redis depende dos requisitos do projeto e das necessidades de desempenho.

### 

<div align="center">
 <img src="https://github.com/willianmikhael/API_Fatorial_CatolicaSC/assets/111321384/1a8a3f44-c2b0-4bb5-9262-4a4094fbca76.png" />
 </div>
 
 ## Instalação

Necessario ter o [Docker](https://www.docker.com/) instalado em seu computador.
Após isso, abra um terminal e  rode o seguinte comando:
 
 ```powershell
 Docker run -it --name redis -p 6379:6379 redis:5.0.3
 ```
 
 #### Atente-se para o arquivo *applications.properties*
 É nele que estão configurados a porta HTTP que sera acessada via **Tomcat** e a conexão com o **Redis**
 
 ```powershell
 server.port=5000

spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

