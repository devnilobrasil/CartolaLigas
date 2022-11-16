# Cartola APP Admin
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/devnilobrasil/CartolaLigas/blob/master/License) 

# Sobre o projeto

O Cartola APP Admin é uma aplicação mobile desenvolvida para gerir uma liga familiar do Cartola FC. 

Consiste em uma aplicação no qual é possível cadastrar/adicionar jogadores da liga, determinando as seguintes informações:
- Nome do Jogador
- Nome do Time
- Títulos Conquistados

Cada título tem uma pontuação específica e determina o desempenho de um jogador ao longo do histórico de participação da liga. Estes dados são armazenados no BD da Firebase Realtime Database, e servirá como base para alimentar a aplicação principal do projeto (em desenvolvimento).

## Layout mobile
<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/splash.png" width="20%" height="20%"> <img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/screen1.png" width="20%" height="20%">
<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/screen2.png" width="20%" height="20%"> <img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/screen3.png" width="20%" height="20%">

# Usabilidade

1) A tela inicial será uma Splash no qual consta o nome e a logo do APP
<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/splash.png" width="20%" height="20%">

2) Posteriormente irá para a tela de login (com o suporte do Firebase Authentication)
<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/login.jpg" width="20%" height="20%">

3) É possível criar uma conta, caso ainda não possua, ou recuperar a conta, caso tenha esquecido a senha.

<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/criar_conta.jpg" width="20%" height="20%"> - <img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/recuperar_conta.jpg" width="20%" height="20%">

4) A tela principal do APP é onde serão exibidos os clubes/jogadores cadastrados. É possível cadastra-los clicando no botão no canto inferior direito da tela.
<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/screen1.png" width="20%" height="20%"> 

5) Tela para o cadastro de clubes/jogadores da liga.

<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/criar_jogador.jpg" width="20%" height="20%"> - <img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/jogador%20registrado.jpg" width="20%" height="20%">

6) Tela inicial com o clube/jogador cadastrado. É possível edita-lo (laranja) ou remove-lo (cinza) clicando nos respectivos botões.

<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/jogador_cadastrado.jpg" width="20%" height="20%">

7) Tela para edição/atualização das informações dos clubes/jogadores cadastrados

<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/editar_jogador.jpg" width="20%" height="20%">

8) Caso clique no nome ou em qualquer área, salvo nos botões mencionados no item 6, será exibido um dialog com as informações detalhadas do clube/jogador.

<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/detalhes.jpg" width="20%" height="20%">

9) Tela para remoção dos clubes/jogadores cadastrados.

<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/deletar_jogador.jpg" width="20%" height="20%">

10) Na aba 'Informações', são exibidas as informações sobre os títulos, pontuação e a imagem do troféu.

<img src="https://github.com/devnilobrasil/assets/blob/main/cartolaAppAdmin/informacoes.jpg" width="20%" height="20%">

# Linguagem utilizada
- Kotlin

## Banco de Dados
- Firebase Realtime Database

# Autor

Nilo Soares Santos Brasil Sousa
