import ply.yacc as yacc
from lexer import MyLexer, tokens
import os


precedence = (
    ('left', 'PLUS', 'MINUS'),
    ('left', 'TIMES', 'DIV', 'MOD'),
)


# Program -> 'почетак' Block 'крај'
def p_program(p):
    '''Program : MAIN_BEGIN Block MAIN_END
                | FunctionDeclaration Program'''
    p[0] = ('Program', p[2])


# Block -> '{' Statements '}'
def p_block(p):
    'Block : OPEN_CURLY_BRACKET Statements CLOSED_CURLY_BRACKET'
    p[0] = ('Block', p[2])


# Statements -> Statement | Statement Statements
def p_statements(p):
    '''Statements : Statement
                  | Statement Statements'''
    if len(p) == 2:
        p[0] = p[1]
    else:
        p[0] = (p[1], p[2])


# Statement -> Declaration ';'
# | Assignment ';'
# | PrintStatement ';' | IfStatement
# | FunctionDeclaration | FunctionCall ';'
def p_statement(p):
    '''Statement : Declaration COMMAND_END
                 | StringDeclaration COMMAND_END
                 | Array COMMAND_END
                 | Assignment COMMAND_END
                 | PrintStatement COMMAND_END
                 | IfStatement
                 | FunctionDeclaration
                 | FunctionCall COMMAND_END
                 | ScanStatement COMMAND_END
                 | ForStatement
                 | WhileStatement
                 | ReturnStatement COMMAND_END'''
    p[0] = p[1]



# StringDeclaration qoute String qoute
def p_string_declaration(p):
    '''StringDeclaration : STRING QUOTATION_MARK STRING QUOTATION_MARK
                            | STRING APOSTROPHE STRING APOSTROPHE'''
    p[0] = ('StringDeclaration', p[3])


# Declaration -> Type VariableList
def p_declaration(p):
    '''Declaration : Type Variable
                   | Type Variable ASSIGN Expression
                   | Type Variable COMMA VariableList'''
    if len(p) == 3:
        # Declaration without initialization
        p[0] = ('Declaration', {'type': p[1], 'variable': p[2]})
    else:
        # Declaration with initialization
        p[0] = ('Declaration', {'type': p[1], 'variable': p[2], 'value': p[4]})



# Type -> 'цели' ...
def p_type(p):
    '''Type : INT
            | DOUBLE
            | STRING
            | CHAR
            | ARRAY'''
    p[0] = p[1]


# Variable -> NAME
def p_variable(p):
    'Variable : NAME'
    p[0] = p[1]


def p_variable_list(p):
    '''VariableList : Variable
                    | Variable COMMA VariableList'''
    if len(p) == 2:  # Only one variable
        p[0] = [p[1]]
    else:  # A variable followed by more variables
        p[0] = [p[1]] + p[3]


# Assignment -> Variable 'je' Expression
def p_assignment(p):
    'Assignment : Variable ASSIGN Expression'
    p[0] = ('Assignment', p[1], p[3])


# Expression -> Term | Term '+' Expression | Term '-' Expression
def p_expression(p):
    '''Expression : Term
                  | Expression PLUS Term
                  | Expression MINUS Term'''
    if len(p) == 2:
        p[0] = p[1]
    else:
        p[0] = ('Expression', p[1], p[2], p[3])


# Term -> Factor | Factor '*' Term | Factor '/' Term | Factor '%' Term
def p_term(p):
    '''Term : Factor
            | Term TIMES Factor
            | Term DIV Factor
            | Term MOD Factor'''
    if len(p) == 2:
        # Single factor
        p[0] = p[1]
    else:
        # Binary operation (TIMES, DIV, or MOD)
        p[0] = ('Term', p[1], p[2], p[3])




def p_factor(p):
    '''Factor : INT_WORD
              | DOUBLE_WORD
              | STRING_WORD
              | CHAR_WORD
              | NAME_WORD
              | OPEN_BRACKET Expression CLOSED_BRACKET'''
    p[0] = p[1]


# NAME_WORD -> NAME | FunctionCall
def p_name_word(p):
    '''NAME_WORD : NAME
                 | FunctionCall'''
    p[0] = p[1]


# PrintStatement -> 'пиши' Expression
def p_print_statement(p):
    'PrintStatement : PRINT Expression'
    p[0] = ('PrintStatement', p[2])


# IfStatement -> 'ако' '(' Expression ')' Block
# | 'ако' '(' Expression ')' Block 'иначе' Block
# | 'ако' '(' Expression ')' Block ElifStatements
# | 'ако' '(' Expression ')' Block ElifStatements 'иначе' Block
def p_if_statement(p):
    '''IfStatement : IF OPEN_BRACKET Expression CLOSED_BRACKET Block
                   | IF OPEN_BRACKET Expression CLOSED_BRACKET Block ELSE Block
                   | IF OPEN_BRACKET Expression CLOSED_BRACKET Block ElifStatements
                   | IF OPEN_BRACKET Expression CLOSED_BRACKET Block ElifStatements ELSE Block'''
    if len(p) == 6:
        # IfStatement -> 'ако' '(' Expression ')' Block
        p[0] = ('IfStatement', p[3], p[5])
    elif len(p) == 8:
        # IfStatement -> 'ако' '(' Expression ')' Block 'иначе' Block
        p[0] = ('IfStatement', p[3], p[5], p[7])
    elif len(p) == 7:
        # IfStatement -> 'ако' '(' Expression ')' Block ElifStatements
        p[0] = ('IfStatement', p[3], p[5], p[6])
    else:
        # IfStatement -> 'ако' '(' Expression ')' Block ElifStatements 'иначе' Block
        p[0] = ('IfStatement', p[3], p[5], p[6], p[8])


