#language: pt

  @regressivo
    Funcionalidade: Cadastro de novo ponto de coleta
      Como usuario da API
      Quero cadastrar um novo ponto de coleta
      Para que o registro seja salvo corretamente no sistema

      Cenario: Cadastro bem-sucedido de ponto de coleta
        Dado que eu tenha os seguintes dados do ponto de coleta:
          | campo                 | valor                     |
          | nome                  | Ponto de Coleta Central   |
          | endereco              | Rua Principal, 123        |
          | horarioFuncionamento  | 120                       |
          | tiposResiduosIds      | 1                         |
        Quando  eu enviar a requisição para o endpoint "/post" de cadastrar ponto de coleta
        Então o status code da resposta de ponto de coleta deve ser 201
        E o arquivo de contrato esperado é o "Cadastro de ponto de coleta bem sucedido" de ponto de coleta
        Então a resposta da requisição deve estar em conformidade com o contrato de ponto de coleta