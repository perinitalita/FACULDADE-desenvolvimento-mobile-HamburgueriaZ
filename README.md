# HamburgueriaZ - Projeto Mobile

## Descrição
Este é um aplicativo Android desenvolvido para a disciplina de **Desenvolvimento Mobile**. O objetivo do projeto é simular o sistema de pedidos de uma hamburgueria, permitindo a personalização do lanche e o envio do pedido por e-mail.

## Funcionalidades
- **Personalização**: Escolha de adicionais como Bacon, Queijo e Onion Rings via Checkbox.
- **Controle de Quantidade**: Botões de incremento (+) e decremento (-) com validação para evitar quantidades negativas.
- **Resumo em Tempo Real**: O valor total do pedido é atualizado instantaneamente na tela.
- **Envio de Pedido**: Integração com aplicativos de e-mail externos via `Intent (ACTION_SENDTO)` para envio do resumo do pedido.
- **Interface Responsiva**: Uso de `ScrollView` e `LinearLayout` para garantir que o layout se ajuste a diferentes tamanhos de tela e teclados.
- **Estilização Padronizada**: Implementação de estilos customizados no arquivo `themes.xml`.

## Requisitos do Roteiro (Checklist)
- [x] Criação do projeto HamburgueriaZ (API 23).
- [x] Interface com campo de nome, adicionais e seletor de quantidade.
- [x] Padronização de estilo de texto via `EstiloTexto`.
- [x] Inserção de logotipo como banner.
- [x] Lógica de somar/subtrair quantidade.
- [x] Cálculo automático de preços (Base R$ 20 + Adicionais).
- [x] Geração de resumo do pedido.
- [x] Implementação de Intent para e-mail.

## Tecnologias
- **Linguagem**: Java
- **Ambiente**: Android Studio
- **Versão do SDK**: Android 6.0 (Marshmallow) - API 23

---
*Projeto desenvolvido para fins acadêmicos.*