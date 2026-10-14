import kotlin.system.exitProcess

var saldo = 340.5 // Float
var user = ""
fun main() {
    val nome = readString("Digite seu nome de usuário: ").capitalize()
    user = nome

    while (!senhaCheck()){
        senhaCheck()
    }

    println("Olá $nome! \nSeja bem-vindo(a) ao Banco Maravilha! ")
    readString("Pressione ENTER para continuar ")
    inicio()
}

fun inicio() {
    println("Escolha uma opção:")
    println("1 - Ver saldo")
    println("2 - Extrato")
    println("3 - Fazer depósito")
    println("4 - Fazer saque")
    println("5 - Fazer transferência")
    println("6 - Sair")


    val escolha = readlnOrNull()?.toIntOrNull()

    when (escolha) {
        1 -> verSaldo()
        2 -> verExtrato()
        3 -> fazerDeposito()
        4 -> fazerSaque()
        5 -> fazerTransferencia()
        6 -> sair()
        else -> erro()
    }
}

fun verSaldo() {
    if (!senhaCheck()){
        inicio()
    }
    println("Seu saldo atual é: $saldo")
    readString("Pressione ENTER para continuar: ")
    inicio()
}

fun verSaldoReturn() {
    // Mostra o Saldo após efetuar uma operação. Usado para não repetir a entrada da senha novamente
    println("Seu saldo atual é: $saldo")
    readString("Pressione ENTER para continuar: ")
    inicio()
}

fun verExtrato() {
    if (!senhaCheck()){
        inicio()
    }
    val extratoIterator = extratoLista.iterator()
    while (extratoIterator.hasNext()) {
        println(extratoIterator.next())
    }
    readString("Pressione ENTER para continuar: ")
    inicio()

}

fun fazerDeposito() {
    if (!senhaCheck()){
        inicio()
    }
    val deposito =readDouble("Qual o valor para deposito? ",1.0)
    saldo += deposito
    val saldoExtrato = Extrato(dia = date.toString(), valor = deposito, numero_da_conta = null, descricao = "Depósito")
    extratoLista.add(saldoExtrato)
    verSaldoReturn()
}

fun fazerSaque() {
    if (!senhaCheck()){
        inicio()
    }
    val saque = readDouble("Qual o valor para saque? : ",1.0,saldo)
        saldo -= saque
    val saldoExtrato = Extrato(dia = date.toString(), valor = toNegative(saque), numero_da_conta = null, descricao = "Saque")
    extratoLista.add(saldoExtrato)
        verSaldoReturn()
    }

fun fazerTransferencia() {
    if (!senhaCheck()){
        inicio()
    }
    var descricao = ""
    val num_transf = readInt("Insira o número da conta: ")
    val valorTransf = readDouble("Insira o valor da transferência: ",1.0,saldo)
    val continuar = readString("Adicionar descrição? (S/N): ").uppercase()
    if (continuar in positivo) {
        descricao = readString("Insira descrição: ")
    }
    val transferencia = Extrato(dia = date.toString(), valor = toNegative(valorTransf),
        numero_da_conta = num_transf, descricao = descricao)
    extratoLista.add(transferencia)
    verSaldoReturn()
}

fun erro() {
    println("Por favor, informe um número entre 1 e 6.")
    inicio()
}

fun sair() {
    print("Você deseja sair? (S/N): ")
    // Usando readln e Elvis operator -> Operador de coalescência nula
    val confirma = readln().uppercase()
    // Locale -> Localização do usuário para converter para maiúsculo
    //val confirma = readLine()?.uppercase(Locale.getDefault())

    when (confirma) {
        in positivo -> {
            println("$user, foi um prazer ter você por aqui!")
            exitProcess(0) //Hasta la vista, baby
        }

        "N" -> inicio() // Volta para o início
        else -> sair()
    }
}