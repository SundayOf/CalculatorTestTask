import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<String> sign = Arrays.asList("+", "-", "*", "/");
    static List<String> arabicNumerals = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    static List<String> romanNumerals = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");

    public static void main(String[] args) {
        try{
            System.out.println("Введите выражение...");
            String input = scanner.nextLine();
            System.out.println("Результат:  " + calc(input));
        } catch (CustomException e){
            System.out.println(e.printMessage());
        }
    }
    public static String calc(String input) throws CustomException {
        String[] piece = input.toUpperCase().split( " ");
        Expression expr;

        if (piece.length < 3){
            throw new CustomException("строка не является математической операцией.");
        }
        if (piece.length > 3 || !sign.contains(piece[1])){
            throw new CustomException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        else if ((!arabicNumerals.contains(piece[0]) && !romanNumerals.contains(piece[0])) || (!arabicNumerals.contains(piece[2]) && !romanNumerals.contains(piece[2]))){
            throw new CustomException("Первый или второй операнд вне диапазона допустимых значений");
        }
        else if ((arabicNumerals.contains(piece[0]) && romanNumerals.contains(piece[2])) || (arabicNumerals.contains(piece[2]) && romanNumerals.contains(piece[0]))) {
            throw new CustomException("Операнды разного типа");
        }
        else if (arabicNumerals.contains(piece[0])){
            expr = new Expression(arabicNumerals.indexOf(piece[0]) + 1, arabicNumerals.indexOf(piece[2]) + 1, getOperator(piece[1]), NumeralType.arabic);
        }
        else if (romanNumerals.contains(piece[0])){
            expr = new Expression(romanNumerals.indexOf(piece[0]) + 1, romanNumerals.indexOf(piece[2]) + 1, getOperator(piece[1]), NumeralType.roman);
        }
        else {
            throw new CustomException("Некорректный ввод");
        }

        int result = switch (expr.operator){
            case plus -> expr.op1 + expr.op2;
            case minus -> expr.op1 - expr.op2;
            case multiply -> expr.op1 * expr.op2;
            case divide -> expr.op1 / expr.op2;
            default -> 0;
        };

        if (expr.type == NumeralType.roman && result <= 0){
            throw new CustomException("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
        }
        else if (expr.type == NumeralType.roman && result > 0) {
            return convertToRoman(result);
        }
        else {
            return String.valueOf(result);
        }
    }
    static String convertToRoman(int number){

        List romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = (RomanNumeral) romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
    static Operators getOperator(String operator){
        switch (operator){
            case "+" -> {
                return Operators.plus;
            }
            case "-" -> {
                return Operators.minus;
            }
            case "*" -> {
                return Operators.multiply;
            }
            case "/" -> {
                return Operators.divide;
            }
        }
        return null;
    }
}