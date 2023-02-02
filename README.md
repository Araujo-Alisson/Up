<h1 align="center">Up Tasks</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0 -blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat" border="0" alt="API"></a>
  <br>
  <a href="https://wa.me/+5511931671899"><img alt="WhatsApp" src="https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo =whatsapp&logoColor=white"/></a>
  <a href="https://www.linkedin.com/in/kaiqueocanha/"><img alt="Linkedin" src="https://img.shields.io/badge/LinkedIn-0077B5?style=for -the-badge&logo=linkedin&logoColor=white"/></a>
  <a href="mailto:aliAraujo197@gmail.com"><img alt="Gmail" src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor= branco"/></a>
</p>

<p align="center">  

⭐ Esse é um projeto para demonstrar meu conhecimento técnico no desenvolvimento Android nativo com Kotlin. Mais informações técnicas abaixo.

Aplicativo de listagem de tarefas criada pelo usuario com uma elegante RecyclerView adaptativa que se refaz, interagindo com o click no dia do calendario
  em que o usuario escolher ver. funcionalidades: criar novas tarefas, editar tarefas criada, etiqueta de prioridade, opção de definir alarme para a tarefa.

</p>

</br>

<p float="left" align="center">
<img alt="screenshot" width="30%" src="https://user-images.githubusercontent.com/124219960/216360794-1f49c9f7-01a7-45c6-af3c-467f3c1bb7f0.png"/>
  <img alt="screenshot" width="30%" src="https://user-images.githubusercontent.com/124219960/216361157-08f69477-592d-4d77-a555-c651a345b8a7.png"/>
  <img alt="screenshot" width="30%" src="https://user-images.githubusercontent.com/124219960/216361318-582797df-8aef-4c73-88f5-f07b4c8f951e.png"/>
  <img alt="screenshot" width="30%" src="https://user-images.githubusercontent.com/124219960/216361483-5b2da61d-337b-4e3a-87db-41c1b2494045.png"/>
</p>

## Download
<a href='https://play.google.com/store/apps/details?id=com.agenda.up&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img width="20%" alt='Disponível no Google Play' src='https://play.google.com/intl/en_us/badges/static/images/badges/pt-br_badge_web_generic.png'/></a>

Ou faça o download da <a href="apk/app-debug.apk?raw=true">APK diretamente</a>. Você pode ver <a href="https://www.google.com/search?q=como+instalar+um+apk+no+android">aqui</a> como instalar uma APK no seu aparelho android.

## Tecnologias usadas e bibliotecas de código aberto

- Nível mínimo do SDK: 21
- [Linguagem Kotlin](https://kotlinlang.org/)

- Jetpack 
  - Lifecycle: Observe os ciclos de vida do Android e manipule os estados da interface do usuário após as alterações do ciclo de vida.
  - ViewModel: Gerencia o detentor de dados relacionados à interface do usuário e o ciclo de vida. Permite que os dados sobrevivam a alterações de configuração, como rotações de tela.
  - ViewBinding: Liga os componentes do XML no Kotlin através de uma classe que garante segurança de tipo e outras vantagens.
  - Room: Biblioteca de abstração do banco de dados SQLite que garante segurança em tempo de construção e facilidade de uso.
  - Custom Views: View customizadas feitas do zero usando XML.

- Arquitetura 
  - MVVM (ModelView - View - Model)
  - Comunicação da ViewModel com a View através de LiveData
  - Repositories para abstração da comunicação com a camada de dados.
  
- Bibliotecas 
  - [Lottie](https://github.com/LottieFiles/awesome-lottie): Para implementar animações no projeto.
  - fragment: Para criar 1 ou mais ambientes na mesma activity.
  - [firebase-auth](https://github.com/firebase/quickstart-android): sistema de autenticação seguro, além de melhorar a experiência de login e integração para os usuários finais.
  

## Arquitetura

**Up Tasks** utiliza a arquitetura MVVM e o padrão de Repositories, que segue as [recomendações oficiais do Google](https://developer.android.com/topic/architecture).
</br></br>
<img width="60%" src="https://user-images.githubusercontent.com/124219960/216390520-a116e913-07e5-41aa-9593-2e04b5fef07d.png"/>
<br>

## API de terceiros

COLOQUE O NOME, LINK E DESCRIÇÃO DAS APIS UTILIZADAS NO PROJETO

## Recursos

### Recurso 1
<img src="https://user-images.githubusercontent.com/124219960/216401341-f1bcca3e-1546-4d32-a39d-f8ea4660d9a3.gif" width="25%"/>

Criando uma nova tarefa com o opcional de adicionar uma descriçao.

### Recurso 2
<img src="https://user-images.githubusercontent.com/124219960/216405216-b4e5301c-16f1-416e-b5f6-b121e1a6fe8e.gif" width="25%"/>

Criando uma tarefa com opção de lembrete.

# Licença

COLOQUE A LICENÇA - https://opensource.org/licenses

```xml
   Direitos autorais [2023] [Alisson Pereira de Araujo Souza]

   Licenciado sob a Licença Apache, Versão 2.0 (a "Licença");
   você não pode usar este arquivo, exceto em conformidade com a Licença.
   Você pode obter uma cópia da Licença em

     http://www.apache.org/licenses/LICENSE-2.0

   A menos que exigido pela lei aplicável ou acordado por escrito, o software
   distribuído sob a Licença é distribuído "COMO ESTÁ",
   SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, expressas ou implícitas.
   Consulte a Licença para obter as permissões de controle do idioma específico e
   limitações sob a Licença.
```
