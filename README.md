# PopKornStudio

## About / Sobre

:us:
PopkornStudio is a quiz game about movies, on this android app the player will challenge it's own knowledge in a lot of differents movie genres in order to improve it's ranking position.


🇧🇷
PopKornStudio é um game quiz sobre filmes, nesse app android o usuário disafiará seus próprios conhecimentos sobre os mais diversos filmes para poder se destacar no ranking.


**Download:** [Playstore](https://play.google.com/store/apps/details?id=com.studio.sevenapp.android.popkornstudio)

## Technologies / Tecnologias

:us:
**Architecture** 
 - MVVM
 - Modules
    - presentation: responsible to manage all the views in the app(activities and fragments);
    - domain: responsible to manage the business, in other words, in this module I apply algoritms to transform list of movies in list of questions and answers and also manage all the logic about points, ranking and etc;
    - data: responsible to get all the information, in short, it's de module that manage (get, save, update) data from backend and/or local database;
    - firebase: as the name says, is the module responsible to use the services from firebase authentication, firestore and remoteconfig;

🇧🇷
**Arquitetura**
 - MVVM
 - Modularização
    - presentation: responsável por gerenciar todas as views no app (acivity e fragments);
    - domain: responsável por gerenciar as regras de negócio, ou seja, é nesse módulo que estão os algoritmos que transformam a lista de filme em lista de perguntas e responstas, nele também está a lógica dos pontos, ranking e etc;
    - data: responsável por pegar todas as informações, ou seja, nele que será feito as requests e também gerenciado o banco de dados;
    - firebase: como o próprio nome diz, esse módulo é responsável por consumir os serviços do firebase authentication, firestore and remoteconfig;


**Others technologies / Outras tecnologias**
 - Requests to API: Retrofit 2;
 - Thread: Coroutine;
 - Koin: dependency-injection / injeção de dependencia;
 - Local Database: Room;
 - Remote Database: Firebase Firestore;
 - Authentication: Firebase authentication UI;

**PLUS TECHNOLOGIES**

:us:
- **Firebase Functions:** Used as a "Trigger" on firebase authentication in order to create a user doc on firestore whenever a new user sign up;
- **Firebase Remote Config:** Therefore this tools is used as a feature flag, in this case I used just to show up messages on home screen;
- **Firebase Admob:** Used to show up ads when user gets on result challenge screen in order to monetize the app;

🇧🇷
- **Firebase Functions:** Usado como uma Trigger no firebase authentication para criar um novo doc de usuário no firestore sempre que um novo usuário se cadastrar;
- **Firebase Remote Config:** Apesar de ser usado como feature flag nesse projeto usei apenas para mostrar msgs na home;
- **Firebase Admob:** Usado para mostrar propagandas quando o usuário chegar na tela de resultados do desafio afim de monetizar o app;

## License

This project is licensed under the GPL - 3.0  License

## Demo
<p>
 <img src="https://github.com/IsaqueCoelho/PopKornStudio/blob/master/login.gif?raw=true!" alt="No Gif é mostrado a tela de login, nela tem no topo escrito PopKorn Studio, a tela em si é toda preenchida por uma imagem que é de uma mesa com um saco de pipocas no canto inferior direito e no centro a figura de um caderno de anotações, ao centro da figura do caderno tem alguns objetos postos lado a lado que são relacionados a cinema começando com um rolo de filme, uma pipoca e por fim um óculos 3d, e abaixo dessas figura tem escrito em amarelo a palavra cinema. Abaixo tem um botão vermelho em que esta escrito "Logar usando google". No gif o usuário clica no botão vermelho para logar, em seguida é aberto uma janela com uma lista de duas opções de conta google que o usuário pode usar para acessar, sendo que ele escolhe a primeira e, em seguida, abre outra janela com uma lista de informações sobre as permissões que o usuário deve dar para acessar o app e após clicar no botão confirmar na parte de baixo da janela ele é redirecionado para a tela de home do app. A tela de home tem uma foto do usuário em um circulo no canto superior esquerdo da tela, ao lado direito da imagem tem o nome do usuário e abaixo no nome tem o email do usuário e ao lado do email tem o botão de sair. Abaixo tem escrito Olá e o nome do usuário. Abaixo existe 4 botões dispostos como se estivessem dentro de uma tabela 2 por 2" width="300"/>
 <span>&nbsp;</span>
 <span>&nbsp;</span>
 <span>&nbsp;</span>
 <img src="https://github.com/IsaqueCoelho/PopKornStudio/blob/master/about.gif?raw=true!" alt="No Gif o usuário está na tela de home e clica no botão que está na segunda linha na primeira coluna que esta escrito Sobre PopKorn. Abre-se uma janela de fundo laranja em que no topo tem o logo do app e embaixo o nome do app, e abaixo 3 paragrafos pequenos com alguns dizeres do criador do app. O usuário faz uma pequena rolagem na tela pra mostrar todo conteúdo e depois fecha a janela voltando assim pra home" width="300"/>
</p>
<p>
 <img src="https://github.com/IsaqueCoelho/PopKornStudio/blob/master/ranking.gif?raw=true!" alt="No Gif o usuário está na tela de home e clica no botão que está na primeira linha na segunda coluna em que esta escrito Conferir Ranking. Abre-se uma tela que no topo tem uma barra azul com uma seta virada para a esquerda sinalizando que é o botão de voltar e ao lado está escrito Ranking. Nessa tela tem uma lista de usuário onde cada item tem o número da positão, a foto do usuário, ao lado da foto tem o nome e embaixo tem escrito o level do usuário. na parte inferior da tela tem uma barra verde com nome do genero de filme daquele ranking no centro, e no canto superior direito da barra tem um ícone redondo de filtro com metade do cirtulo para fora da barra. No gif o usuário clica no botão de filtro o que faz a barra verde subir e exibir uma lista de generos distintos de filmes e ao clicar em um deles a barra desce e a lista de ranking muda o nome dos usuário mostrando assim os integrantes daqueles rankings. O usuário faz a troca de ranking por duas vezes, escolhendo as categorias Animação e em seguida comédia, e deslizando a tela pra mostrar alguns usuários, e após isso clica no botão de voltar no canto superior esquerdo da tela e volta para a home" width="300"/>
 <span>&nbsp;</span>
 <span>&nbsp;</span>
 <span>&nbsp;</span>
 <img src="https://github.com/IsaqueCoelho/PopKornStudio/blob/master/game.gif?raw=true!" alt="No Gif o usuário está na tela de home e clica no botão que está na segunda linha e na segunda coluna em que esta escrito Iniciar Desafio. Abre-se uma tela que no topo tem uma barra azul com uma seta virada para a esquerda sinalizando que é o botão de voltar e ao lado está escrito Desafio. Abaixo tem o número da pergunta com o titulo dela que varia entre Qual o filme da sinopse e Quando lançou o filme. Abaixo do titulo tem um quadrado pretro com bordas arredondadas e letras brancas onde fica a sinopse do filme ou então o nome do filme. Abaixo tem um texto escrito As opções são. Abaixo tem uma lista de 3 opções de respostas, que podem ser o nome do filme ou a data de lançamento de acordo com a pergunta. Cada item das opções das respostas tem no canto esquerdo uma letra que pode ser A, B ou C e ao lado a resposta. no canto infeiro direito tem um contador de tempo que varia de 30 segundos para perguntas com sinopse, e 15 segundos para perguntas de tada de lançamento. No Gif sempre que o usuário clica em uma resposata o corpo da tela desliza para a esquerda dando espaço para a nova pergunta até que o usuário responde 10 perguntas onde é redirecionado para a tela de resultado. A telade resultado tem no topo escrito Resultado, abaixo está escrito confira aqui o resultado do desafio. abaixo tem um grande quadrado verde de bordas arredondadas que informa o genero de filme e quantas perguntas foram acertadas. Abaixo do quadrado de resultados tem um botão azul turqueza escrito Confirmar, e ao clicar nele o usuário é redirecionado para a tela de home." width="300"/>
</p>
