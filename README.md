#  Certiface para Android

Neste repositório encontre as documentações para **Android** sobre o **Liveness FaceCaptcha**, **Liveness 3D** e **Documentoscopia**.



## Sobre

O Liveness, ou Prova de Vida, pode ser executado através de  SDK único que reúne os tipos: *Liveness FaceCaptcha* e *Liveness 3D*. 

Os tipos de Liveness serão apresentados na demonstração do serviço e a escolha de um deles deve ser efetuada mediante contratação. Um diagnóstico será efetuado pelo time comercial, responsável por ofertar a melhor experiência para o cliente, considerando seu o modelo de negócio e o comportamento de seus usuários.

**Para cada tipo de Liveness, são apresentados propriedades técnicas específicas**. Assim, a pessoa desenvolvedora deve executar os comandos pertencentes ao tipo do serviço contratado.

Abaixo, estão descritos os processos de: instalação, uso, guias de migração e outros documentos. Esses processos integram o projeto FaceCapctha.

##  Instalação

A instalação pode ocorrer de duas formas: manualmente ou via Gradle (repositório).

### Manual

No arquivo `build.gradle` do projeto, adicione o repositório:


```gradle

repositories {
    flatDir { dirs 'libs/' }
}

implementation 'br.com.oiti.liveness3d:3.0.0@aar'
```

> **Nota: [clique aqui](https://github.com/oititec/liveness-android-sdk/tree/main/br/com/oititec/liveness-sdk) para baixar os artefatos.**

###  Gradle

No arquivo `build.gradle` do projeto, adicione o repositório:

```gradle
allprojects {
  repositories {
     maven { url "https://raw.githubusercontent.com/oititec/liveness-android-sdk/main/" }
  }
}
```

No arquivo `build.gradle` do módulo/app, adicione a dependência:

```gradle
dependencies {
  implementation 'br.com.oititec:liveness-sdk:2.3.1'
}
```

##  Uso

###  Liveness FaceCaptcha

As instruções de uso, integração e implementação do **Liveness FaceCaptcha** podem ser acessadas nos links abaixo:

  - [Guia de uso e integração](Documentation/Liveness-Usage.md);
  - [Guia de implementação de view customizada](Documentation/Liveness-CustomView.md).

###  Liveness 3D

As instruções de uso, integração, implementação e customização do **Liveness 3D** podem ser acessadas nos links abaixo: 

  - [Guia de uso e integração](Documentation/Liveness3D-Usage.md);
  - [Guia de implementação de view customizada](Documentation/Liveness3D-CustomView.md);
  - [Guia de customização do Liveness3DTheme](Documentation/Liveness3D-Liveness3DTheme.md);
  - [Exemplo de Projeto: criação e inicialização](https://github.com/oititec/liveness3d-sample).


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
