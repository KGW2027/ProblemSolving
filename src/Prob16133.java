import java.io.BufferedInputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.math.MathContext;

public class Prob16133 {

    static PushbackInputStream pis;

    public static void main(String[] args) throws Exception {
        pis = new PushbackInputStream(new BufferedInputStream(System.in));
        System.out.print(parseExpr());
    }

    static long parseExpr() throws Exception {
        long left = parseTerm();
        int peek = peek();
        while (peek == '+' || peek == '-') {
            pis.read();
            long next = parseTerm();
            if (peek == '+') left += next;
            else left -= next;
            peek = peek();
        }
        return left;
    }

    static long parseTerm() throws Exception {
        long left = parseFactor();
        int peek = peek();
        while (peek == '*' || peek == '/') {
            pis.read();
            long next = parseFactor();
            if (peek == '*') left *= next;
            else left /= next;
            peek = peek();
        }
        return left;
    }

    static long parseFactor() throws Exception {
        long left = parsePower();
        if (peek() == '^') {
            pis.read();
            long factor = parseFactor();
            return (long) Math.pow(left, factor);
        } else return left;
    }

    static long parsePower() throws Exception {
        if (peek() == '#') {
            pis.read();
            long power = parsePower();
            return new BigDecimal(power).sqrt(MathContext.DECIMAL128).longValue();
        } else {
            return parseRoot();
        }
    }

    static long parseRoot() throws Exception {
        if (peek() == '(') {
            pis.read();
            long res = parseExpr();
            pis.read();
            return res;
        } else {
            long num = parseNum();
            return num;
        }
    }

    static long parseNum() throws Exception {
        if (peek() == '0') return pis.read() - '0';
        long n = 0;
        int b;
        do {
            b = pis.read();
            if ('0' <= b && b <= '9') n = n * 10 + (b - '0');
        } while ('0' <= b && b <= '9');

        pis.unread(b);
        return n;
    }

    static int peek() throws Exception {
        int peek = pis.read();
        pis.unread(peek);
        return peek;
    }
}

