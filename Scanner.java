// analogue of the scanner class based on the reader class

import java.io.*;
import java.lang.NumberFormatException;

class tokenChecker implements Checker {
    public boolean isDelimeter(char c) {
        return Character.isWhitespace(c);
    }
}

public class Scanner{
    private final Reader in;
    private int let;
    private char[] buffer = new char[1024];
    private int i = 0;
    private int sz = 0;
    private Checker checker = new tokenChecker();

    private boolean check(int l) {
        return checker.isDelimeter((char)l);
    }

    private int read() throws IOException {
        if (i >= sz) {
            sz = in.read(buffer);
            i = 0;
        }
        return ((int)buffer[i++]);
    }

    Scanner(InputStream sc) throws IOException {
        this.in = new InputStreamReader(sc);
        this.let = read();
    }

    Scanner(InputStreamReader input) throws IOException {  
        this.in = input;
        this.let = read();
    }

    Scanner(Reader sc) throws IOException {
        this.in = sc;
        this.let = read();
    }

    Scanner(InputStream sc, Checker ch) throws IOException {
        this.in = new InputStreamReader(sc);
        this.let = read();
        this.checker = ch;
    }

    Scanner(InputStreamReader input, Checker ch) throws IOException {  
        this.in = input;
        this.let = read();
        this.checker = ch;
    }

    Scanner(Reader sc, Checker ch) throws IOException {
        this.in = sc;
        this.let = read();
        this.checker = ch;
    }

    public String nextLine() throws IOException {
        StringBuilder answ = new StringBuilder();
        while (sz != -1 && let != '\n' && let != '\r') {
            answ.append((char)let);
            let = read(); 
        }
        if (let == '\r') {
            let = read();
            if (let == '\n') {
                let = read();
            }
        } else {
            let = read();
        }
        return answ.toString();
    }
    
    public boolean hasNextLine() throws IOException {
        return sz != -1;
    }

    public boolean hasNext() throws IOException {
        while (sz != -1 && check(let)) {
            let = read();
        }
        return sz != -1;
    }

    public String next() throws IOException {
        StringBuilder answ = new StringBuilder("");
        while (sz != -1 && check(let)) {
            let = read();
        }
        while (sz != -1 && !(check(let))) {
            answ.append((char)let);
            let = read(); 
        }
        return answ.toString();
    }
    // to be or not to be
    public boolean hasNextInLine() throws IOException {
        while (sz != -1 && let != '\n' && let != '\r' && check(let)) {
            System.out.println("Now let = " + let);
            let = read();
        }
        return !(check(let)) && sz != -1;
    }
    
    public boolean hasNextInt() throws IOException {
        in.mark(1 << 30);
        String s = next();
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException er) {
            in.reset();
            return false;
        }
        in.reset();
        return true;
    }

    public int nextInt(int rad) throws IOException {
        return Integer.parseInt(next());
    }
    
    public void close() throws IOException {
        in.close();
    }
}