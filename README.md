# Certiface

Bibliotecas Certiface para Android.

## Instalação

### Gradle

No arquivo `build.gradle` do projeto, adicione o repositório:

```gradle
allprojects {
    repositories {
        maven { url "https://raw.githubusercontent.com/oititec/liveness-android-sdk/main/" }
    }
}
```

No arquivo  `build.gradle` do módulo/app, adicione a dependência:

```gradle
dependencies {
    implementation 'br.com.oititec:liveness-sdk:2.3.1'
}
```

## Uso

### Liveness Oiti

As instruções de uso e integração do Liveness podem ser acessadas [neste link](Documentation/Liveness-Usage.md).

### Liveness 3D

As instruções de uso e integração do Liveness podem ser acessadas neste link.


### Documentoscopia

As instruções de uso e integração da Documentoscopia podem ser acessadas [neste link](Documentation/Documentscopy-Usage.md).

## Sample

Um exemplo de implementação pode ser encontrado no projeto [SampleFaceCaptcha](https://github.com/oititec/liveness-android-sdk/tree/main/FaceCaptchaSample "SampleFaceCaptcha"), neste mesmo repositório.

## Changelog

As novidades das versões podem ser acessadas [neste link](Documentation/Changelog.md).

## Guias de migração

- [2.0.0](Documentation/Migration-Guide-2.0.0.md)
- [1.5.0](Documentation/Migration-Guide-1.5.0.md)
- [1.4.1](Documentation/Migration-Guide-1.4.1.md)
- [1.4.0](Documentation/Migration-Guide-1.4.0.md)
