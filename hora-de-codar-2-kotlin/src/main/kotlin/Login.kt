const val senha = "3589"

fun senhaCheck(

): Boolean{
    val senhaInput = readString("Insira sua senha: ")
        if (senhaInput != senha){
            println("Senha incorreta!")
            return false
        }
    return true
}
