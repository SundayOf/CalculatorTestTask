class Expression {
    int op1, op2;
    Operators operator;
    NumeralType type;
    public Expression(int op1, int op2, Operators operator, NumeralType type){
        this.op1 = op1;
        this.op2 = op2;
        this.operator = operator;
        this.type = type;
    }
}
