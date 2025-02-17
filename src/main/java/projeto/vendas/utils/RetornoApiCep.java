package projeto.vendas.utils;

public record RetornoApiCep(String cep,
                            String state,
                            String city,
                            String neighborhood,
                            String street,
                            String complement,
                            String ibge) {

    public RetornoApiCep(RetornoApiCep retornoCep) {
        this(retornoCep.cep(),
             retornoCep.state(),
             retornoCep.city(),
             retornoCep.neighborhood(),
             retornoCep.street(),
             retornoCep.complement(),
             retornoCep.ibge());
    }
}
