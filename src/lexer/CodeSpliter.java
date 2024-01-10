package lexer;

import java.util.ArrayList;
import java.util.List;


public class CodeSpliter {
    public static void combineStringsInQuotes(ArrayList<String> resultList) {
        boolean insideQuotes = false;
        StringBuilder sb = new StringBuilder();
        int startIndex = -1;

        for (int i = 0; i < resultList.size(); i++) {
            String current = resultList.get(i);

            if ("\"".equals(current)) {
                if (insideQuotes) {
                    insideQuotes = false;
                    sb.append("\"");
                    resultList.set(startIndex, sb.toString());
                    while (i - startIndex > 1) {
                        resultList.remove(startIndex + 1);
                        i--;
                    }
                    sb.setLength(0);
                } else {
                    insideQuotes = true;
                    startIndex = i;
                    sb.append("\"");
                }
            } else if (insideQuotes) {
                sb.append(" ").append(current);
            }
        }
    }

    private List<String> codeSplitted;

    public CodeSpliter(String code) {

        code = code.replaceAll("\r?\n", " ");
        code = code.replaceAll("\\{", " { ");
        code = code.replaceAll("\\}", " } ");
        code = code.replaceAll("\\(", " ( ");
        code = code.replaceAll("\\)", " ) ");
        code = code.replaceAll("\\[", " [ ");
        code = code.replaceAll("\\]", " ] ");
        code = code.replaceAll(",", " , ");
        code = code.replaceAll(";", " ; ");
        code = code.replaceAll("\"", " \" ");
        code = code.replaceAll("\'", " \' ");

        String[] codeSplitted1 = code.split(" ");
        ArrayList<String> resultList = new ArrayList<>();


        for (String s : codeSplitted1) {
            if (!s.equals(" ") && !s.equals("")) {
                resultList.add(s);
            }
        }
        //"" STRING
        combineStringsInQuotes(resultList);
        //SCAN DEST
        for(int i = 0; i < resultList.size() - 1; i++){
            if(resultList.get(i).equals("и") && resultList.get(i+1).equals("унеси") && resultList.get(i+2).equals("у")){
                resultList.set(i, "и унеси у");
                resultList.remove(i+1);
                resultList.remove(i+1);
                i = i - 2;
            }
        }
        //ELIF
        for(int i = 0; i < resultList.size() - 1; i++){
            if(resultList.get(i).equals("иначе") && resultList.get(i+1).equals("ако")){
                resultList.set(i, "иначе ако");
                resultList.remove(i+1);
                i--;
            }
        }
        //CELI BROJ
        for(int i = 0; i < resultList.size() - 1; i++){
            if(resultList.get(i).equals("цели") && resultList.get(i+1).equals("број")){
                resultList.set(i, "цели број");
                resultList.remove(i+1);
                i--;
            }
        }
        //REALAN BROJ
        for(int i = 0; i < resultList.size() - 1; i++){
            if(resultList.get(i).equals("реалан") && resultList.get(i+1).equals("број")){
                resultList.set(i, "реалан број");
                resultList.remove(i+1);
                i--;
            }
        }
        //<=
        for(int i = 0; i < resultList.size() - 1; i++){
            if(resultList.get(i).equals("већи") && resultList.get(i+1).equals("једнак")){
                resultList.set(i, "већи једнак");
                resultList.remove(i+1);
                i--;
            }
        }
        //>=
        for(int i = 0; i < resultList.size() - 1; i++){
            if(resultList.get(i).equals("мањи") && resultList.get(i+1).equals("једнак")){
                resultList.set(i, "мањи једнак");
                resultList.remove(i+1);
                i--;
            }
        }
        codeSplitted = resultList;
    }

    public List<String> getCodeSplitted() {
        return codeSplitted;
    }
}
