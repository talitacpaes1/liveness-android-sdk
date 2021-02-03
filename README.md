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
    implementation 'br.com.oititec:liveness-sdk:1.4.0'
}
```

## Release Notes

- As novidades das versões podem ser acessadas [neste link](Documentation/ReleaseNotes.md).
