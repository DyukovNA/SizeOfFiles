import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SizeOfFiles {
    private Boolean isForHumanRead;
    private String unitOfMeasure;
    private Boolean isTotalSizeRequired;
    private Boolean isDifferentBaseRequired;
    private int base = 1024;

    public static void main(String args[]) {
        String line = "[-h B] [-c] [--si] filename1.txt filename2.txt";
        String[] ass = line.split(" ");
        /* List<String> argsList = Arrays.stream(args).toList();
        String input = String.join("", args); */
        List<String> argsList = Arrays.stream(line.split(" ")).toList();
        String a = "a";
        System.out.println(Arrays.stream(args).toList());
        new SizeOfFiles().parse(ass);
    }

    public void parse(String args[]) {

    }

    private void getFileNames() {

    }

    private void setFlags(String args[]) {
        for (String arg : args) {
            if (isForHumanRead) setUnitOfMeasure(arg);
            setIsDifferentBaseRequired(arg);
            setIsForHumanRead(arg);
            setIsTotalSizeRequired(arg);
        }
        if (isForHumanRead && unitOfMeasure == null) throw new IllegalArgumentException();
    }

    private void setIsForHumanRead(String input) {
        Pattern patternForHumanRead = Pattern.compile("(-h)");
        isForHumanRead = patternForHumanRead.matcher(input).find();
    }

    public void setUnitOfMeasure(String arg) {
        Pattern patternForUnit = Pattern.compile("(\\w{1,2}])");
        if (patternForUnit.matcher(arg).find()) {
            String unit = arg.replace("]", "");
            if (unit.toLowerCase() != "b"
                    || unit.toLowerCase() != "kb"
                    || unit.toLowerCase() != "mb"
                    || unit.toLowerCase() != "gb") throw new IllegalArgumentException();
            else unitOfMeasure = unit.toUpperCase();
        }
    }

    private void setIsTotalSizeRequired(String input) {
        Pattern patternForTotalSize = Pattern.compile("(-c)");
        isTotalSizeRequired = patternForTotalSize.matcher(input).find();
    }

    private void setIsDifferentBaseRequired(String input) {
        Pattern patternForDifferentBase = Pattern.compile("(--si)");
        isDifferentBaseRequired = patternForDifferentBase.matcher(input).find();
        if (isDifferentBaseRequired) base = 1000;
    }
}