# ElifStatements -> ElifStatement | ElifStatement ElifStatements
def p_elif_statements(p):
    '''ElifStatements : ElifStatement
                      | ElifStatement ElifStatements'''
    if len(p) == 2:
        p[0] = p[1]
    else:
        p[0] = ('ElifStatements', p[1], p[2])


# ElifStatement -> 'иначе_ако' '(' Expression ')' Block
def p_elif_statement(p):
    'ElifStatement : ELIF OPEN_BRACKET Expression CLOSED_BRACKET Block'
    p[0] = ('ElifStatement', p[3], p[5])


# FunctionDeclaration -> 'функција' FunctionName '(' FunctionParameters ')' Block
def p_function_declaration(p):
    'FunctionDeclaration : FUNCTION FunctionName OPEN_BRACKET FunctionParameters CLOSED_BRACKET Block'
    p[0] = ('FunctionDeclaration', p[2], p[4], p[6])


# FunctionName -> NAME
def p_function_name(p):
    'FunctionName : NAME'
    p[0] = p[1]


# FunctionParameters -> FunctionParameter | FunctionParameter ',' FunctionParameters
def p_function_parameters(p):
    '''FunctionParameters : FunctionParameter
                          | FunctionParameter COMMA FunctionParameters'''
    if len(p) == 2:
        # Only one parameter, create a new list
        p[0] = [p[1]]
    else:
        # Append the current parameter to the existing list
        p[0] = [p[1]] + p[3]

# FunctionParameter -> Type Variable
def p_function_parameter(p):
    'FunctionParameter : Type Variable'
    p[0] = ('FunctionParameter', p[1], p[2])


# FunctionCall -> FunctionName '(' FunctionArguments ')'
def p_function_call(p):
    'FunctionCall : FunctionName OPEN_BRACKET FunctionArguments CLOSED_BRACKET'
    p[0] = ('FunctionCall', p[1], p[3])


# FunctionArguments -> FunctionArgument | FunctionArgument ',' FunctionArguments
def p_function_arguments(p):
    '''FunctionArguments : FunctionArgument
                         | FunctionArgument COMMA FunctionArguments'''
    if len(p) == 2:
        p[0] = p[1]
    else:
        p[0] = ('FunctionArguments', p[1], p[3])


# FunctionArgument -> Expression
def p_function_argument(p):
    'FunctionArgument : Expression'
    p[0] = p[1]


# ReturnStatement -> 'врати' Expression
def p_return_statement(p):
    'ReturnStatement : RETURN Expression'
    p[0] = ('ReturnStatement', p[2])


# WhileStatement -> 'док' '(' Expression ')' Block
def p_while_statement(p):
    'WhileStatement : WHILE OPEN_BRACKET Expression CLOSED_BRACKET Block'
    p[0] = ('WhileStatement', p[3], p[5])


# ForStatement -> 'за' '(' Assignment ';' Expression ';' Assignment ')' Block
def p_for_statement(p):
    'ForStatement : FOR OPEN_BRACKET Assignment COMMAND_END Expression COMMAND_END Assignment CLOSED_BRACKET Block'
    p[0] = ('ForStatement', p[3], p[5], p[7], p[9])


# LogicalExpression -> LogicalTerm | LogicalTerm 'и' LogicalExpression
def p_expression_logical(p):
    '''Expression : Expression LOGAND Expression
                  | Expression LOGOR Expression
                  | LOGNOT Expression'''
    if len(p) == 4:
        p[0] = ('LogicalExpression', p[1], p[2], p[3])
    else: # Unary logical NOT
        p[0] = ('LogicalNot', p[2])


# Relationships
def p_expression_relational(p):
    '''Expression : Expression EQUALS Expression
                  | Expression LESS_OR_EQUAL Expression
                  | Expression GREATER_OR_EQUAL Expression
                  | Expression LESS Expression
                  | Expression GREATER Expression
                  | Expression NOT_EQUAL Expression'''
    p[0] = ('RelationalExpression', p[1], p[2], p[3])


# Array -> 'низ' '[' ArrayElements ']'
def p_array(p):
    'Array : ARRAY OPEN_SQUARE ArrayElements CLOSE_SQUARE'
    p[0] = ('Array', p[3])


# ArrayElements -> ArrayElement | ArrayElement ',' ArrayElements
def p_array_elements(p):
    '''ArrayElements : ArrayElement
                     | ArrayElement COMMA ArrayElements'''
    if len(p) == 2:
        p[0] = p[1]
    else:
        p[0] = ('ArrayElements', p[1], p[3])


# ArrayElement -> Expression
def p_array_element(p):
    'ArrayElement : Expression'
    p[0] = p[1]


# ScanStatement -> 'прочитај' ScanDestination
def p_scan_statement(p):
    'ScanStatement : SCAN SCANDEST Variable'
    p[0] = ('ScanStatement', p[3])


# Error rule for syntax errors
def p_error(p):
    print("ERROR_START")
    print(f"Syntax error at {p.value!r}")
    print(p)

    # Check if the token type is in the list of tokens
    if p.type in tokens:
        print(f"The token {p.type} is recognized but used incorrectly.")
    else:
        print(f"The token {p.type} is unrecognized.")

    print("ERROR_END")




def parse(inputed_code):
    lexer = MyLexer()
    #parser = yacc.yacc(debug=True, write_tables=True, outputdir='./', debugfile='parsing_table.txt')
    parser = yacc.yacc()

    result = parser.parse(inputed_code, lexer=lexer)

    return result


if __name__ == "__main__":
    input_code = """
        почетак
        {
            цели број м;
            прочитај и унеси у м;
            пиши м;
        }
        крај
        """

    parsed_result = parse(input_code)

    print(parsed_result)
