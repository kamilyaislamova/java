import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.io.*;
//java -ea -jar WsppTest.jar SortedFirst

public class WsppSortedFirst {
    private static class WordChecker implements Checker {
        public boolean isDelimeter(char c) {
            return !(Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
        }
    }

    private static int add(String word, Map<String, Occurrence> voc, int sz, Set<String> entr) {
        Occurrence oc = new Occurrence();
        if (voc.containsKey(word)) {
            oc = voc.get(word);
        }
        oc.newEntr();
        if (!entr.contains(word)) {
            oc.add(sz);
            entr.add(word);
        }
        voc.put(word, oc);
        sz++;
        return sz;
    }

    public static void main(String[] args) {
        Map<String, Occurrence> voc = new TreeMap<>();
        int occurrenceNumber = 1;
        try {
            Scanner in = new Scanner(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"), new WordChecker());
            try {
                while (in.hasNext()) {
                    HashSet<String> entr = new HashSet<>();
                    String s = in.next().toLowerCase();
                    occurrenceNumber = add(s, voc, occurrenceNumber, entr);
                    while (in.hasNextInLine()) {
                        s = in.next().toLowerCase();
                        occurrenceNumber = add(s, voc, occurrenceNumber, entr);
                    }
                }
            } finally {
                in.close();
            }
        } catch (java.nio.file.NoSuchFileException er) {
            System.out.println("File doesn't exist" + er.getMessage());
        } catch (FileNotFoundException er) {
            System.out.println("Input file not found " + er.getMessage());
        } catch (UnsupportedEncodingException er) {
            System.out.println("This encoding is not supported " + er.getMessage());
        } catch (IOException er) {
            System.out.println("IOException " + er.getMessage());
        }

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));
            try {
                for (Map.Entry<String, Occurrence> answ : voc.entrySet()) {
                    out.write(answ.getKey());
                    out.write(" ");
                    Occurrence ans = answ.getValue();
                    out.write(Integer.toString(ans.size()));
                    out.write(" ");
                    IntList a = ans.getIntlist();
                    for (int q = 0; q < a.size() - 1; q++) {
                        out.write(Integer.toString(a.get(q)));
                        out.write(" ");
                    }
                    out.write(Integer.toString(a.get(a.size() - 1)));
                    out.newLine();
                }
            } finally {
                out.close();
            }
        } catch (FileNotFoundException er) {
            System.out.println("Input or Output file not found " + er.getMessage());
        } catch (IOException er) {
            System.out.println("IOException " + er.getMessage());
        }
    }

}
