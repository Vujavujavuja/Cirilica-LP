import subprocess
from ply.lex import LexToken


tokens = [
    'MAIN_BEGIN', 'MAIN_END', 'ASSIGN', 'NAME', 'IF', 'ELIF', 'ELSE', 'SCAN', 'SCANDEST', 'PRINT', 'INT', 'DOUBLE',
    'STRING', 'CHAR', 'ARRAY', 'INT_WORD', 'DOUBLE_WORD', 'STRING_WORD', 'CHAR_WORD', 'PLUS', 'MINUS', 'TIMES',
    'DIV', 'MOD', 'EQUALS', 'LESS_OR_EQUAL', 'GREATER_OR_EQUAL', 'LESS', 'GREATER', 'NOT_EQUAL', 'LOGAND', 'LOGOR',
    'LOGNOT', 'OPEN_CURLY_BRACKET', 'CLOSED_CURLY_BRACKET', 'OPEN_BRACKET', 'CLOSED_BRACKET', 'OPEN_SQUARE', 'CLOSE_SQUARE',
    'QUOTATION_MARK', 'APOSTROPHE', 'COMMA', 'COMMAND_END', 'FOR', 'WHILE', 'FUNCTION', 'RETURN'
]


class MyLexer:
    def __init__(self):
        self.token_stream = None

    def input(self, input_data):
        self.token_stream = run_lexer(input_data)

    def token(self):
        try:
            return next(self.token_stream)
        except StopIteration:
            return None


def run_lexer(input_file):
    java_path = "../javaLexer/out/production/javaLexer/"
    run_command = ["java", "-cp", java_path, "Lexer"]
    process = subprocess.Popen(run_command, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE,
                               text=True, encoding='utf-8')

    stdout, stderr = process.communicate(input_file)

    if stderr:
        raise Exception(f"Error while running lexer: {stderr}")

    stdout.encode('utf-8').decode('utf-8')
    for line in stdout.split('\n'):
        if not line or line == '\n':
            continue
        line = line.encode('utf-8').decode('utf-8')
        token_type = line.split('->')[0].upper()
        token_value = line.split('->')[1]
        if token_type:
            token = LexToken()
            token.type = token_type
            token.value = token_value
            token.lineno = -1
            token.lexpos = -1
            print(token)
            yield token


