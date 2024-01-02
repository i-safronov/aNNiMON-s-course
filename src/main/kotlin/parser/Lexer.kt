package parser

class Lexer(private val input: String) {
    private val tokens: MutableList<Token>
    private val lenght: Int
    private var pos = 0

    init {
        lenght = input.length
        tokens = ArrayList()
    }

    fun tokenize(): List<Token> {
        while (pos < lenght) {
            val current = peek(0)
            if (Character.isDigit(current)) tokenizeNumber() else if (OPERATOR_CHARS.indexOf(current) != -1) {
                tokenizeOperator()
            } else {
                // whitespaces
                next()
            }
        }
        return tokens
    }

    private fun tokenizeNumber() {
        val buffer = StringBuilder()
        var current = peek(0)
        while (Character.isDigit(current)) {
            buffer.append(current)
            current = next()
        }
        addToken(TokenType.NUMBER, buffer.toString())
    }

    private fun tokenizeOperator() {
        val position = OPERATOR_CHARS.indexOf(peek(0))
        addToken(
            OPERATOR_TOKENS[position]
        )
        next()
    }

    private operator fun next(): Char {
        pos++
        return peek(pos)
    }

    private fun peek(relativePosition: Int): Char {
        val position = pos + relativePosition
        return if (position >= lenght) '\u0000' else input[position]
    }

    private fun addToken(type: TokenType) {
        tokens.add(
            Token("", type)
        )
    }

    private fun addToken(type: TokenType, text: String) {
        tokens.add(
            Token(
                text,
                type
            )
        )
    }

    companion object {
        private const val OPERATOR_CHARS = "+-*/"
        private val OPERATOR_TOKENS = arrayOf(
            TokenType.PLUS, TokenType.MINUS,
            TokenType.SLASH, TokenType.STAR
        )
    }
}