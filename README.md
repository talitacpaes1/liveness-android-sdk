#  Certiface para Android

Neste repositório encontre as documentações para **Android** sobre o **Liveness FaceCaptcha**, **Liveness 3D** e **Documentoscopia**.



## Sobre

O Liveness, ou Prova de Vida, pode ser executado através de  SDK único que reúne os tipos: *Liveness FaceCaptcha* e *Liveness 3D*. 

Os tipos de Liveness serão apresentados na demonstração do serviço e a escolha de um deles deve ser efetuada mediante contratação. Um diagnóstico será efetuado pelo time comercial, responsável por ofertar a melhor experiência para o cliente, considerando seu o modelo de negócio e o comportamento de seus usuários.

**Para cada tipo de Liveness, são apresentados propriedades técnicas específicas**. Assim, a pessoa desenvolvedora deve executar os comandos pertencentes ao tipo do serviço contratado.

Abaixo, estão descritos os processos de: instalação, uso, guias de migração e outros documentos. Esses processos integram os projetos Liveness2D/FaceCaptcha e Liveness 3D.

##  Instalação

Para efetuar a instalação do liveness, seja **2D** ou **3D**, é necessário baixar e importar as dependências conforme exemplos abaixo:

> **Nota 1:** É necessário baixar e colar o(s) artefato(s) dentro da pasta **libs/** no projeto.

> **Nota 2:** Não é necessário baixar e importar ambas as bibliotecas, somente aquela que for utilizada, seja **2D** ou **3D**.

###  Liveness 2D/Facecaptcha

No arquivo `build.gradle` do projeto, adicione a seguinte configuração:

```gradle

repositories {
    flatDir { dirs 'libs/' }
}

```

No arquivo `build.gradle` do módulo/app, adicione as seguintes dependências:

```gradle
dependencies {
   implementation files('libs/liveness-sdk-2.3.1.aar')
}
```
> **Nota: [clique aqui](https://github.com/oititec/android-oiti-versions) para baixar os artefatos.**

### Liveness 3D

No arquivo `build.gradle` do projeto, adicione a seguinte configuração:

```gradle

repositories {
    flatDir { dirs 'libs/' }
}

```

No arquivo `build.gradle` do módulo/app, adicione as seguintes dependências:

```gradle
 dependencies {
   implementation files('libs/facetec-sdk-9.4.23.aar')
   implementation files('libs/liveness3d-release.aar')
 }
 ```

> **Nota: [clique aqui](https://github.com/oititec/android-oiti-versions) para baixar os artefatos.**

####  Processo de atualização

> **A cada atualização, novas regras de segurança são adicionadas ao SDK, por esse motivo é altamente recomendável que o processo de atualização seja algo constante. Recomendamos ao menos uma atualização a cada 30 dias.**

##  Uso

###  Liveness 2D/Facecaptcha

As instruções de uso, integração e implementação do **Liveness FaceCaptcha** podem ser acessadas nos links abaixo:

  - [Guia de uso e integração](Documentation/Liveness-Usage.md);
  - [Guia de implementação de view customizada](Documentation/Liveness-CustomView.md).

###  Liveness 3D

As instruções de uso, integração, implementação e customização do **Liveness 3D** podem ser acessadas nos links abaixo: 

  - [Guia de uso e integração](Documentation/Liveness3D-Usage.md);
  - [Guia de implementação de view customizada](Documentation/Liveness3D-CustomView.md);
  - [Guia de customização do Liveness3DTheme](Documentation/Liveness3D-Liveness3DTheme.md);
  - [Exemplo de Projeto: criação e inicialização](https://github.com/oititec/android-liveness3d-sample).


###  Documentoscopia

As instruções de uso, integração e customização da **Documentoscopia** podem ser acessadas nos links abaixo:

  - [Guia de uso e integração](Documentation/Documentscopy-Usage.md);
  - [Guia de customização de view customizada](Documentation/Documentscopy-CustomView.md).



##  Outros Documentos

###  Sample

- Um exemplo de implementação pode ser encontrado no projeto [SampleFaceCaptcha](https://github.com/oititec/liveness-android-sdk/tree/main/FaceCaptchaSample "SampleFaceCaptcha"), neste mesmo repositório.

###  Changelog

- As novidades das versões podem ser acessadas [neste link](Documentation/Changelog.md).

##  Guias de migração

- [2.0.0](Documentation/Migration-Guide-2.0.0.md)
- [1.5.0](Documentation/Migration-Guide-1.5.0.md)
- [1.4.1](Documentation/Migration-Guide-1.4.1.md)
- [1.4.0](Documentation/Migration-Guide-1.4.0.md)

>⚠️ **Para conhecer mais sobre Liveness, consulte [este link.](https://certifaceid.readme.io/docs/liveness-detection-vs-atualizada)**
