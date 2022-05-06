# Guia de Implementação de View Customizada do Liveness 3D - Versão Beta



A Interface do Liveness 3D é altamente customizável e permite alterar cores, fontes, bordas, ícones, textos, fundos e botões. 

**São elementos customizáveis:** textos, cores, imagens, fontes, bordas e linhas.

**São telas customizáveis:** tela principal, de identificação e de orientações para usuários; tela de preparação, de progresso e de sucesso.

**Não são customizáveis**: propriedades de segurança e direitos autorais, desvios de layout significativos, dimensionamento das fontes.


## 1. Elementos Customizáveis

**INSERIR IMAGEM AQUI 

**Botão de cancelamento:** imagem e localização.

**Todas as telas Liveness 3D e Tela de Fundo:** cores: borda, largura, raio, fundo; sombra.

**Marca do cliente:** logotipo de marca personalizada exibido na interface.

**Tela de execução do liveness:** cores: borda, largura, raio, fundo, texto; fonte do texto; sombra.

**Barra de Texto:** cores: borda, largura, raio, fundo, texto; fonte de texto; sombra.

**Bordas da captura da face:** cor, largura, raios; estilo de animação.

**Quadro de captura e identificação:** cores: borda, largura, raio, fundo.

**Botões de ação:** cor da borda, largura, raio, fonte, fundo; fonte de texto.


## 2. Customizações Gerais

**INSERIR IMAGEM AQUI 

**1 Botão Voltar**
 - CancelButtonCustomization.customImage
 - CancelButtonCustomization.location
 - CancelButtonCustomization.customLocation

**2  Barra de texto e Frame geral**
- FeedbackCustomization.backgroundColor
- FeedbackCustomization.cornerRadius
- FeedbackCustomization.shadow
- FeedbackCustomization.textColor
- FeedbackCustomization.textFont

**3  Borda da captura da face**
- OvalCustomization.strokeColor
- OvalCustomization.strokeWidth
- OvalCustomization.progressColor1
- OvalCustomization.progressColor2
- OvalCustomization.progressStrokeWidth
- OvalCustomization.progressRadialOffset

### 2.1 Outras customizações

**Cores do Texto**
- GuidanceCustomization.foregroundColor

**Botões**
- GuidanceCustomization.buttonFont
- GuidanceCustomization.buttonBorderWidth
- GuidanceCustomization.buttonBorderColor
- GuidanceCustomization.buttonCornerRadius
- GuidanceCustomization.buttonTextNormalColor
- GuidanceCustomization.buttonTextHighlightColor
- GuidanceCustomization.buttonTextDisabledColor
- GuidanceCustomization.buttonBackgroundNormalColor
- GuidanceCustomization.buttonBackgroundHighlightColor
- GuidanceCustomization.buttonBackgroundDisabledColor

**Customização de orientações**
- GuidanceCustomization.readyScreenTextBackgroundColor
- GuidanceCustomization.readyScreenTextBackgroundCornerRadius

> As customizações de orientações são aplicáveis apenas para dispositivos muito pequenos e com espaço de tela reduzido.


### 2.2 Outras customizações relevantes:

**Customize o estilo da appearing:**
- EntryAnimationCustomization: EntryAnimationStyle

**Customize o estilo da disappearing:**
- ExitAnimationCustomization: ExitAnimationStyle


## 3. Customizações da Tela de Execução do Liveness 3D

As **customizações da tela de execução do Liveness 3D** devem respeitar as propriedades customizáveis vistas acima.


## 4. Customização da Tela de Nova Tentativa (Retry  Screen)

Nesta tela, são fornecidas orientações que guiam o usuário a realizar uma nova Sessão Liveness 3D. 
Apresenta boa iluminação e pose, permitindo que o usuário compreenda as correções necessárias.

### 4.1 Tela de Nova Tentativa

**1 Orientação Principal**
- headerFont

**2 Orientação da Face**
- retryScreenIdealFaceTecImage
- retryScreenSlideshowImages
- retryScreenSlideshowInterval
- enableRetryScreenSlideshowShuffle
- retryScreenImageBorderColor
- retryScreenImageBorderWidth
- retryScreenImageCornerRadius
- retryScreenOvalStrokerColor

**3 Orientações secundárias**
- subtextFont

### 4.2 Tela de Nova Tentativa - Adicionais

**1 Orientação Principal - Adicionais**
- retryScreenHeaderFont
- retryScreenHeaderTextColor
- retryScreenHeaderAttributedString

**2 Orientação Secundária - Adicionais**
- retryScreenSubtextFont
- retryScreenSubtextTextColor
- retryScreenSubtextAttributedString
