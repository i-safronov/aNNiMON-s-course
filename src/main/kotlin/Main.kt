import parser.Lexer

fun main(args: Array<String>) {
    val input = "2 + 2"
    val lexer = Lexer(input).tokenize()
    lexer.forEach {
        println("parser.Token: ${it.type}")
    }
}