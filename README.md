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

