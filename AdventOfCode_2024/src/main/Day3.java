package main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day3 {
    private static int start = 0;
    private static int current = 0;
    private static byte[] bytes;
    private static String source;
    private static int total = 0;

    public static void main(String[] args) throws IOException {
        bytes = Files.readAllBytes(Paths.get("puzzleInputFiles/day3.txt"));
        source = new String(bytes, Charset.defaultCharset());
        scan();
    }

    private static void scan() {
        boolean doSum = true;
        while (!isAtEnd()) {

            char c = advance();
            if (c == 'd') {
                doSum = isDo(doSum);
            }

            if (c == 'm') {
                if (isMul()) {
                    System.out.print("mul");
                    advance();
                    if (peek() == '(') {
                        int x = 0;
                        int y = 0;
                        System.out.print("(");
                        advance();

                        if (isDigit(peek())) {
                            System.out.print("x: ");
                            x = createNumber();
                        }

                        if (peek() == ',') {
                            advance();
                            if (isDigit(peek())) {
                                System.out.print("y: ");
                                y = createNumber();
                                if (peek() == ')') {
                                    advance();
                                    System.out.println("doSum: " + doSum);
                                    if (doSum) {
                                        total += x * y;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("total: " + total);
    }

    private static boolean isAtEnd() {
        return current >= source.length();
    }

    private static char advance() {
        return source.charAt(current++);
    }

    private static boolean isMul() {
        if (peek() == 'u') {
            advance();
            return peek() == 'l';
        }

        return false;
    }

    private static boolean isDo(boolean currentState) {
        if (peek() == 'o') {
            advance();
            if (peek() != 'n') {
                return true;
            } else {
                advance();
                return peek() != '\'' || peekPeek() != 't';
            }
        }
        return currentState;
    }

    private static boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    private static char peek() {
        if (isAtEnd()) {
            return '\0';
        }

        return source.charAt(current);
    }

    private static char peekPeek() {
        if (isAtEnd()) {
            return '\0';
        }

        return source.charAt(current + 1);
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static int createNumber() {
        int numberLength = 0;
        start = current;

        while (numberLength < 4 && isDigit(peek())) {
            advance();
            numberLength++;
        }

        if (numberLength <= 3 && (peek() == ',' || peek() == ')')) {
            System.out.print(source.substring(start, current));
            System.out.print(peek());
            if (peek() == ')') {
                System.out.println();
            }
            return Integer.parseInt(source.substring(start, current));
        }

        return 0;
    }
}

