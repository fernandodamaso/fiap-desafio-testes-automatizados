package org.example.projetoesgfiap.steps;

import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.example.projetoesgfiap.dto.TipoResiduoDTO;
import org.example.projetoesgfiap.service.CadastroTipoResiduoService;

public class CadastroTipoResiduoSteps {

    private final CadastroTipoResiduoService cadastroTipoResiduoService = new CadastroTipoResiduoService();

    private TipoResiduoDTO tipoResiduo;
    private Response response;


    @Dado("que eu tenha os seguintes dados do tipo de resíduo:")
    public void queEuTenhaOsSeguintesDadosDoTipoDeResiduo(io.cucumber.datatable.DataTable dataTable) {
        java.util.Map<String, String> dados = dataTable.asMap(String.class, String.class);
        tipoResiduo = new TipoResiduoDTO(
                dados.get("nome"),
                dados.get("descricao")
        );
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastrar tipo de resíduo")
    public void euEnviarARequisicaoParaOEndpointDeCadastrarTipoDeResiduo(String endpoint) {
        response = cadastroTipoResiduoService.cadastrarTipoResiduo(tipoResiduo, endpoint);
    }

    @Entao("o status code da resposta de tipo de resíduo deve ser {int}")
    public void oStatusCodeDaRespostaDeTipoDeResiduo(int statusCode) {
        org.junit.Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @E("o arquivo de contrato esperado é o {string} de tipo de resíduo")
    public void oArquivoDeContratoEsperadoETipoDeResiduo(String schemaPath) throws Exception {
        cadastroTipoResiduoService.setContract(schemaPath);
    }

    @Entao("a resposta da requisição deve estar em conformidade com o contrato de tipo de resíduo")
    public void aRespostaDaRequisicaoDeveEstarEmConformidadeComOContratoDeTipoDeResiduo() throws Exception {
        java.util.Set<com.networknt.schema.ValidationMessage> validateResponse = cadastroTipoResiduoService.validateResponseAgainstSchema(response);
        org.junit.Assert.assertTrue("O contrato está inválido!. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @After
    public void limparDados() {
        if (response != null && response.getStatusCode() == 201) {
            Long id = null;
            try {
                id = response.jsonPath().getLong("id");
            } catch (Exception e) {
                System.out.println("ID não encontrado na resposta, nada para excluir.");
            }
            if (id != null) {
                cadastroTipoResiduoService.excluirTipoResiduo(id);
            }
        }
    }

}
