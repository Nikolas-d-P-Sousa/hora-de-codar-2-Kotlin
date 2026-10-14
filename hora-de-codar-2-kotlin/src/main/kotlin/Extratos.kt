import java.time.LocalDate


var date: LocalDate? = LocalDate.now()


data class Extrato(
    var dia: String? = null,
    var valor: Double?= null,
    var numero_da_conta: Int? = null,
    var descricao: String? = null
)

val compraAmazon = Extrato(dia = "2026-05-11", valor = -94.47,
    numero_da_conta = 6769,
    descricao = "Compra do livro Kafka à beira-mar ")

val extratoLista = mutableListOf<Extrato>(compraAmazon)