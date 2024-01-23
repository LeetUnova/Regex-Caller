package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static final Pattern getFileFolder = Pattern.compile("^(.+\\/)?([^/]+)");

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("regexcaller [regexes] [destination] [files...]\nUse regexcaller help for more info.");
            return;
        }

        if (args[0].equals("help")) {
            System.out.println("Regex file format:\nRegex\nReplacement\n\nRegex\nReplacement\n...\n\nDestination:\nFolder to place finished files in.\n\nFiles:\nAny number of files to use the regex on.");
            return;
        }

        if (args.length < 3) {
            System.out.println("regexcaller [regexes] [destination] [files...]\nUse regexcaller help for more info.");
            return;
        }

        String regexes = fromFile(args[0]);

        for (int i = 2; i < args.length; i++)
            printToFile(args[1] + "/" + getFileName(args[i]), replace(fromFile(args[i]), regexes));
    }

    public static String getFileName(String filename) throws Exception {
        Matcher matcher = getFileFolder.matcher(filename);
        if (!matcher.matches())
            throw new Exception("How did we get here");
        return matcher.group(2);
    }

    public static String fromFile(String filename) throws FileNotFoundException {
        String str = "";
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine())
                str += scanner.nextLine() + "\n";
        }
        return str;
    }

    public static void printToFile(String filename, String data) throws FileNotFoundException {
        try (PrintStream stream = new PrintStream(new File(filename))) {
            stream.print(data);
        }
    }

    public static String replace(String str, String regexes) {
        String ret = str;
        try (Scanner scanner = new Scanner(regexes)) {
            while (scanner.hasNextLine()) {
                String regex = scanner.nextLine();
                if (regex.equals(""))
                    continue;

                String repla = scanner.nextLine().replace("\\n", "\n");
                ret = ret.replaceAll(regex, repla);
            }
        }
        return ret;
    }
}
