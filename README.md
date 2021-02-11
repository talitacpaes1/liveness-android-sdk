# Liveness

Biblioteca Liveness para Android.

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
    implementation 'br.com.oititec:liveness-sdk:1.5.0'
}
```

## Release notes

- As novidades das versões podem ser acessadas [neste link](Documentation/ReleaseNotes.md).

## Guias de migração

- [1.5.0](Documentation/Migration-Guide-1.5.0.md)
- [1.4.1](Documentation/Migration-Guide-1.4.1.md)
- [1.4.0](Documentation/Migration-Guide-1.4.0.md)
