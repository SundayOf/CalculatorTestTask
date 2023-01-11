import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
// Воспользовался https://www.codeflow.site/ru/article/java-convert-roman-arabic
enum RomanNumeral{
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100);
    int value;
    RomanNumeral(int value) { this.value = value; }
    int getValue() { return value; }
    public static List getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}
